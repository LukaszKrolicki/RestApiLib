package com.rest.restapi.controllers;


import com.rest.restapi.domain.DTO.AuthorDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.mappers.Mapper;
import com.rest.restapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @Autowired
    private Mapper<AuthorEntity, AuthorDto> authorMapper;
    @PostMapping("/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapToEntity(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapToDto(savedAuthorEntity), org.springframework.http.HttpStatus.CREATED);
    }
}
