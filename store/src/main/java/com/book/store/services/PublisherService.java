package com.book.store.services;

import com.book.store.dto.PublisherDTO;

import java.util.Optional;

public interface PublisherService {
    PublisherDTO savePublisher(PublisherDTO publisherToSave);

    Optional<PublisherDTO> findPublisherByName(String name);
}
