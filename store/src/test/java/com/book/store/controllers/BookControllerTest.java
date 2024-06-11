package com.book.store.controllers;

import com.book.store.dto.BookDTO;
import com.book.store.dto.PublisherDTO;
import com.book.store.mappers.AuthorMapper;
import com.book.store.models.Author;
import com.book.store.models.Publisher;
import com.book.store.models.Review;
import com.book.store.services.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.book.store.mappers.PublisherMapper.publisherToPublisherDTO;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    public static final String URL_BOOKS = "/books";
    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void shouldAllowSaveABook() throws Exception {
        Set<Author> authors = Set.of(Author.builder().id(UUID.randomUUID()).name("Jonathan Knudsen").build(),
                Author.builder().id(UUID.randomUUID()).name("Patrick Niemeyer").build());
        Publisher publisher = Publisher.builder().id(UUID.randomUUID()).name("O'Reilly Media").build();
        Review review = Review.builder().comment("A great book").build();
        String title = "Learning Java";

        BookDTO bookDTOToSave = BookDTO.builder()
                .authors(authors.stream().map(AuthorMapper::toAuthorDTO).collect(Collectors.toSet()))
                .title(title)
                .publisher(publisherToPublisherDTO(publisher))
                .review(review)
                .build();

        BookDTO savedBook = BookDTO.builder()
                .authors(authors.stream().map(AuthorMapper::toAuthorDTO).collect(Collectors.toSet()))
                .title(title)
                .publisher(publisherToPublisherDTO(publisher))
                .review(review)
                .build();

        when(bookService.saveBook(any(BookDTO.class))).thenReturn(savedBook);

        mockMvc.perform(post(URL_BOOKS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTOToSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.publisher.name", is(publisher.getName())))
                .andExpect(jsonPath("$.authors", hasSize(2)))
                .andExpect(jsonPath("$.authors[*].name", containsInAnyOrder("Jonathan Knudsen", "Patrick Niemeyer")))
                .andExpect(jsonPath("$.review.comment", is("A great book")));
    }

    @Test
    void shouldNotSaveABookBecauseAlreadyExistAPublisherWithTheSameName() throws Exception {
        BookDTO bookDTOToSave = BookDTO.builder()
                .authors(null)
                .title("title")
                .publisher(PublisherDTO.builder().build())
                .review(null)
                .build();

        String exceptionPublisherMessage = "Already exists a publisher with this name";

        doThrow(new RuntimeException(exceptionPublisherMessage)).when(bookService).saveBook(any(BookDTO.class));

        mockMvc.perform(post(URL_BOOKS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTOToSave))).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(exceptionPublisherMessage)));
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        when(bookService.getAllBook()).thenReturn(List.of(BookDTO.builder().build(), BookDTO.builder().build()));

        mockMvc.perform(get(URL_BOOKS)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


}