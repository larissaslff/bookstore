package com.book.store.mappers;

import com.book.store.dto.BookDTO;
import com.book.store.models.Book;

import java.util.stream.Collectors;

import static com.book.store.mappers.PublisherMapper.publisherDTOToEntity;
import static com.book.store.mappers.PublisherMapper.publisherToPublisherDTO;

public abstract class BookMapper {
    public static Book bookDTOToEntity(BookDTO bookDTO) {

        return Book.builder()
                .title(bookDTO.title())
                .authors(bookDTO.authors().stream().map(AuthorMapper::toAuthorEntity).collect(Collectors.toSet()))
                .review(bookDTO.review())
                .publisher(publisherDTOToEntity(bookDTO.publisher()))
                .build();
    }

    public static BookDTO bookToBookDTO(Book book) {
        return BookDTO.builder()
                .authors(book.getAuthors().stream().map(AuthorMapper::toAuthorDTO).collect(Collectors.toSet()))
                .title(book.getTitle())
                .review(book.getReview())
                .publisher(publisherToPublisherDTO(book.getPublisher()))
                .build();
    }
}
