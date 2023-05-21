package com.ariq.citcitapi.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ariq.citcitapi.post.model.Post;




public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select p.id, p.title, p.description, p.image, p.user_id, p.created_at, p.updated_at from post p join app_user au on p.user_id = au.id where au.username = ?1", nativeQuery = true)
    List<Post> findByUsername(String username);

    List<Post> findByTitleContainingIgnoreCase(String title);
}
