package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.LikedPostService;
import com.kaankaplan.blog_app.business.abstracts.PostService;
import com.kaankaplan.blog_app.business.abstracts.UserService;
import com.kaankaplan.blog_app.dataAccess.UserDao;
import com.kaankaplan.blog_app.entities.LikedPost;
import com.kaankaplan.blog_app.entities.User;
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
    public void delete(int userId) {
        this.userDao.deleteById(userId);
    }

    @Override
    @Transactional
    public void likePost(int postId, int userId) {
        User user = this.userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        this.postService.updateLikeCount(postId, 1);

        LikedPost likedPost = LikedPost.builder().postId(postId).user(user).build();
        likedPostService.add(likedPost);
    }

    @Override
    @Transactional
    public void removelikePost(int postId, int userId) {

        this.postService.updateLikeCount(postId, -1);

        LikedPost likedPost = this.likedPostService.getUserLikePost(userId, postId);
        this.likedPostService.delete(likedPost.getLikedPostId());
    }


}
