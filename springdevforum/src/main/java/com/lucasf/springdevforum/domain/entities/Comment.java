package com.lucasf.springdevforum.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasf.springdevforum.application.dtos.AuthorDto;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Comment {
    @NonNull private String text;
    @NonNull private AuthorDto author;

    @Setter(AccessLevel.NONE) @JsonProperty("created_at")
    private Date createdAt = new Date();
    @JsonProperty("updated_at")
    private Date updatedAt = new Date();
}
