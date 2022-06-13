package com.kaankaplan.blog_app.dataAccess;

import com.kaankaplan.blog_app.entities.OperationClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationClaimDao extends JpaRepository<OperationClaim, Integer> {

    OperationClaim findByClaimName(String claimName);

}
