package com.book.store.services;


import com.book.store.dto.AuthorDTO;

import java.util.Optional;

public interface AuthorService {
    AuthorDTO saveAuthor(AuthorDTO authorToSave);

    Optional<AuthorDTO> findAuthorsByName(String name);
}
