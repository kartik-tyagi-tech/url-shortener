package com.learning.urlshortener.repository;

import com.learning.urlshortener.entity.UrlMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    // "Containing" generates a SQL LIKE %text% query. "IgnoreCase" makes it case-insensitive.
    // Pageable lets Spring Data JPA auto-handle LIMIT/OFFSET for us -- this is our pagination.
    Page<UrlMapping> findByOriginalUrlContainingIgnoreCase(String searchText, Pageable pageable);
}
