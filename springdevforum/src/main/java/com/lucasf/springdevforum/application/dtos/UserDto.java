package com.lucasf.springdevforum.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.domain.valueObjects.Email;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class UserDto {
    @NotNull private String id;
    @NotNull private String firstname;
    @NotNull private String lastname;
    @NotNull private String email;
    @NotNull private String cpf;
    @NotNull private String phone;
    @NotNull @JsonProperty("created_at")
    private Date createdAt;
    @NotNull @JsonProperty("updated_at")
    private Date updatedAt;

    public UserDto(User user){
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.phone = user.getPhone().toString();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
