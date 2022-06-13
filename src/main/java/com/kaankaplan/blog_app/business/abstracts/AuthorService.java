package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors(int pageNo, int pageSize);

    Author getAuthorById(int authorId);

    Author add(Author author);

    void delete(int authorId);
}
