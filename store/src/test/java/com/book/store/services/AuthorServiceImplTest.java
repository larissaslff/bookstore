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

import static org.assertj.core.api.Assertions.assertThat;
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
    void shouldSaveAnAuthorBecauseDoesNotExistOneAlreadyWithTheSameName() {
        AuthorDTO authorToSave = AuthorDTO.builder()
                .name("Shakespeare")
                .build();

        Author savedAuthor = Author.builder()
                .id(UUID.randomUUID())
                .name("Shakespeare")
                .build();

        when(authorRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorDTO savedAuthorDTO = authorService.saveAuthor(authorToSave);

        verify(authorRepository, times(1)).findByName(anyString());
        verify(authorRepository, times(1)).save(any(Author.class));
        assertNotNull(savedAuthorDTO);
        assertEquals(authorToSave.name(), savedAuthorDTO.name());
    }

    @Test
    void shouldNotSaveAnAuthorBecauseAlreadyExistWithTheSameName() {
        AuthorDTO authorToSave = AuthorDTO.builder()
                .name("Shakespeare")
                .build();

        when(authorRepository.findByName(anyString())).thenReturn(Optional.of(Author.builder().name("Shakespeare").build()));

        RuntimeException exceptionAlreadySavedAuthor = assertThrows(RuntimeException.class, () -> authorService.saveAuthor(authorToSave));

        verify(authorRepository, times(1)).findByName(anyString());
        verify(authorRepository, times(0)).save(any(Author.class));
        assertEquals("Already exists this author in our system", exceptionAlreadySavedAuthor.getMessage());
    }

    @Test
    void shouldFindAnExistingAuthorByNameAndReturnThisAuthor(){
        when(authorRepository.findByName(anyString())).thenReturn(Optional.of(Author.builder().id(UUID.randomUUID()).name("name").build()));

        Optional<AuthorDTO> authorsExist = authorService.findAuthorsByName("name");

        verify(authorRepository, times(1)).findByName(anyString());
        assertNotNull(authorsExist.get().id());
    }

    @Test
    void shouldNotFindAnAuthorBecauseIsNotSavedYet(){
        when(authorRepository.findByName(anyString())).thenReturn(Optional.empty());

        Optional<AuthorDTO> author = authorService.findAuthorsByName("name");

        verify(authorRepository, times(1)).findByName(anyString());
        assertThat(author).isEmpty();
    }

}