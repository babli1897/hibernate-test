package jpa.controller;

import jpa.model.Comments;
import jpa.repository.CommentRepository;
import jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("posts/{postId}/comments")
    Page<Comments> getComments(@PathVariable long postId, Pageable pageable)
    {
        return commentRepository.findByPostId(postId,pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    Comments createComments(@PathVariable long postId, @Valid @RequestBody Comments comments)
    {
        return postRepository.findById(postId).map(post -> {
            comments.setPost(post);
            return commentRepository.save(comments);
        }).orElseThrow(()-> new RuntimeException("no post found for postId "+postId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    Comments updateComments(@PathVariable long postId, @PathVariable long commentId, @Valid @RequestBody Comments comments)
    {
        return commentRepository.findByIdAndPostId(commentId,postId).map(comment->{
            comment.setText(comments.getText());
            return commentRepository.save(comment);
        }).orElseThrow(()->new RuntimeException("commentId " + commentId+" not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    ResponseEntity<?> deleteComments(@PathVariable long postId, @PathVariable long commentId)
    {
        return commentRepository.findByIdAndPostId(commentId,postId).map(comments -> {
            commentRepository.delete(comments);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new RuntimeException("comment not found for given postId "+postId+" and commentId "+commentId));
    }
}
