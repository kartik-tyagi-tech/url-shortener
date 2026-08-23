package com.learning.urlshortener.controller;

import com.learning.urlshortener.dto.CreateUrlRequest;
import com.learning.urlshortener.dto.UpdateUrlRequest;
import com.learning.urlshortener.dto.UrlResponse;
import com.learning.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@Valid @RequestBody CreateUrlRequest request) {
        UrlResponse response = urlService.createShortUrl(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Example: GET /api/urls?page=0&size=10&search=github
    @GetMapping
    public ResponseEntity<Page<UrlResponse>> listMyUrls(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(urlService.listUrls(search, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UrlResponse> updateUrl(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateUrlRequest request) {
        return ResponseEntity.ok(urlService.updateUrl(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
        urlService.deleteUrl(id);
        return ResponseEntity.noContent().build();
    }
}
