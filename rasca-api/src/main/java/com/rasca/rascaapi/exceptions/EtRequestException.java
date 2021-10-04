package com.rasca.rascaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EtRequestException extends RuntimeException{
    public EtRequestException (String message){
        super(message);
    }
}
