package com.ariq.citcitapi.post.model.dto;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.user.model.dto.UserRequest.UserRequestPost;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotEmpty
    @Size(min = 5, max = 12, message = "Title should be minimum 5 characters and maximum 12 characters")
    private String title;

    @NotEmpty
    @Size(min = 20, max = 100, message = "Description Should be minimum 20 characters and maximum 100 characters")
    private String description;

    @NotEmpty
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
