package com.book.store.services;

import com.book.store.dto.PublisherDTO;
import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Test
    void shouldSaveAPublisher() {
        //Arrange
        PublisherDTO publisherToSave = PublisherDTO.builder()
                .name("A publisher name")
                .build();

        Publisher savedPublisher = Publisher.builder()
                .name("A publisher name")
                .build();

        when(publisherRepository.save(any(Publisher.class))).thenReturn(savedPublisher);

        //Act
        PublisherDTO aNewPublisher = publisherService.savePublisher(publisherToSave);

        //Assert
        verify(publisherRepository, times(1)).save(any(Publisher.class));
        assertNotNull(aNewPublisher);
        assertEquals(publisherToSave.name(), aNewPublisher.name());
    }

}