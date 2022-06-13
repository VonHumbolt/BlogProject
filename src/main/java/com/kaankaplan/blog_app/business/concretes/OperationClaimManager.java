package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.OperationClaimService;
import com.kaankaplan.blog_app.dataAccess.OperationClaimDao;
import com.kaankaplan.blog_app.entities.OperationClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationClaimManager implements OperationClaimService {

    private final OperationClaimDao operationClaimDao;

    @Autowired
    public OperationClaimManager(OperationClaimDao operationClaimDao) {
        this.operationClaimDao = operationClaimDao;
    }

    @Override
    public OperationClaim getClaimByName(String claimName) {
        return this.operationClaimDao.findByClaimName(claimName);
    }
}
