package com.book.store.mappers;

import com.book.store.dto.PublisherDTO;
import com.book.store.models.Publisher;

public abstract class PublisherMapper {

    public static Publisher publisherDTOToEntity(PublisherDTO publisherDTO) {
        return Publisher.builder()
                .name(publisherDTO.name())
                .build();
    }

    public static PublisherDTO publisherToPublisherDTO(Publisher publisher) {
        return PublisherDTO.builder()
                .name(publisher.getName())
                .build();
    }
}
