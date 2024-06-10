package com.book.store.mappers;

import com.book.store.dto.PublisherDTO;
import com.book.store.models.Publisher;

public abstract class PublisherMapper {

    public static Publisher publisherDTOToEntity(PublisherDTO publisherDTO) {
        return Publisher.builder()
                .id(publisherDTO.id())
                .name(publisherDTO.name())
                .build();
    }

    public static PublisherDTO publisherToPublisherDTO(Publisher publisher) {
        return PublisherDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }
}
