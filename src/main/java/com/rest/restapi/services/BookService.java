package com.rest.restapi.services;

import com.rest.restapi.domain.Entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> listBooks();
}
