package com.book.store.repositories;

import com.book.store.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Author findByName(String authorName);
}
