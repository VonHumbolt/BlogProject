package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.AuthService;
import com.kaankaplan.blog_app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody @Valid User user){
        this.authService.register(user);

        return new ResponseEntity<>("İşlem başarılı", HttpStatus.CREATED);
    }

}
