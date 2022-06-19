package com.lucasf.springdevforum.dtos;

import com.lucasf.springdevforum.domain.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class UserDto {
    @NotNull private String id;
    @NotNull private String firstname;
    @NotNull private String lastname;
    @NotNull private String email;
    @NotNull private Date createdAt;
    @NotNull private Date updatedAt;

    public UserDto(User user){
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
