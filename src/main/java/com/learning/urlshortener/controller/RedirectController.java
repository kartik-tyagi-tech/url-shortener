package com.learning.urlshortener.controller;

import com.learning.urlshortener.entity.UrlMapping;
import com.learning.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

// Separate, PUBLIC controller (no auth required — see SecurityConfig's "/r/**" permitAll).
// This is the endpoint people actually click on.
@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlService urlService;

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        UrlMapping urlMapping = urlService.getByShortCodeAndIncrementClicks(shortCode);

        // HTTP 302 Found + Location header = "browser, go fetch this URL instead."
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, urlMapping.getOriginalUrl())
                .build();
    }
}
