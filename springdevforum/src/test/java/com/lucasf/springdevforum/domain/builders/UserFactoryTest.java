package com.lucasf.springdevforum.domain.builders;

import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.domain.valueObjects.Cpf;
import com.lucasf.springdevforum.domain.valueObjects.Email;
import com.lucasf.springdevforum.domain.valueObjects.Phone;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class UserFactoryTest {

    @Test
    @DisplayName("Given correct user data, When create UserFactory and call get function, must return the user created")
    public void test1(){
        User user = new User("Test", "User", new Email("email@email.com"),
                new Cpf("123.123.123-12"), new Phone(21, "12345-1234"), "12345678"
        );
        UserFactory userFactory = new UserFactory().createUser(user.getFirstname(), user.getLastname(), user.getEmail(),
                user.getCpf(), user.getPhone().getDdd(), user.getPhone().getNumber(), user.getPassword()
        );

        User result = userFactory.get();

        assertThat(result).isEqualTo(user);
    }
}