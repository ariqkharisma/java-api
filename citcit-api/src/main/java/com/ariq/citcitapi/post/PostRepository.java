package com.ariq.citcitapi.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariq.citcitapi.post.model.Post;




public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleContainingIgnoreCase(String title);
}
