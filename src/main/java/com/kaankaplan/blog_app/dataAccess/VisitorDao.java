package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorDao extends JpaRepository<Visitor, Integer> {


}
