package com.kaankaplan.blog_app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "liked_posts")
public class LikedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_post_id")
    private int likedPostId;

    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
