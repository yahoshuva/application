package com.application.linkedinpost.repository;

import com.application.linkedinpost.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}