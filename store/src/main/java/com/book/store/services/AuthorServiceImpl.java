package com.book.store.services;

import com.book.store.dto.AuthorDTO;
import com.book.store.models.Author;
import com.book.store.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import static com.book.store.mappers.AuthorMapper.*;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    private AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = toAuthorEntity(authorDTO);
        Author saved = authorRepository.save(author);
        return toAuthorDTO(saved);
    }
}
