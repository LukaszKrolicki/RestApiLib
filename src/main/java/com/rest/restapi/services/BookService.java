package com.rest.restapi.services;

import com.rest.restapi.domain.Entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> listBooks();

    BookEntity getBook(String isbn);

    BookEntity updateBook(String isbn, BookEntity bookEntity);

    BookEntity patchBook(String isbn, BookEntity bookEntity);

    void deleteBook(String isbn);

    Page<BookEntity> findAll(Pageable pageable);
}
