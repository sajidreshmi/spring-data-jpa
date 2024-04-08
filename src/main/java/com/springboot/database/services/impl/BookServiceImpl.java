package com.springboot.database.services.impl;

import com.springboot.database.domain.BookEntity;
import com.springboot.database.domain.dto.BookDto;
import com.springboot.database.repositories.BookRepository;
import com.springboot.database.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return this.bookRepository.save(bookEntity);
    }
}
