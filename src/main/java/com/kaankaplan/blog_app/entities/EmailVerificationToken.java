package com.kaankaplan.blog_app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "email_verification_tokens")
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiation_date")
    private Date expritaionDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
