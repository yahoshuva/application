package com.application.linkedinpost.service;

import com.application.linkedinpost.dto.PostDto;
import com.application.linkedinpost.model.AuthenticationUser;
import com.application.linkedinpost.model.Comment;
import com.application.linkedinpost.model.Post;
import com.application.linkedinpost.repository.AuthenticationUserRepository;
import com.application.linkedinpost.repository.CommentRepository;
import com.application.linkedinpost.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    private final PostRepository postRepository;
    private final AuthenticationUserRepository authenticationUserRepository;

    private final CommentRepository commentRepository;

    public FeedService(PostRepository postRepository, AuthenticationUserRepository authenticationUserRepository,CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.authenticationUserRepository = authenticationUserRepository;
        this.commentRepository = commentRepository;
    }

    public Post createPost(PostDto postDto, Long authorId){
        AuthenticationUser author= authenticationUserRepository.findById(authorId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        Post post = new Post(postDto.getContent(),author);
        post.setPicture(postDto.getPicture());
        return postRepository.save(post);

    }


    public Post editPost(Long postId, Long userId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!post.getAuthor().equals(user)) {
            throw new IllegalArgumentException("User is not the author of the post");
        }
        post.setContent(postDto.getContent());
        post.setPicture(postDto.getPicture());
        return postRepository.save(post);
    }

    public List<Post> getPostsByUserId(Long userId) {

        return postRepository.findByAuthorId(userId);
    }

    public List<Post> getFeedPosts(Long authenticatedUserId) {
        return postRepository.findByAuthorIdNotOrderByCreationDateDesc(authenticatedUserId);
    }

    public List<Post> getAllPosts() {

        return postRepository.findAllByOrderByCreationDateDesc();
    }

    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        if (!post.getAuthor().equals(user)) {
            throw new IllegalArgumentException("User is not the author of the post");
        }
        postRepository.delete(post);
    }

    public Post getPost(Long postId){
        return postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("Post not found"));
    }

    public Post likePost(Long postId,Long userId){
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("Post not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));

        if(post.getLikes().contains(user)){
            post.getLikes().remove(user);
        }else{
            post.getLikes().add(user);
        }
        return postRepository.save(post);
    }



    public Comment addComment(Long postId, Long userId, String content) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Comment comment = new Comment(post, user, content);
        return commentRepository.save(comment);

    }

    public Comment editComment(Long commentId,Long userId, String newContent){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("Comment not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));

        if(!comment.getAuthor().equals(user)){
            throw new IllegalArgumentException("User is not the author of the comment");
        }
        comment.setContent((newContent));
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("Comment not found"));
        AuthenticationUser user = authenticationUserRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        if(!comment.getAuthor().equals(user)){
            throw new IllegalArgumentException("User is not the author of the comment");
        }
        commentRepository.delete(comment);
    }




}
