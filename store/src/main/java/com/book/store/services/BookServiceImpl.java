package com.book.store.services;

import com.book.store.dto.BookDTO;
import com.book.store.models.Book;
import com.book.store.repositories.BookRepository;
import org.springframework.stereotype.Service;

import static com.book.store.mappers.BookMapper.bookDTOToEntity;
import static com.book.store.mappers.BookMapper.bookToBookDTO;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book bookToSave = bookDTOToEntity(bookDTO);
        Book savedBook = bookRepository.save(bookToSave);
        return bookToBookDTO(savedBook);
    }
}