package com.book.store.controllers;

import com.book.store.dto.BookDTO;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
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
                .authors(authors)
                .title(title)
                .publisher(publisher)
                .review(review)
                .build();

        BookDTO savedBook = BookDTO.builder()
                .authors(authors)
                .title(title)
                .publisher(publisher)
                .review(review)
                .build();

        when(bookService.saveBook(any(BookDTO.class))).thenReturn(savedBook);

        mockMvc.perform(post("/books")
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
    void shouldReturnAllBooks() throws Exception {
        when(bookService.getAllBook()).thenReturn(List.of(BookDTO.builder().build(), BookDTO.builder().build()));

        mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


}