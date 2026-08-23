package com.learning.urlshortener.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUrlRequest {

    @NotBlank(message = "Original URL is required")
    private String originalUrl;

    // Optional — if provided, we use it instead of auto-generating a short code.
    private String customAlias;

    // Optional — if provided, must be in the future. Null means "never expires".
    @Future(message = "Expiration date must be in the future")
    private LocalDateTime expirationDate;
}
