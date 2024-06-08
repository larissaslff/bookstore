package com.book.store.mappers;

import com.book.store.dto.BookDTO;
import com.book.store.models.Book;

public abstract class BookMapper {
    public static Book bookDTOToEntity(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.title())
                .authors(bookDTO.authors())
                .review(bookDTO.review())
                .publisher(bookDTO.publisher())
                .build();
    }

    public static BookDTO bookToBookDTO(Book book) {
        return BookDTO.builder()
                .authors(book.getAuthors())
                .title(book.getTitle())
                .review(book.getReview())
                .publisher(book.getPublisher())
                .build();
    }
}
