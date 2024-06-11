package com.book.store.services;

import com.book.store.dto.AuthorDTO;
import com.book.store.models.Author;
import com.book.store.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import static com.book.store.mappers.AuthorMapper.toAuthorDTO;
import static com.book.store.mappers.AuthorMapper.toAuthorEntity;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    private AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author saved = authorRepository.save(toAuthorEntity(authorDTO));
        return toAuthorDTO(saved);
    }

    @Override
    public boolean findAuthorsByName(String name) {
        return false;
    }
}
