package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter @EqualsAndHashCode
public class Cpf {
    private final String code;

    public Cpf(@NotNull String code){
        this.code = validate(code);
    }

    private String validate(String code) {
        boolean isValid = code.matches("^((\\d{3}).(\\d{3}).(\\d{3})-(\\d{2}))*$");
        if(!isValid){
            throw new InvalidFormDataException("Cpf is not valid");
        }

        return code;
    }
}
