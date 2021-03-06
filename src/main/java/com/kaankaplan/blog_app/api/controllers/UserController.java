package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.UserService;
import com.kaankaplan.blog_app.entities.User;
import com.kaankaplan.blog_app.entities.dtos.UpdatedUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users/")
@CrossOrigin
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("getall")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {
        List<User> users = this.userService.getAllUsers(pageNo.orElse(1), pageSize.orElse(10));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("getById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("getByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = this.userService.findByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable int userId) {
        this.userService.delete(userId);

        return new ResponseEntity<>("User is deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('AUTHOR')")
    @PostMapping("update/{userId}")
    public ResponseEntity<String> update(@PathVariable int userId, @RequestBody UpdatedUserDto userDto) {
        this.userService.update(userId, userDto);

        return new ResponseEntity<>("User is updated", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @PostMapping("like/{postId}/{userId}")
    public ResponseEntity<String> likePost(@PathVariable int postId, @PathVariable int userId) {
        this.userService.likePost(postId, userId);

        return new ResponseEntity<>("Post liked", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @PostMapping("removeLike/{postId}/{userId}")
    public ResponseEntity<String> removeLikePost(@PathVariable int postId, @PathVariable int userId) {
        this.userService.removelikePost(postId, userId);

        return new ResponseEntity<>("Post like is removed", HttpStatus.OK);
    }
}
