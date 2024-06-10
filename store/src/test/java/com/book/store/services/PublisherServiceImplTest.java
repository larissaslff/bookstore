package com.book.store.services;

import com.book.store.dto.PublisherDTO;
import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import org.junit.jupiter.api.BeforeAll;
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
class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    private static Publisher publisher;

    private static PublisherDTO publisherToSave;

    @BeforeAll
    static void setUp() {
        publisher = Publisher.builder()
                .id(UUID.randomUUID())
                .name("A publisher name")
                .build();

        publisherToSave = PublisherDTO.builder()
                .name("A publisher name")
                .build();

    }

    @Test
    void shouldOnlySaveAPublisherWhenDoesNotExistOneWithTheSameName() {
        //Arrange
        when(publisherRepository.findByName(anyString())).thenReturn(Optional.of(publisher));

        //Act
        PublisherDTO savedPublisher = publisherService.savePublisher(publisherToSave);

        //Assert
        verify(publisherRepository, times(1)).findByName(anyString());
        verify(publisherRepository, times(0)).save(any(Publisher.class));
        assertNotNull(savedPublisher);
        assertNotNull(savedPublisher.id());
        assertEquals(publisherToSave.name(), savedPublisher.name());
    }

    @Test
    void shouldSaveAPublisherBecauseDoesNotExistOneWithTheSameName() {
        when(publisherRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);

        PublisherDTO savedPublisherDTO = publisherService.savePublisher(publisherToSave);

        verify(publisherRepository, times(1)).findByName(anyString());
        verify(publisherRepository, times(1)).save(any(Publisher.class));
        assertNotNull(savedPublisherDTO);
        assertNotNull(savedPublisherDTO.id());
        assertEquals(publisherToSave.name(), savedPublisherDTO.name());
    }

    @Test
    void shouldFindAExistingPublisherByName() {
        when(publisherRepository.findByName(anyString())).thenReturn(Optional.of(publisher));

        PublisherDTO foundAPublisher = publisherService.findPublisherByName("A publisher name").get();

        verify(publisherRepository, times(1)).findByName(anyString());
        assertNotNull(foundAPublisher);
    }

    @Test
    void shouldNotFindAnyPublisherByName() {
        when(publisherRepository.findByName(anyString())).thenReturn(Optional.empty());

        Optional<PublisherDTO> optionalPublisherDTO = publisherService.findPublisherByName("A publisher name");

        verify(publisherRepository, times(1)).findByName(anyString());
        assertThat(optionalPublisherDTO).isEmpty();
    }
}