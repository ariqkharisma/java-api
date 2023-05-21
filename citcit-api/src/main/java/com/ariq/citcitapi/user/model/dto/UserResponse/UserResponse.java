package com.ariq.citcitapi.user.model.dto.UserResponse;

import java.util.List;

import com.ariq.citcitapi.post.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private String username;
    private String email;
    private List<Post> posts;
}
