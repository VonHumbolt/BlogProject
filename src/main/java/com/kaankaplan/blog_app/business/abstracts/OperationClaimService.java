package com.kaankaplan.blog_app.business.abstracts;

import com.kaankaplan.blog_app.entities.OperationClaim;

public interface OperationClaimService {

    OperationClaim getClaimByName(String claimName);
}
