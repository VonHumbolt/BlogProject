package com.kaankaplan.blog_app.api.controllers;

import com.kaankaplan.blog_app.business.abstracts.VisitorService;
import com.kaankaplan.blog_app.entities.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/visitors/")
@CrossOrigin
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("getall")
    public ResponseEntity<List<Visitor>> getAllVisitors(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize){
        List<Visitor> visitors = this.visitorService.getAllVisitors(pageNo.orElse(1), pageSize.orElse(10));
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    @GetMapping("getById/{visitorId}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable int visitorId){
        Visitor visitor = this.visitorService.getVisitorById(visitorId);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
}
