package com.alepaucar.urlshortener.url_shortener_service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OriginalURLResponseDTO {
    private String url;
    private UUID id;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
