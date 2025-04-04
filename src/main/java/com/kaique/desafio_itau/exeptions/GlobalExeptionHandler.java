package com.kaique.desafio_itau.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleUnprocessableEntity(UnprocessableEntity e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro:  " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleUnprocessableEntity(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:  " + e.getMessage());
    }

}
