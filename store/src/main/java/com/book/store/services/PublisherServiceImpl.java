package com.book.store.services;

import com.book.store.models.Publisher;
import com.book.store.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;

    @Override
    public void savePublisher(Publisher publisherToSave) {

        publisherRepository.save(null);
    }
}
