package com.book.store.services;

import com.book.store.dto.AuthorDTO;
import com.book.store.models.Author;
import com.book.store.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void shouldSaveAnAuthor() {
        AuthorDTO authorToSave = AuthorDTO.builder()
                .name("Shakespeare")
                .build();

        Author savedAuthor = Author.builder()
                .id(UUID.randomUUID())
                .name("Shakespeare")
                .build();

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorDTO savedAuthorDTO = authorService.saveAuthor(authorToSave);

        verify(authorRepository, times(1)).save(any(Author.class));
        assertNotNull(savedAuthorDTO);
        assertEquals(authorToSave.name(), savedAuthorDTO.name());
    }

    @Test
    void shouldFindAnExistingAuthorByName(){
        when(authorRepository.findByName(anyString())).thenReturn(Optional.of(Author.builder().id(UUID.randomUUID()).name("name").build()));

        boolean authorsExist = authorService.findAuthorsByName("name");

        verify(authorRepository, times(1)).findByName(anyString());
        assertTrue(authorsExist);
    }

    @Test
    void shouldNotFindAnAuthorBecauseIsNotSavedYet(){
        when(authorRepository.findByName(anyString())).thenReturn(Optional.empty());

        boolean authorsExist = authorService.findAuthorsByName("name");

        verify(authorRepository, times(1)).findByName(anyString());
        assertFalse(authorsExist);
    }

}