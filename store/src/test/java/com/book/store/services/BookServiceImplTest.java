package com.book.store.services;

import com.book.store.dto.BookDTO;
import com.book.store.models.Author;
import com.book.store.models.Book;
import com.book.store.models.Publisher;
import com.book.store.models.Review;
import com.book.store.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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

    private Set<Author> authors;
    private Publisher publisher;
    private BookDTO bookDTOToSave;
    private BookDTO savedBookDTO;
    private Book aSavedBook;

    @BeforeEach
    void setUp() {
        authors = Set.of(
                Author.builder().id(UUID.randomUUID()).name("Jonathan Knudsen").build(),
                Author.builder().id(UUID.randomUUID()).name("Patrick Niemeyer").build()
        );
        publisher = Publisher.builder().id(UUID.randomUUID()).name("O'Reilly Media").build();

        bookDTOToSave = BookDTO.builder()
                .title("Learning Java")
                .authors(authors)
                .publisher(publisher)
                .review(Review.builder().comment("A great book").build())
                .build();

        savedBookDTO = BookDTO.builder()
                .title("Learning Java")
                .authors(authors)
                .publisher(publisher)
                .review(Review.builder().comment("A great book").build())
                .build();

        aSavedBook = Book.builder()
                .title("Learning Java")
                .authors(authors)
                .publisher(publisher)
                .build();
    }

    @Test
    void shouldSaveAnBook() {
        BookDTO bookDTO = new BookDTO("Learning Java", null, authors, publisher);

        when(bookRepository.save(any(Book.class))).thenReturn(aSavedBook);

        BookDTO newBook = bookService.saveBook(bookDTO);

        verify(bookRepository, times(1)).save(any(Book.class));
        assertNotNull(newBook);
        assertEquals(newBook.title(), bookDTO.title());
        assertEquals(newBook.authors().size(), bookDTO.authors().size());
        assertEquals(newBook.publisher().getName(), bookDTO.publisher().getName());
    }

    @Test
    void shouldReturnAllBooksSaved() {
        List<Book> books = List.of(aSavedBook);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> allSavedBook = bookService.getAllBook();

        assertNotNull(allSavedBook);
        assertThat(allSavedBook).hasSize(1);
        assertThat(allSavedBook.get(0).authors()).hasSize(2);
        assertEquals(aSavedBook.getTitle(), allSavedBook.get(0).title());
        assertEquals(aSavedBook.getPublisher().getName(), allSavedBook.get(0).publisher().getName());
    }

}