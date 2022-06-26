package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.AuthorService;
import com.kaankaplan.blog_app.business.abstracts.PostService;
import com.kaankaplan.blog_app.dataAccess.PostDao;
import com.kaankaplan.blog_app.entities.Author;
import com.kaankaplan.blog_app.entities.Post;
import com.kaankaplan.blog_app.entities.User;
import com.kaankaplan.blog_app.entities.dtos.EditPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostManager implements PostService {

    private final PostDao postDao;
    private final AuthorService authorService;

    @Autowired
    public PostManager(PostDao postDao, AuthorService authorService) {
        this.postDao = postDao;
        this.authorService = authorService;
    }

    @Override
    public List<Post> getPostsBySortedDate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.postDao.getPostsBySortedDate(pageable);
    }

    @Override
    public List<Post> getPostsBySortedLikeCount(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.postDao.getPostsBySortedLikeCount(pageable);
    }

    @Override
    public List<Post> getPostsByAuthorId(int authorId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.postDao.getPostsByAuthorId(authorId, pageable);
    }

    @Override
    public List<Post> getPostsByTitle(String word, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.postDao.getPostsByTitle(word, pageable);
    }

    @Override
    public List<Post> getPostsByDescription(String word,  int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.postDao.getPostsByDescription(word, pageable);
    }

    @Override
    public List<Post> getPostsByContent(String word,  int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.postDao.getPostsByContent(word,pageable);
    }

    @Override
    public List<Post> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.postDao.findAll(pageable).getContent();
    }

    @Override
    public Post getPostById(int postId) {
        return this.postDao.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    @Transactional
    public Post add(int userId, Post post) {

        Author author = this.authorService.getAuthorById(userId);

        post.setAuthor(author);
        Post addedPost = this.postDao.save(post);
        return addedPost;
    }

    @Override
    @Transactional
    public void updateLikeCount(int postId, int count) {
        Post post = this.postDao.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikeCount(post.getLikeCount() + count);
        this.postDao.save(post);
    }

    @Override
    public void delete(int postId) {
        this.postDao.deleteById(postId);
    }

    @Override
    @Transactional
    public void editPost(int postId, int userId, EditPostDto postDto) {
        Post postFromDb = this.postDao.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        if (postFromDb.getAuthor().getUserId() == userId){
            postFromDb.setTitle(postDto.getTitle());
            postFromDb.setDescription(postDto.getDescription());
            postFromDb.setContent(postDto.getContent());
            postFromDb.setLikeCount(0);

            this.postDao.save(postFromDb);
        } else {
            throw new RuntimeException("Bu işlem için yetkiniz bulunmamaktadır");
        }
    }

    @Override
    public int getNumberOfPosts() {
        return this.postDao.getNumberOfPosts();
    }

    @Override
    public int getAuthorNumberOfPosts(int authorId) {
        return this.postDao.getAuthorNumberOfPosts(authorId);
    }
}
