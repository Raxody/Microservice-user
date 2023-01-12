package org.springcloud.msvc.courses.model;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidatorParam {

    private ValidatorParam() {}

    public static ResponseEntity<Map<String, String>> getErrors(BindingResult bindingResult) {
        Map<String,String>  errors = new HashMap<> ();
        if(bindingResult.hasErrors()){
            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError ->
                            errors.put(fieldError.getField(),
                                     fieldError.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(errors);
    }



}

