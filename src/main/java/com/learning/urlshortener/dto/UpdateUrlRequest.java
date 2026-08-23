package com.learning.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUrlRequest {

    @NotBlank(message = "Original URL is required")
    private String originalUrl;
}
