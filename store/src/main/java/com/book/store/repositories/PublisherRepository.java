package com.book.store.repositories;

import com.book.store.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    Optional<Publisher> findByName(String publisherName);
}
