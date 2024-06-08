package com.book.store.services;


import com.book.store.dto.AuthorDTO;

public interface AuthorService {
    AuthorDTO saveAuthor(AuthorDTO authorToSave);

    }
