package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.LikedPostService;
import com.kaankaplan.blog_app.business.abstracts.PostService;
import com.kaankaplan.blog_app.business.abstracts.UserService;
import com.kaankaplan.blog_app.dataAccess.UserDao;
import com.kaankaplan.blog_app.entities.LikedPost;
import com.kaankaplan.blog_app.entities.Post;
import com.kaankaplan.blog_app.entities.User;
import com.kaankaplan.blog_app.entities.dtos.UpdatedUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final LikedPostService likedPostService;
    private final PostService postService;

    @Autowired
    public UserManager(UserDao userDao, LikedPostService likedPostService, PostService postService) {
        this.userDao = userDao;
        this.likedPostService = likedPostService;
        this.postService = postService;
    }

    @Override
    public List<User> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userDao.findAll(pageable).getContent();
    }

    @Override
    public User getUserById(int userId) {
        return this.userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //.orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public void add(User user) {
        this.userDao.save(user);
    }

    @Override
    public void delete(int userId) {
        this.userDao.deleteById(userId);
    }

    @Override
    public void update(int userId, UpdatedUserDto userDto) {
        User userFromDb = this.userDao.findById(userId).orElse(null);

        if (userFromDb != null) {
            userFromDb.setFirstName(userDto.getFirstName());
            userFromDb.setLastName(userDto.getLastName());
            userFromDb.setEmail(userDto.getEmail());

            this.userDao.save(userFromDb);
        }
    }

    @Override
    @Transactional
    public void likePost(int postId, int userId) {
        User user = this.userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        LikedPost userLikePost = this.likedPostService.getUserLikePost(userId, postId);
        if (userLikePost != null) {
            throw new RuntimeException("Post is already liked!");
        }
        Post post = this.postService.getPostById(postId);
        this.postService.updateLikeCount(postId, 1);

        LikedPost likedPost = LikedPost.builder().post(post).user(user).build();
        likedPostService.add(likedPost);
    }

    @Override
    @Transactional
    public void removelikePost(int postId, int userId) {

        LikedPost userLikePost = this.likedPostService.getUserLikePost(userId, postId);
        if (userLikePost == null){
            throw new RuntimeException("There is no like for post");
        }

        this.postService.updateLikeCount(postId, -1);

        LikedPost likedPost = this.likedPostService.getUserLikePost(userId, postId);
        this.likedPostService.delete(likedPost.getLikedPostId());
    }


}
