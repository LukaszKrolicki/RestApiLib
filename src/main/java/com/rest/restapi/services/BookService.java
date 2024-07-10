package com.rest.restapi.services;

import com.rest.restapi.domain.Entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}
