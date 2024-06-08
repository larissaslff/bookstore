package com.book.store.repositories;

import com.book.store.models.Author;
import com.book.store.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
