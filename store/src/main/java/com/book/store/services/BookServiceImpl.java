package com.book.store.services;

import com.book.store.dto.BookDTO;
import com.book.store.dto.PublisherDTO;
import com.book.store.mappers.BookMapper;
import com.book.store.models.Book;
import com.book.store.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.book.store.mappers.BookMapper.bookDTOToEntity;
import static com.book.store.mappers.BookMapper.bookToBookDTO;
import static com.book.store.mappers.PublisherMapper.publisherDTOToEntity;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final PublisherServiceImpl publisherService;

    private BookServiceImpl(BookRepository bookRepository, PublisherServiceImpl publisherService){
        this.bookRepository = bookRepository;
        this.publisherService = publisherService;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        PublisherDTO publisherSaved = publisherService.savePublisher(bookDTO.publisher());
        Book bookToSave = bookDTOToEntity(bookDTO);
        bookToSave.setPublisher(publisherDTOToEntity(publisherSaved));
        Book savedBook = bookRepository.save(bookToSave);
        return bookToBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper::bookToBookDTO).toList();
    }
}
