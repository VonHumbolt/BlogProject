package com.kaankaplan.blog_app.business.concretes;

import com.google.common.collect.Lists;
import com.kaankaplan.blog_app.business.abstracts.AuthService;
import com.kaankaplan.blog_app.business.abstracts.AuthorService;
import com.kaankaplan.blog_app.business.abstracts.EmailVerificationTokenService;
import com.kaankaplan.blog_app.business.abstracts.UserService;
import com.kaankaplan.blog_app.core.mail.abstracts.MailService;
import com.kaankaplan.blog_app.core.mail.modal.NotificationEmail;
import com.kaankaplan.blog_app.entities.Author;
import com.kaankaplan.blog_app.entities.EmailVerificationToken;
import com.kaankaplan.blog_app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthManager implements AuthService, UserDetailsService {

    private final UserService userService;
    private final AuthorService authorService;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final MailService mailService;

    @Autowired
    public AuthManager(UserService userService, AuthorService authorService,
                       EmailVerificationTokenService emailVerificationTokenService, MailService mailService) {
        this.userService = userService;
        this.authorService = authorService;
        this.emailVerificationTokenService = emailVerificationTokenService;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public void register(User user) {
        User userFromDb = this.userService.findByEmail(user.getEmail());

        if (userFromDb == null) {
            Author author = new Author();
            author.setFirstName(user.getFirstName());
            author.setLastName(user.getLastName());
            author.setEmail(user.getEmail());
            author.setPassword(user.getPassword());

            User savedAuthor = this.authorService.add(author);

            String token = this.emailVerificationTokenService.createEmailVerificationToken(savedAuthor).getToken();
            mailService.sendMail(new NotificationEmail("Please Activate Your Account", user.getEmail(),
                    "Thank you for signing up to Blog App! For activating your account, please click on the link:" +
                            " http://localhost:8080/api/v1/auth/accountVerification/" + token));
        }
    }

    @Transactional
    @Override
    public void verifyAccount(String verificationToken) {
        EmailVerificationToken emailVerificationToken = this.emailVerificationTokenService.getByToken(verificationToken);

        if(emailVerificationToken == null)
            throw new RuntimeException("Invalid Token!");

        User user = userService.findByEmail(emailVerificationToken.getUser().getEmail());
        user.setActive(true);
        userService.add(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userService.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("Email is already used");

        if (!user.isActive()) {
            throw new RuntimeException("Please verify your account using the activation link sent to your email");
        }

        String claim = user.getClaim().getClaimName();

        List<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority(claim));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
