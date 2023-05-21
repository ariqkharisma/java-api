package com.team1.citcitapi.post;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.citcitapi.post.model.Post;
import com.team1.citcitapi.post.model.dto.PostRequest;
import com.team1.citcitapi.post.model.dto.PostResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(name = "page", defaultValue = "1") Optional<Integer> optionalPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Optional<Integer> optionalPageSize,
            @RequestParam(name = "title", required = false) Optional<String> title,
            @RequestParam(name = "createdBy", required = false) Optional<String> createdBy) {

        Pageable pageable = PageRequest.of(optionalPage.get() - 1, optionalPageSize.get(),
                Sort.by("createdAt").descending());
        Page<Post> posts = this.postService.getAll(title, createdBy, pageable);
        Page<PostResponse> postResponses = new PageImpl<>(posts.get().map(post -> post.convertToResponse()).toList(),
                pageable, posts.getTotalElements());

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
