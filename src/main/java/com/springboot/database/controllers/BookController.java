package com.springboot.database.controllers;

import com.springboot.database.domain.AuthorEntity;
import com.springboot.database.domain.BookEntity;
import com.springboot.database.domain.dto.AuthorDto;
import com.springboot.database.domain.dto.BookDto;
import com.springboot.database.mappers.Mapper;
import com.springboot.database.services.BookService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

       public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
            this.bookMapper = bookMapper;
            this.bookService = bookService;
        }
    @GetMapping("/books")
    public BookEntity retrieveBooks() {
        return BookEntity.builder()
                .title("The Great Gatsby")
                .isbn("978-3-16-148410-0")
                .yearPublished("1925")
                .authorEntity(AuthorEntity.builder().build())
                .build();
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable String isbn, @RequestBody BookDto book) {
        log.info("Creating book with ISBN: " + isbn);
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }
}
