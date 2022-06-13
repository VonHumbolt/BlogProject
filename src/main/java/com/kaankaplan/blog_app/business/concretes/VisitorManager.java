package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.OperationClaimService;
import com.kaankaplan.blog_app.business.abstracts.VisitorService;
import com.kaankaplan.blog_app.dataAccess.VisitorDao;
import com.kaankaplan.blog_app.entities.OperationClaim;
import com.kaankaplan.blog_app.entities.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorManager implements VisitorService {

    private final VisitorDao visitorDao;
    private final OperationClaimService operationClaimService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public VisitorManager(VisitorDao visitorDao, OperationClaimService operationClaimService, PasswordEncoder passwordEncoder) {
        this.visitorDao = visitorDao;
        this.operationClaimService = operationClaimService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Visitor> getAllVisitors(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.visitorDao.findAll(pageable).getContent();
    }

    @Override
    public Visitor getVisitorById(int visitorId) {
        return this.visitorDao.findById(visitorId).orElseThrow(() -> new RuntimeException("Visitor not found"));
    }

    @Override
    public void add(Visitor visitor) {
        visitor.setPassword(passwordEncoder.encode(visitor.getPassword()));

        OperationClaim claim = this.operationClaimService.getClaimByName("VISITOR");
        visitor.setClaim(claim);

        this.visitorDao.save(visitor);
    }

    @Override
    public void delete(int visitorId) {
        this.visitorDao.deleteById(visitorId);
    }
}
