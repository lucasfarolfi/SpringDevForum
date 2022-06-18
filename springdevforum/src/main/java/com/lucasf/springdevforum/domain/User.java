package com.lucasf.springdevforum.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Document
public class User {
    @Id
    private String Id;
    private String name;
    private String email;
    private String password;
}
