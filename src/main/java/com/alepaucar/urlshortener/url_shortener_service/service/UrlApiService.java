package com.alepaucar.urlshortener.url_shortener_service.service;

import com.alepaucar.urlshortener.url_shortener_service.dto.OriginURLAccessCountDTO;
import com.alepaucar.urlshortener.url_shortener_service.dto.OriginalURLRequestDTO;
import com.alepaucar.urlshortener.url_shortener_service.dto.OriginalURLResponseDTO;
import com.alepaucar.urlshortener.url_shortener_service.entity.OriginalURL;

import com.alepaucar.urlshortener.url_shortener_service.exceptions.NotFoundException;
import com.alepaucar.urlshortener.url_shortener_service.handler.HashHandler;
import com.alepaucar.urlshortener.url_shortener_service.repository.UrlRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlApiService {
    private static final int MAX_ATTEMPTS = 5;
    private final UrlRepository urlRepository;

    public UrlApiService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public OriginalURLResponseDTO addNewUrl(OriginalURLRequestDTO originalURLRequestDTO) {
        OriginalURL url = new OriginalURL();
        url.setUrl(originalURLRequestDTO.getUrl());
        url.setAccessCount(0);
        String shortURL = null;
        int attempts = 0;
        while (attempts<MAX_ATTEMPTS){
            attempts++;
            shortURL = HashHandler.generateNewShortCode();
            url.setShortCode(shortURL);
            try{
                urlRepository.save(url);
                return toResponseDTO(url);
            }catch (DataIntegrityViolationException e){
                System.err.println("Duplicate code, trying again...");
                url.setId(null);
            }
        }
        throw new IllegalStateException("Could not generate a unique shortCode after " + MAX_ATTEMPTS + " attempts");
    }

    public OriginalURLResponseDTO getURLFromShortCode(String code) {
        OriginalURL url = urlRepository.findByShortCode(code).orElseThrow(() -> new NotFoundException("Id not found"));
        return toResponseDTO(url);
    }

    public OriginalURLResponseDTO updateURLFromShortCode(String code, OriginalURLRequestDTO originalURLRequestDTO) {
        OriginalURL url = urlRepository.findByShortCode(code).orElseThrow(() -> new NotFoundException("Code not found"));
        url.setUrl(originalURLRequestDTO.getUrl());
        url.setUpdatedAt(LocalDateTime.now());
        urlRepository.save(url);

        return toResponseDTO(url);
    }
    //this mapper helps with redundant code
    private OriginalURLResponseDTO toResponseDTO(OriginalURL url){
        return OriginalURLResponseDTO.builder()
                .id(url.getId())
                .url(url.getUrl())
                .shortCode(url.getShortCode())
                .updatedAt(url.getUpdatedAt())
                .createdAt(url.getCreatedAt())
                .build();
    }

    public void deleteURLFromShortCode(String code) {
        OriginalURL url = urlRepository.findByShortCode(code).orElseThrow(() -> new NotFoundException("Code not found"));
        urlRepository.deleteById(url.getId());
    }

    public OriginURLAccessCountDTO getURLStatsFromShortCode(String code) {
        OriginalURL url = urlRepository.findByShortCode(code).orElseThrow(() -> new NotFoundException("Code not found"));
        return OriginURLAccessCountDTO.builder()
                .id(url.getId())
                .url(url.getUrl())
                .shortCode(url.getShortCode())
                .updatedAt(url.getUpdatedAt())
                .createdAt(url.getCreatedAt())
                .accessCount(url.getAccessCount())
                .build();
    }

    public void increaseAccessCount(UUID id) {
        OriginalURL url = urlRepository.findById(id).orElseThrow(() -> new NotFoundException("Code not found"));
        url.setAccessCount(url.getAccessCount()+1);
        urlRepository.save(url);
    }
}
