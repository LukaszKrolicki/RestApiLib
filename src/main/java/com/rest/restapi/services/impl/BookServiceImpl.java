package com.rest.restapi.services.impl;

import com.rest.restapi.domain.Entities.BookEntity;
import com.rest.restapi.repositories.BookRepository;
import com.rest.restapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
