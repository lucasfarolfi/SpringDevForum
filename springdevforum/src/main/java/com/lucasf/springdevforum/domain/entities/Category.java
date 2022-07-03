package com.lucasf.springdevforum.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "category")
public class Category {
    @Id @EqualsAndHashCode.Include private String id;
    @NonNull private String name;

    @Setter(AccessLevel.NONE) @JsonProperty("created_at")
    private Date createdAt = new Date();
    @JsonProperty("updated_at")
    private Date updatedAt = new Date();
}
