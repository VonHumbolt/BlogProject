package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.Post;

import java.util.List;

public interface PostService {

    List<Post> getPostsBySortedDate(int pageNo, int pageSize);

    List<Post> getPostsBySortedLikeCount(int pageNo, int pageSize);

    List<Post> getPostsByAuthorId(int authorId, int pageNo, int pageSize);

    List<Post> getPostsByTitle(String word);

    List<Post> getPostsByDescription(String word);

    List<Post> getPostsByContent(String word);

    List<Post> getAllPosts(int pageNo, int pageSize);

    Post getPostById(int postId);

    void add(int userId, Post post);

    void delete(int postId);
}
