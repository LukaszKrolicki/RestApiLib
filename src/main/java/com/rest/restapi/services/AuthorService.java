package com.rest.restapi.services;

import com.rest.restapi.controllers.AuthorController;
import com.rest.restapi.domain.Entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> listAuthors();
}
