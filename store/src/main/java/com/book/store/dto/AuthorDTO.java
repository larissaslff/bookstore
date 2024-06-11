package com.book.store.dto;

import com.book.store.models.Book;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record AuthorDTO(UUID id, String name, Set<Book> books) {
}
