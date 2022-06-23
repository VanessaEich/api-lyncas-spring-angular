package net.lyncas.apilyncas.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vanessa Eich
 * @created 12/05/2022
 */
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }
}
