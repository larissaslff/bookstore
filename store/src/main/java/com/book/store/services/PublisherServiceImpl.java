package com.book.store.services;

import com.book.store.dto.PublisherDTO;
import com.book.store.mappers.PublisherMapper;
import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.book.store.mappers.PublisherMapper.publisherDTOToEntity;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;

    @Override
    public PublisherDTO savePublisher(PublisherDTO publisherDTO) {
        Publisher publisherToSave = publisherDTOToEntity(publisherDTO);
        Publisher saved = publisherRepository.save(publisherToSave);
        return PublisherDTO.builder().name(saved.getName()).build();
    }

    @Override
    public Optional<PublisherDTO> findPublisherByName(String name) {
        return publisherRepository.findByName(name).map(PublisherMapper::publisherToPublisherDTO);
    }
}
