package com.book.store.dto;

import com.book.store.models.Author;
import com.book.store.models.Publisher;
import com.book.store.models.Review;
import lombok.Builder;

import java.util.Set;

@Builder
public record BookDTO(String title, Review review, Set<Author> authors, Publisher publisher) {
}
