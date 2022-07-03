package com.lucasf.springdevforum.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasf.springdevforum.domain.valueObjects.Cpf;
import com.lucasf.springdevforum.domain.valueObjects.Email;
import com.lucasf.springdevforum.domain.valueObjects.Phone;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.util.Date;

@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
@Document(collection = "user")
public class User {
    @Id @EqualsAndHashCode.Include private String id;
    @NonNull private String firstname;
    @NonNull private String lastname;
    @NonNull private Email email;
    @NonNull private Cpf cpf;
    @NonNull @Valid private Phone phone;
    @NonNull private String password;

    //@DBRef(lazy = true)
    //private List<Post> posts = new ArrayList<>();

    @Setter(AccessLevel.NONE) @JsonProperty("created_at")
    private Date createdAt = new Date();
    @JsonProperty("updated_at")
    private Date updatedAt = new Date();

    public String getEmail(){ return email.getAddress(); }
    public String getCpf(){ return cpf.getCode(); }
    public Phone getPhone(){ return phone; }
}
