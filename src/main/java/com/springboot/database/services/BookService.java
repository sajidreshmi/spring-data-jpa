package com.springboot.database.services;

import com.springboot.database.domain.BookEntity;
import com.springboot.database.domain.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);
    List<BookEntity> findAll();

    Optional<BookEntity> findByIsbn(String isbn);

    Page<BookEntity> findAll(Pageable pageable);
}
