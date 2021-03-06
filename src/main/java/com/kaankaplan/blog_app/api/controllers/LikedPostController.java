package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.LikedPostService;
import com.kaankaplan.blog_app.entities.LikedPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likedPosts/")
public class LikedPostController {

    private final LikedPostService likedPostService;

    @Autowired
    public LikedPostController(LikedPostService likedPostService) {
        this.likedPostService = likedPostService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @GetMapping("getall")
    public ResponseEntity<List<LikedPost>> getAllLikedPosts(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize){
        List<LikedPost> likedPosts = this.likedPostService.getAllLikedPost(pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(likedPosts, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @GetMapping("getUsersLikedPost/{userId}")
    public ResponseEntity<List<LikedPost>> getUsersLikedPosts(@PathVariable int userId, @RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize){
        List<LikedPost> likedPosts = this.likedPostService.getUsersLikedPosts(userId, pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(likedPosts, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @GetMapping("getById/{likedPostId}")
    public ResponseEntity<LikedPost> getByLikedPostId(@PathVariable int likedPostId){
        LikedPost likedPost = this.likedPostService.getLikedPostById(likedPostId);
        return new ResponseEntity<>(likedPost, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    @GetMapping("getUserLikedPost/{userId}/{postId}")
    public ResponseEntity<LikedPost> getUserLikedPost(@PathVariable int userId, @PathVariable int postId){
        LikedPost likedPost = this.likedPostService.getUserLikePost(userId, postId);
        return new ResponseEntity<>(likedPost, HttpStatus.OK);
    }

    @GetMapping("getNumberOfUsersLikedPosts/{userId}")
    public int getNumberOfUsersLikedPosts(@PathVariable int userId){
        return this.likedPostService.getNumberOfUsersLikedPosts(userId);
    }
    @GetMapping("getPostLikeCount/{postId}")
    public int getPostLikeCount(@PathVariable int postId){
        return this.likedPostService.getPostLikeCount(postId);
    }

}
