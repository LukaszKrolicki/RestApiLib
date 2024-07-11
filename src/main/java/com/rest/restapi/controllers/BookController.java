package com.rest.restapi.controllers;

import com.rest.restapi.domain.DTO.BookDto;
import com.rest.restapi.domain.Entities.BookEntity;
import com.rest.restapi.mappers.Mapper;
import com.rest.restapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private Mapper<BookEntity, BookDto> bookMapper;

    @Autowired
    private BookService bookService;
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        BookEntity bookEntity = bookMapper.mapToEntity(book);
        if(bookService.getBook(bookEntity.getIsbn()) != null) {
            bookService.updateBook(isbn, bookEntity);
            return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
        }
        else{
            BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
            return new ResponseEntity<>(bookMapper.mapToDto(savedBookEntity), org.springframework.http.HttpStatus.CREATED);
        }
    }

    @GetMapping("/books")
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.listBooks();
        return books.stream().map(bookMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {

        BookEntity book = bookService.getBook(isbn);
        if(book == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookMapper.mapToDto(book), org.springframework.http.HttpStatus.OK);
    }

}
