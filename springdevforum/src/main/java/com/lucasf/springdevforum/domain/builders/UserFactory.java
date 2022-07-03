package com.lucasf.springdevforum.domain.builders;

import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.domain.valueObjects.Cpf;
import com.lucasf.springdevforum.domain.valueObjects.Email;
import com.lucasf.springdevforum.domain.valueObjects.Phone;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserFactory {
    private User user;

    public UserFactory createUser(
            String firstname, String lastname, String email, String cpf, int ddd,
            String phoneNumber, String password
    ) {
        this.user = new User(firstname, lastname, new Email(email), new Cpf(cpf), new Phone(ddd, phoneNumber), password);
        return this;
    }

    public User get(){ return this.user; }
}
