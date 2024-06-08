package com.book.store.services;

import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Test
    void shouldSaveAPublisher() {
        //Arrange
        Publisher publisherToSave = Publisher.builder()
                .name("A publisher name")
                .build();

        //Act
        publisherService.savePublisher(publisherToSave);

        //Assert


    }

}