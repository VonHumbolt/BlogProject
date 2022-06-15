package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenDao  extends JpaRepository<EmailVerificationToken , Integer> {

    EmailVerificationToken findByToken(String token);

}
