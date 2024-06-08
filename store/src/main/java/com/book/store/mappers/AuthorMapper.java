package com.book.store.mappers;

import com.book.store.dto.AuthorDTO;
import com.book.store.models.Author;

public abstract class AuthorMapper {

    public static Author toAuthorEntity(AuthorDTO authorDTO) {
        return Author.builder()
                .name(authorDTO.name())
                .build();
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        return AuthorDTO.builder()
                .name(author.getName())
                .build();
    }
}
