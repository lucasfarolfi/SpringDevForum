package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class CpfUnitTest {

    @Test
    @DisplayName("Given a valid cpf, When create CPF value object, Then return a new CPF object correctly")
    public void test1(){
        String cpf = "123.123.123-12";

        Cpf result = new Cpf(cpf);

        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(cpf);
    }

    @Test
    @DisplayName("Given a invalid cpf, When create CPF value object, Then InvalidFormDataException is thrown")
    public void test2(){
        String cpf = "12312312312";

        Throwable result = catchThrowable(() -> new Cpf(cpf));

        assertThat(result).isInstanceOf(InvalidFormDataException.class).hasMessage("Cpf is not valid");
    }
}