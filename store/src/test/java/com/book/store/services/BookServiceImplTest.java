package com.book.store.services;

import com.book.store.dto.BookDTO;
import com.book.store.models.Author;
import com.book.store.models.Book;
import com.book.store.models.Publisher;
import com.book.store.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository  bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;


    @Test
    void shouldSaveAnBook() {
        Set<Author> authors = Set.of(Author.builder().id(UUID.randomUUID()).name("Jonathan Knudsen").build(),
                Author.builder().id(UUID.randomUUID()).name("Patrick Niemeyer").build());
        Publisher publisher = Publisher.builder().id(UUID.randomUUID()).name("O'Reilly Media").build();
        Book bookSaved = Book.builder()
                .title("Learning Java")
                .authors(authors)
                .publisher(publisher)
                .build();
        BookDTO bookDTO = new BookDTO("Learning Java", null, authors, publisher);

        when(bookRepository.save(any(Book.class))).thenReturn(bookSaved);

        BookDTO newBook = bookService.saveBook(bookDTO);

        verify(bookRepository, times(1)).save(any(Book.class));
        assertNotNull(newBook);
        assertEquals(newBook.title(), bookDTO.title());
        assertEquals(newBook.authors().size(), bookDTO.authors().size());
        assertEquals(newBook.publisher().getName(), bookDTO.publisher().getName());
    }

}