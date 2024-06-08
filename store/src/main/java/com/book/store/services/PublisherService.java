package com.book.store.services;

import com.book.store.dto.PublisherDTO;
import com.book.store.models.Publisher;

public interface PublisherService {
    PublisherDTO savePublisher(PublisherDTO publisherToSave);

    }
