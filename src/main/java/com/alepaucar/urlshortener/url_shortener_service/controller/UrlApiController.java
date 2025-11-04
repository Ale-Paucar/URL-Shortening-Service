package com.alepaucar.urlshortener.url_shortener_service.controller;

import com.alepaucar.urlshortener.url_shortener_service.dto.OriginURLAccessCountDTO;
import com.alepaucar.urlshortener.url_shortener_service.dto.OriginalURLRequestDTO;
import com.alepaucar.urlshortener.url_shortener_service.dto.OriginalURLResponseDTO;
import com.alepaucar.urlshortener.url_shortener_service.service.UrlApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlApiController {

    private final UrlApiService urlApiService;

    @Autowired
    public UrlApiController(UrlApiService urlApiService) {
        this.urlApiService = urlApiService;
    }


    @GetMapping("/shorten/{code}")
    public ResponseEntity<OriginalURLResponseDTO> getOriginalUrl(@PathVariable String code) {
        OriginalURLResponseDTO responseDTO = urlApiService.getURLFromShortCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/shorten/{code}/stats")
    public ResponseEntity<OriginURLAccessCountDTO> getOriginalUrlStats(@PathVariable String code) {
        OriginURLAccessCountDTO responseDTO = urlApiService.getURLStatsFromShortCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //redirect
    @GetMapping("/{code}")
    public ResponseEntity<Void> getAndRedirectOriginalUrl(@PathVariable String code) {
        OriginalURLResponseDTO responseDTO = urlApiService.getURLFromShortCode(code);
        urlApiService.increaseAccessCount(responseDTO.getId());
        String originalUrl = responseDTO.getUrl();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    @PostMapping("/shorten")
    public ResponseEntity<OriginalURLResponseDTO> postOriginalUrl(@Valid @RequestBody  OriginalURLRequestDTO originalURLRequestDTO) {
        OriginalURLResponseDTO responseDTO = urlApiService.addNewUrl(originalURLRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/shorten/{code}")
    public ResponseEntity<OriginalURLResponseDTO> putOriginalUrl(@PathVariable String code, @Valid @RequestBody  OriginalURLRequestDTO originalURLRequestDTO) {
        OriginalURLResponseDTO responseDTO = urlApiService.updateURLFromShortCode(code,originalURLRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/shorten/{code}")
    public ResponseEntity<?> deleteOriginalUrl(@PathVariable String code) {
        urlApiService.deleteURLFromShortCode(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
