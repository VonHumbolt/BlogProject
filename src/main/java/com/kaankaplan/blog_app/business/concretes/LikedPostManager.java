package com.kaankaplan.blog_app.business.concretes;

import com.kaankaplan.blog_app.business.abstracts.LikedPostService;
import com.kaankaplan.blog_app.dataAccess.LikedPostDao;
import com.kaankaplan.blog_app.entities.LikedPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedPostManager implements LikedPostService {

    private final LikedPostDao likedPostDao;

    @Autowired
    public LikedPostManager(LikedPostDao likedPostDao) {
        this.likedPostDao = likedPostDao;
    }

    @Override
    public List<LikedPost> getAllLikedPost(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.likedPostDao.findAll(pageable).getContent();
    }

    @Override
    public List<LikedPost> getUsersLikedPosts(int userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.likedPostDao.getUsersLikedPosts(userId, pageable);
    }

    @Override
    public LikedPost getLikedPostById(int likedPostId) {
        return this.likedPostDao.findById(likedPostId).orElseThrow(() -> new RuntimeException("Liked Post not found"));
    }

    @Override
    public void add(LikedPost likedPost) {
        this.likedPostDao.save(likedPost);
    }

    @Override
    public void delete(int likedPostId) {
        this.likedPostDao.deleteById(likedPostId);
    }
}
