package com.lucasf.springdevforum.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

    @Setter(AccessLevel.NONE) @JsonProperty("created_at")
    private Date createdAt = new Date();
    @JsonProperty("updated_at")
    private Date updatedAt = new Date();
}
