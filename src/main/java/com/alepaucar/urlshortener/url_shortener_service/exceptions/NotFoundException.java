package com.alepaucar.urlshortener.url_shortener_service.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
