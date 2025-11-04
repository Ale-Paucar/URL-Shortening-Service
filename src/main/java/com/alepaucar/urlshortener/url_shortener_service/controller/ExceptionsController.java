package com.alepaucar.urlshortener.url_shortener_service.controller;

import com.alepaucar.urlshortener.url_shortener_service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExpectionsController (NotFoundException e ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
