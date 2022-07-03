package com.lucasf.springdevforum.application.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserFormDto {
    @NotNull private String name;
    @NotNull private String email;
    @NotNull @Length(min = 8)
    private String password;
}
