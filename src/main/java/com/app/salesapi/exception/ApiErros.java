package com.app.salesapi.exception;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErros {
    private List<String> errors;

    public ApiErros(String msgError) {
        this.errors = Arrays.asList(msgError);
    }
}
