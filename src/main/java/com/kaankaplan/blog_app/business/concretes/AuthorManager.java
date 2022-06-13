package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.AuthorService;
import com.kaankaplan.blog_app.business.abstracts.OperationClaimService;
import com.kaankaplan.blog_app.dataAccess.AuthorDao;
import com.kaankaplan.blog_app.entities.Author;
import com.kaankaplan.blog_app.entities.OperationClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {
    private final AuthorDao authorDao;
    private final OperationClaimService operationClaimService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorManager(AuthorDao authorDao, OperationClaimService operationClaimService, PasswordEncoder passwordEncoder) {
        this.authorDao = authorDao;
        this.operationClaimService = operationClaimService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Author> getAllAuthors(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.authorDao.findAll(pageable).getContent();
    }

    @Override
    public Author getAuthorById(int authorId) {
        return this.authorDao.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public Author add(Author author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));

        OperationClaim claim = this.operationClaimService.getClaimByName("AUTHOR");
        author.setClaim(claim);

        return this.authorDao.save(author);
    }

    @Override
    public void delete(int authorId) {
        this.authorDao.deleteById(authorId);
    }
}
