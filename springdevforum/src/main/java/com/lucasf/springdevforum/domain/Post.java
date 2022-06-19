package com.lucasf.springdevforum.domain;

import com.lucasf.springdevforum.dtos.AuthorDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "post")
public class Post {
    @Id @EqualsAndHashCode.Include private String id;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private PostStatus status;
    @NonNull private AuthorDto author;

    private List<Category> categories = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
}
