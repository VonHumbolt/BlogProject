package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.EmailVerificationToken;
import com.kaankaplan.blog_app.entities.User;

public interface EmailVerificationTokenService {

    EmailVerificationToken getByToken(String token);

    EmailVerificationToken createEmailVerificationToken(User user);
}
