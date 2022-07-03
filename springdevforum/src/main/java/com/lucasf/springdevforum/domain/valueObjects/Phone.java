package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter @EqualsAndHashCode
public class Phone {
    private final int ddd;
    private final String number;

    public Phone(
            @NotNull @Length(min = 2, max = 2) int ddd,
            @NotNull @Length(min = 9, max = 10) String number)
    {
        this.ddd = ddd;
        this.number = validateNumber(number);
    }

    @Override public String toString(){
        return "(" + ddd + ") " + number;
    }

    private String validateNumber(String number) {
        boolean isValid = number.matches("\\d{5,}[\\-\\s]\\d{4}");
        if(!isValid){
            throw new InvalidFormDataException("Phone number is not valid");
        }
        return number;
    }
}
