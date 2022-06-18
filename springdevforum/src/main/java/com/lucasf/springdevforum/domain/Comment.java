package com.lucasf.springdevforum.domain;

import com.lucasf.springdevforum.dtos.AuthorDto;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Comment {
    @NonNull private String text;
    @NonNull private AuthorDto author;

    @Setter(AccessLevel.NONE)
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
}
