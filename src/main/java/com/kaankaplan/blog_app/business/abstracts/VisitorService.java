package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.Visitor;

import java.util.List;

public interface VisitorService {

    List<Visitor> getAllVisitors(int pageNo, int pageSize);

    Visitor getVisitorById(int visitorId);

    void add(Visitor visitor);

    void delete(int visitorId);
}
