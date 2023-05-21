package com.ariq.citcitapi.post;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.post.model.dto.PostRequest;
import com.ariq.citcitapi.post.model.dto.PostResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getAllPosts(
            @RequestParam(name = "title", required = false) Optional<String> title,
            @RequestParam(name = "createdBy", required = false) Optional<String> createdBy) {

                List<Post> posts = this.postService.getAll(title);
                List<PostResponse> postResponses = posts.stream().map(post -> post.convertToResponse()).toList();

                return ResponseEntity.ok().body(postResponses);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> getOnePost(@PathVariable("id") int id) {
        Optional<Post> post = this.postService.getOne(id);

        if (post.isPresent()) {
            PostResponse postResponse = post.get().convertToResponse();
            return ResponseEntity.ok().body(postResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createOnePost(@Valid @RequestBody PostRequest postRequest) {

        Post newPost = postRequest.convertToEntity();
        Post savePost = this.postService.createOne(newPost);
        PostResponse postResponse = savePost.convertToResponse();

        return ResponseEntity.created(null).body(postResponse);
    }
}
