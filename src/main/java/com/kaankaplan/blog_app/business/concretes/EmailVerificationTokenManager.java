package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.EmailVerificationTokenService;
import com.kaankaplan.blog_app.dataAccess.EmailVerificationTokenDao;
import com.kaankaplan.blog_app.entities.EmailVerificationToken;
import com.kaankaplan.blog_app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailVerificationTokenManager implements EmailVerificationTokenService {

    private final EmailVerificationTokenDao emailVerificationTokenDao;

    @Autowired
    public EmailVerificationTokenManager(EmailVerificationTokenDao emailVerificationTokenDao) {
        this.emailVerificationTokenDao = emailVerificationTokenDao;
    }

    @Override
    public EmailVerificationToken getByToken(String token) {
        return this.emailVerificationTokenDao.findByToken(token);
    }

    @Override
    public EmailVerificationToken createEmailVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .build();

        return this.emailVerificationTokenDao.save(emailVerificationToken);
    }
}
