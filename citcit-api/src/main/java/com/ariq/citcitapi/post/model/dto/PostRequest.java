package com.ariq.citcitapi.post.model.dto;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.user.model.dto.UserRequest.UserRequestPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String description;
    private String image;
    private UserRequestPost createdBy;

    public Post convertToEntity() {
        return Post.builder()
                .title(this.title)
                .description(this.description)
                .image(this.image)
                .createdBy(this.createdBy.convertToEntity())
                .build();
    }
}
