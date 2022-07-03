package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class PhoneUnitTest {
    @Test
    @DisplayName("Given a valid Phone, When create Phone value object, Then return a new Phone object correctly")
    public void test1(){
        int ddd = 12;
        String number = "12345-6789";

        Phone result = new Phone(ddd, number);

        assertThat(result).isNotNull();
        assertThat(result.getDdd()).isEqualTo(ddd);
        assertThat(result.getNumber()).isEqualTo(number);
    }

    @Test
    @DisplayName("Given a invalid phone number, When create Phone value object, Then InvalidFormDataException is thrown")
    public void test2(){
        int ddd = 12;
        String number = "123456789";

        Throwable throwable = catchThrowable(() -> new Phone(ddd, number));

        assertThat(throwable).isInstanceOf(InvalidFormDataException.class).hasMessage("Phone number is not valid");
    }
}