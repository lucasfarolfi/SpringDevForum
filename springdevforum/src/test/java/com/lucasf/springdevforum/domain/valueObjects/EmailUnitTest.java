package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class EmailUnitTest {

    @Test
    @DisplayName("Given a valid Email, When create Email value object, Then return a new Email object correctly")
    public void test1(){
        String email = "email@email.com";

        Email result = new Email(email);

        assertThat(result).isNotNull();
        assertThat(result.getAddress()).isEqualTo(email);
    }

    @Test
    @DisplayName("Given a invalid Email, When create Email value object, Then InvalidFormDataException is thrown")
    public void test2(){
        String email = "email@email.";

        Throwable throwable = catchThrowable(() -> new Email(email));

        assertThat(throwable).isInstanceOf(InvalidFormDataException.class).hasMessage("Email is not valid");
    }
}