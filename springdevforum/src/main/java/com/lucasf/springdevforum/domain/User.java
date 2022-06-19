package com.lucasf.springdevforum.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "user")
public class User {
    @Id @EqualsAndHashCode.Include private String id;
    @NonNull private String firstname;
    @NonNull private String lastname;
    @NonNull private String email;
    @NonNull private String password;

    //@DBRef(lazy = true)
    //private List<Post> posts = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
}
