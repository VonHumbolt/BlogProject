package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {


}
