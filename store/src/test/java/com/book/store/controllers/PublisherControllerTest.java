package com.book.store.controllers;

import com.book.store.dto.PublisherDTO;
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
class PublisherControllerTest {

    @Mock
    private PublisherServiceImpl publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void shouldAllowSaveAPublisher() throws Exception {
        PublisherDTO publisherToSave = PublisherDTO.builder().name("A publisher name").build();

        PublisherDTO savedPublisher = PublisherDTO.builder()
                .name("A publisher name")
                .build();

        when(publisherService.savePublisher(any(PublisherDTO.class))).thenReturn(savedPublisher);

        mockMvc.perform(post("/publishers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(publisherToSave)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("A publisher name")));

    }
}