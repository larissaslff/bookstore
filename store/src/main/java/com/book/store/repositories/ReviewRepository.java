package com.book.store.repositories;

import com.book.store.models.Author;
import com.book.store.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
