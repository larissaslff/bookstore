package com.book.store.services;

import com.book.store.dto.BookDTO;
import com.book.store.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        return null;
    }
}
