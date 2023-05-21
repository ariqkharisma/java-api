package com.ariq.citcitapi.post;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.user.UserRepository;
import com.ariq.citcitapi.user.model.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getAll(Optional<String> title) {
        if (title.isPresent()) {
            List<Post> postsFilter = this.postRepository.findByTitleContainingIgnoreCase(title.get());
            return postsFilter;
        }

        return this.postRepository.findAll();
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
