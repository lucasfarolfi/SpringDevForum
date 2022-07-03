package com.lucasf.springdevforum.domain.valueObjects;

import com.lucasf.springdevforum.application.exceptions.InvalidFormDataException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter @EqualsAndHashCode
public class Email {
    private final String address;

    public Email(@NotNull String address){
        this.address = validate(address);
    }

    @Override
    public String toString(){ return address; }

    private String validate(String address) {
        boolean isMatched = address.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        if(!isMatched){
            throw new InvalidFormDataException("Email is not valid");
        }
        return address;
    }
}
