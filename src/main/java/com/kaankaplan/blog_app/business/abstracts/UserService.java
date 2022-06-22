package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.User;
import com.kaankaplan.blog_app.entities.dtos.UpdatedUserDto;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(int pageNo, int pageSize);

    User getUserById(int userId);

    User findByEmail(String email);

    void add(User user);

    void delete(int userId);

    void update(int userId, UpdatedUserDto userDto);

    void likePost(int postId, int userId);

    void removelikePost(int postId, int userId);

}
