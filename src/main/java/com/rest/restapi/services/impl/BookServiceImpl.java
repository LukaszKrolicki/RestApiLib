package com.rest.restapi.services.impl;

import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.domain.Entities.BookEntity;
import com.rest.restapi.repositories.AuthorRepository;
import com.rest.restapi.repositories.BookRepository;
import com.rest.restapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            AuthorEntity authorEntity = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(authorEntity);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> listBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public BookEntity getBook(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    @Override
    public BookEntity updateBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity patchBook(String isbn, BookEntity bookEntity) {
        return bookRepository.findById(isbn).map(existingBookEntity ->{
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBookEntity::setTitle);
            if (bookEntity.getAuthor() != null && bookEntity.getAuthor().getId() != null) {
                AuthorEntity authorEntity = authorRepository.findById(bookEntity.getAuthor().getId())
                        .orElseThrow(() -> new RuntimeException("Author not found"));
                existingBookEntity.setAuthor(authorEntity);
            } else {
                Optional.ofNullable(bookEntity.getAuthor()).ifPresent(existingBookEntity::setAuthor);
            }
            return bookRepository.save(existingBookEntity);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
