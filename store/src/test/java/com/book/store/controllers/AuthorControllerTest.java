package com.book.store.controllers;

import com.book.store.dto.AuthorDTO;
import com.book.store.dto.PublisherDTO;
import com.book.store.services.AuthorServiceImpl;
import com.book.store.services.PublisherServiceImpl;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {
    @Mock
    private AuthorServiceImpl authorService;

    @InjectMocks
    private AuthorController authorController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void shouldAllowSaveAAuthor() throws Exception {
        AuthorDTO authorDTOToSave = AuthorDTO.builder().name("Shakespeare").build();

        AuthorDTO savedAuthor = AuthorDTO.builder()
                .name("Shakespeare")
                .build();

        when(authorService.saveAuthor(any(AuthorDTO.class))).thenReturn(savedAuthor);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDTOToSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("A publisher name")));

    }

}