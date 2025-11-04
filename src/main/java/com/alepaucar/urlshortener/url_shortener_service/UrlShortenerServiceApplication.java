package com.alepaucar.urlshortener.url_shortener_service;

import com.alepaucar.urlshortener.url_shortener_service.entity.OriginalURL;
import com.alepaucar.urlshortener.url_shortener_service.handler.HashHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerServiceApplication.class, args);
	}

}
