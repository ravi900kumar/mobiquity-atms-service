package com.mobiquity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ATMs are not availble for this city.")
public class NoAtmsAvilable extends RuntimeException {

 
    @Override
    public String getMessage() {
         return "ATMs are not availble for this city.";
    }

}