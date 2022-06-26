package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.LikedPost;

import java.util.List;

public interface LikedPostService {

    List<LikedPost> getAllLikedPost(int pageNo, int pageSize);

    List<LikedPost> getUsersLikedPosts(int userId, int pageNo, int pageSize);

    LikedPost getLikedPostById(int likedPostId);

    LikedPost getUserLikePost(int userId, int postId);

    void add(LikedPost likedPost);

    void delete(int likedPostId);

    int getNumberOfUsersLikedPosts(int userId);

    int getPostLikeCount(int postId);
}
