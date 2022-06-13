package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
