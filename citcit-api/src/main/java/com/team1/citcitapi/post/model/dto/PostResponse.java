package com.team1.citcitapi.post.model.dto;

import java.sql.Timestamp;

import com.team1.citcitapi.user.model.dto.UserResponse.UserResponsePost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private int id;
    private String title;
    private String description;
    private String image;
    private UserResponsePost createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
