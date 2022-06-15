package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.Post;
import com.kaankaplan.blog_app.entities.dtos.EditPostDto;

import java.util.List;

public interface PostService {

    List<Post> getPostsBySortedDate(int pageNo, int pageSize);

    List<Post> getPostsBySortedLikeCount(int pageNo, int pageSize);

    List<Post> getPostsByAuthorId(int authorId, int pageNo, int pageSize);

    List<Post> getPostsByTitle(String word, int pageNo, int pageSize);

    List<Post> getPostsByDescription(String word, int pageNo, int pageSize);

    List<Post> getPostsByContent(String word, int pageNo, int pageSize);

    List<Post> getAllPosts(int pageNo, int pageSize);

    Post getPostById(int postId);

    void add(int userId, Post post);

    void updateLikeCount(int postId, int count);

    void delete(int postId);

    void editPost(int postId, int userId, EditPostDto postDto);
}
