package com.lucasf.springdevforum.application.dtos;

import com.lucasf.springdevforum.domain.entities.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class AuthorDto {
    @NotNull private String id;
    @NotNull private String firstname;
    @NotNull private String lastname;

    public AuthorDto(User user){
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }
}
