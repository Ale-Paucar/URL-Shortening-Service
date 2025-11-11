package com.alepaucar.urlshortener.url_shortener_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OriginalURLRequestDTO {
    @NotBlank(message = "The URL no be empty")
    @URL(message = "The URL isn't valid")
    private String url;
}
