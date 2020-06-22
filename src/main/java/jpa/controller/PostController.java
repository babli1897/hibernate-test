package jpa.controller;

import jpa.model.Post;
import jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    Page<Post> getAllPost(Pageable pageable)
    {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    Post createPost(@Valid  @RequestBody Post post)
    {
        return postRepository.save(post);
    }

    @PutMapping("/update/{postId}")
    Post updatePost(@PathVariable long postId, @Valid @RequestBody Post reqPost)
    {

        return postRepository.findById(postId).map(post->{
            post.setContent(reqPost.getContent());
            post.setDescription(reqPost.getDescription());
            post.setTitle(reqPost.getTitle());
            return postRepository.save(post);
        }).orElseThrow(()->new RuntimeException("postId " +postId+" not found"));
    }

    @DeleteMapping("/posts/{postId}")
    ResponseEntity<?> deletePost(@PathVariable Long postId)
    {
        return postRepository.findById(postId).map(post -> {postRepository.delete(post);
        return ResponseEntity.ok().build();}).orElseThrow(()->new RuntimeException("postId " +postId+" not found"));

    }
}
