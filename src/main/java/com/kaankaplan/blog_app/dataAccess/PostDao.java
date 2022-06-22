package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

    @Query("From Post p order by p.publishedDate desc")
    List<Post> getPostsBySortedDate(Pageable pageable);

    @Query("From Post p order by p.likeCount desc")
    List<Post> getPostsBySortedLikeCount(Pageable pageable);

    @Query("From Post p where p.author.userId = :authorId order by p.publishedDate desc")
    List<Post> getPostsByAuthorId(int authorId, Pageable pageable);

    @Query("From Post p where p.title like '%:word%'")
    List<Post> getPostsByTitle(String word,  Pageable pageable);

    @Query("From Post p where p.description like '%:word%'")
    List<Post> getPostsByDescription(String word, Pageable pageable);

    @Query("From Post p where p.content like '%:word%'")
    List<Post> getPostsByContent(String word,  Pageable pageable);

    @Query("Select Count(p) From Post p")
    int getNumberOfPosts();

    @Query("Select Count(p) From Post p where p.author.userId=:authorId")
    int getAuthorNumberOfPosts(int authorId);
}
