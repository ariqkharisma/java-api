package com.team1.citcitapi.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.citcitapi.post.model.Post;




public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select p.id, p.title, p.description, p.image, p.user_id, p.created_at, p.updated_at from post p join app_user au on p.user_id = au.id where au.username = ?1", nativeQuery = true)
    Page<Post> findByUsername(String username, Pageable pageable);

    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
