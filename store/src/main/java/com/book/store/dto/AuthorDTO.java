package com.book.store.dto;

import com.book.store.models.Book;
import lombok.Builder;

import java.util.Set;

@Builder
public record AuthorDTO(String name, Set<Book> books) {
}
