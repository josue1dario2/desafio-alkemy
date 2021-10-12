package com.challenge.alkemy.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@Getter
public class SpringException extends Exception{

    private HttpStatus status;

    public SpringException(String msg){
        super(msg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public SpringException(String msg, HttpStatus status){
        super(msg);
        this.status = status;
    }


}
