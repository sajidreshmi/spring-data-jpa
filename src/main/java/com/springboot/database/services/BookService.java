package com.springboot.database.services;

import com.springboot.database.domain.BookEntity;
import com.springboot.database.domain.dto.BookDto;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);
}
