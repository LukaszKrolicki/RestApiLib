package com.rest.restapi.services.impl;

import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.repositories.AuthorRepository;
import com.rest.restapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        authorRepository.save(author);
        return author;
    }
}
