package com.book.store.services;

import com.book.store.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO saveBook(BookDTO bookDTO);

    List<BookDTO> getAllBook();
}
