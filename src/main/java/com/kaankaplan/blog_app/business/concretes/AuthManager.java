package com.kaankaplan.blog_app.business.concretes;

import com.google.common.collect.Lists;
import com.kaankaplan.blog_app.business.abstracts.AuthService;
import com.kaankaplan.blog_app.business.abstracts.AuthorService;
import com.kaankaplan.blog_app.business.abstracts.UserService;
import com.kaankaplan.blog_app.entities.Author;
import com.kaankaplan.blog_app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthManager implements AuthService, UserDetailsService {

    private final UserService userService;
    private final AuthorService authorService;

    @Autowired
    public AuthManager(UserService userService, AuthorService authorService) {
        this.userService = userService;
        this.authorService = authorService;
    }

    @Override
    public void register(User user) {
        User userFromDb = this.userService.findByEmail(user.getEmail());

        if (userFromDb == null) {
            Author author = (Author) Author.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();

            this.authorService.add(author);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userService.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        String claim = user.getClaim().getClaimName();

        List<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority(claim));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
