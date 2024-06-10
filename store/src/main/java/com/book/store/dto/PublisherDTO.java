package com.book.store.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PublisherDTO(UUID id, String name) {
}
