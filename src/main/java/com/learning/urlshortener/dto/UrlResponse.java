package com.learning.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UrlResponse {
    private Long id;
    private String originalUrl;
    private String shortCode;
    private String shortUrl;       // full clickable link, e.g. http://localhost:8080/r/aZ3
    private LocalDateTime expirationDate;
    private long clickCount;
    private LocalDateTime createdAt;
}
