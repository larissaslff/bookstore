package com.book.store.controllers;

import com.book.store.models.Publisher;
import com.book.store.services.PublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

    @Mock
    private PublisherServiceImpl publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void shouldAllowSaveAPublisher() {
        Publisher publisherToSave = Publisher.builder()
                .name("A publisher name")
                .build();
        Publisher savedPublisher = Publisher.builder()
                .id(UUID.randomUUID())
                .name("A publisher name")
                .build();

        when(publisherService.savePublisher(any(Publisher.class))).thenReturn(savedPublisher);

    }
}