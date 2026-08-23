package com.learning.urlshortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings")
@Getter
@Setter
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    // The short code, e.g. "aZ3xQ1". Unique because two URLs can never share one.
    @Column(nullable = false, unique = true, length = 20)
    private String shortCode;

    private LocalDateTime expirationDate; // nullable -- null means "never expires"

    @Column(nullable = false)
    private long clickCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return expirationDate != null && expirationDate.isBefore(LocalDateTime.now());
    }
}
