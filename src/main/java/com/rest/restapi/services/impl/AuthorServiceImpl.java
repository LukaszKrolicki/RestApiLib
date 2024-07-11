package com.rest.restapi.services.impl;

import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.domain.Entities.BookEntity;
import com.rest.restapi.repositories.AuthorRepository;
import com.rest.restapi.repositories.BookRepository;
import com.rest.restapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<AuthorEntity> listAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public AuthorEntity getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public AuthorEntity updateAuthor(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity patchAuthor(Long id, AuthorEntity authorEntity) {
        return authorRepository.findById(id).map(existingAuthorEntity ->{
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthorEntity::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthorEntity::setAge);
            return authorRepository.save(existingAuthorEntity);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public void deleteAuthor(Long id) {
        // Fetch all books by the author
        List<BookEntity> booksByAuthor = bookRepository.findByAuthorId(id);

        // Delete or update all books by the author
        for (BookEntity book : booksByAuthor) {

             book.setAuthor(null);
             bookRepository.save(book);
        }

        // Now you can delete the author
        authorRepository.deleteById(id);
    }

}
