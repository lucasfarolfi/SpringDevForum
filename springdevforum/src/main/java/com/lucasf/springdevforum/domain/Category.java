package com.lucasf.springdevforum.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "category")
public class Category {
    @Id @EqualsAndHashCode.Include private String id;
    @NonNull private String name;

    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
}
