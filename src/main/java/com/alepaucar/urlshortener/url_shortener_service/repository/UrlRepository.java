package com.alepaucar.urlshortener.url_shortener_service.repository;

import com.alepaucar.urlshortener.url_shortener_service.entity.OriginalURL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<OriginalURL, UUID> {
    Optional<OriginalURL> findByShortCode(String shortCode);
}
