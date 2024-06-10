package com.book.store.dto;

import com.book.store.models.Review;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record BookDTO(UUID id, String title, Review review, Set<AuthorDTO> authors, PublisherDTO publisher) {
}
