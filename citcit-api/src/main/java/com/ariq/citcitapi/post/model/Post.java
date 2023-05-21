package com.ariq.citcitapi.post.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ariq.citcitapi.post.model.dto.PostResponse;
import com.ariq.citcitapi.user.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String title;
    
    private String description;
    
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser createdBy;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public PostResponse convertToResponse() {
        return PostResponse.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .image(this.image)
                .createdBy(this.createdBy.convertToResponsePost())
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
