package com.springboot.database.controllers;

import com.springboot.database.domain.AuthorEntity;
import com.springboot.database.domain.BookEntity;
import com.springboot.database.domain.dto.AuthorDto;
import com.springboot.database.domain.dto.BookDto;
import com.springboot.database.mappers.Mapper;
import com.springboot.database.services.BookService;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Log
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

       public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
            this.bookMapper = bookMapper;
            this.bookService = bookService;
        }
//    @GetMapping("/books")
//    public BookEntity retrieveBooks() {
//        return BookEntity.builder()
//                .title("The Great Gatsby")
//                .isbn("978-3-16-148410-0")
//                .yearPublished("1925")
//                .authorEntity(AuthorEntity.builder().build())
//                .build();
//    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable String isbn, @RequestBody BookDto book) {
        log.info("Creating book with ISBN: " + isbn);
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public Page<BookDto> listBooks(Pageable pageable) {
           Page<BookEntity> books= bookService.findAll(pageable);
           return books.map(bookEntity -> bookMapper.mapTo(bookEntity));
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findByIsbn(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> fullUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        Optional<BookEntity> foundBook = bookService.findByIsbn(isbn);
        return foundBook.map(bookEntity -> {
            bookEntity.setTitle(book.getTitle());
            bookEntity.setYearPublished(book.getYearPublished());
            BookEntity updatedBookEntity = bookService.createBook(isbn, bookEntity);
            return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
