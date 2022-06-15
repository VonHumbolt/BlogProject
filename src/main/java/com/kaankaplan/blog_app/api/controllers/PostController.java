package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.PostService;
import com.kaankaplan.blog_app.entities.Post;
import com.kaankaplan.blog_app.entities.dtos.EditPostDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts/")
@CrossOrigin
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("sortedDate")
    public ResponseEntity<List<Post>> getPostsBySortedDate(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Post> postList = this.postService.getPostsBySortedDate(pageNo.orElse(1), pageSize.orElse(10));

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("sortedLikeCount")
    public ResponseEntity<List<Post>> getPostsBySortedLikeCount(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {
        List<Post> postList = this.postService.getPostsBySortedLikeCount(pageNo.orElse(1), pageSize.orElse(10));

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("sortedLikeCount/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthorId(@PathVariable int authorId, @RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {
        List<Post> postList = this.postService.getPostsByAuthorId(authorId, pageNo.orElse(1), pageSize.orElse(10));

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("getByTitle/{word}")
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String word, @RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Post> postList = this.postService.getPostsByTitle(word, pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("getByDescription/{word}")
    public ResponseEntity<List<Post>> getPostsByDescription(@PathVariable String word, @RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Post> postList = this.postService.getPostsByDescription(word, pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("getByContent/{word}")
    public ResponseEntity<List<Post>> getPostsByContent(@PathVariable String word, @RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Post> postList = this.postService.getPostsByContent(word, pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("getall")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {

        List<Post> postList = this.postService.getAllPosts(pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("getById/{postId}")
    public ResponseEntity<Post> getAllPosts(@PathVariable int postId) {

        Post post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("add/{userId}")
    public ResponseEntity<String> add(@PathVariable int userId, @RequestBody @Valid Post post) {
        this.postService.add(userId, post);

        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
    @PostMapping("delete/{postId}")
    public ResponseEntity<String> delete(@PathVariable int postId) {
        this.postService.delete(postId);

        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("edit/{postId}/{userId}")
    public ResponseEntity<String> editPost(@PathVariable int postId, @PathVariable int userId, @RequestBody EditPostDto postDto) {
        this.postService.editPost(postId, userId, postDto);

        return new ResponseEntity<>("Post edit successfull", HttpStatus.OK);
    }
}
