package com.team1.citcitapi.post;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team1.citcitapi.post.model.Post;
import com.team1.citcitapi.user.UserRepository;
import com.team1.citcitapi.user.model.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Page<Post> getAll(Optional<String> title, Optional<String> username, Pageable pageable) {

        if (username.isPresent()) {
            return this.postRepository.findByUsername(username.get(), pageable);
        }

        if (title.isPresent()) {
            return this.postRepository.findByTitleContainingIgnoreCase(title.get(), pageable);
           
        }

        return this.postRepository.findAll(pageable);
    }

    public Optional<Post> getOne(int id) {
        return this.postRepository.findById(id);
    }

    public Post createOne(Post post) {
        AppUser user = post.getCreatedBy();
        AppUser findUser = this.userRepository.findByUsername(user.getUsername()).get();
    
        post.setCreatedBy(findUser);
        return this.postRepository.save(post);
    }

}
