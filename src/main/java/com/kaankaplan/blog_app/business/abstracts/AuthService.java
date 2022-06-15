package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.EmailVerificationToken;
import com.kaankaplan.blog_app.entities.User;

public interface AuthService {

    void register(User user);

    void verifyAccount(String verificationToken);
}
