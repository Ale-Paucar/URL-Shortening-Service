package com.alepaucar.urlshortener.url_shortener_service.handler;

public class GenerateShortUrlHandler {
    private String shortenedUrl;

    private static final String DOMAIN = "https://www.example.com/";

    public static String generateShortUrl(){
        String code = HashHandler.generateNewShortCode();
        //Save de code for future purposes
        return DOMAIN + code;
    }

}
