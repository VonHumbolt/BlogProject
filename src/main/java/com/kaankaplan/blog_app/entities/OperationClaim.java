package com.kaankaplan.blog_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "operation_claims")
public class OperationClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private int claimId;

    @Column(name = "claim_name")
    private String claimName;

    @OneToMany(mappedBy = "claim")
    @JsonIgnore
    private List<User> users;

}
