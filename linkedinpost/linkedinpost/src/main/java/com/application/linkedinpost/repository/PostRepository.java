package com.application.linkedinpost.repository;

import com.application.linkedinpost.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long authorId);

    List<Post> findAllByOrderByCreationDateDesc();
    List<Post> findByAuthorIdNotOrderByCreationDateDesc(Long authenticatedUserId);
}
