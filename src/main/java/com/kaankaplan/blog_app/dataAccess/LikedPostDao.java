package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.LikedPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedPostDao extends JpaRepository<LikedPost, Integer> {

    @Query("From LikedPost p where p.user.userId = :userId")
    List<LikedPost> getUsersLikedPosts(int userId, Pageable pageable);

    @Query("From LikedPost p where p.user.userId = :userId and p.post.postId = :postId")
    LikedPost getUserLikePost(int userId, int postId);

    @Query("Select Count(p) From LikedPost p where p.user.userId = :userId")
    int getNumberOfUsersLikedPosts(int userId);

    @Query("Select Count(p) From LikedPost p where p.post.postId = :postId")
    int getPostLikeCount(int postId);
}
