package com.ariq.citcitapi.user.model;

import java.util.List;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.user.model.dto.UserResponse.UserResponse;
import com.ariq.citcitapi.user.model.dto.UserResponse.UserResponsePost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "createdBy")
    private List<Post> posts;

    public UserResponse convertToResponse() {
        return UserResponse.builder().id(this.id).name(this.name).username(this.username).email(this.email).posts(this.posts).build();
    }

    public UserResponsePost convertToResponsePost() {
        return UserResponsePost.builder().id(this.id).username(this.username).build();
    }
    
}
