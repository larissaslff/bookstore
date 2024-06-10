package com.book.store.services;

import com.book.store.dto.PublisherDTO;
import com.book.store.mappers.PublisherMapper;
import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.book.store.mappers.PublisherMapper.publisherDTOToEntity;
import static com.book.store.mappers.PublisherMapper.publisherToPublisherDTO;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;

    @Override
    public PublisherDTO savePublisher(PublisherDTO publisherDTO) {
        Optional<Publisher> optionalPublisher = publisherRepository.findByName(publisherDTO.name());
        if (optionalPublisher.isEmpty()) {
            Publisher publisherToSave = publisherDTOToEntity(publisherDTO);
            Publisher saved = publisherRepository.save(publisherToSave);
            return publisherToPublisherDTO(saved);
        }

        throw new RuntimeException("Already exists a publisher with this name");
    }

    @Override
    public Optional<PublisherDTO> findPublisherByName(String name) {
        return publisherRepository.findByName(name).map(PublisherMapper::publisherToPublisherDTO);
    }
}
