package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(int pageNo, int pageSize);

    User getUserById(int userId);

    User findByEmail(String email);

    void delete(int userId);
}
