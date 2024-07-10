package com.rest.restapi.controllers;


import com.rest.restapi.domain.DTO.AuthorDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.mappers.Mapper;
import com.rest.restapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authors = authorService.listAuthors();
        return authors.stream().map(authorMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/authors/{id}")
    public AuthorDto getAuthor(@PathVariable("id") Long id) {
        AuthorEntity author = authorService.getAuthor(id);
        return authorMapper.mapToDto(author);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto author) {
        if(authorService.getAuthor(id) == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = authorMapper.mapToEntity(author);
        AuthorEntity updatedAuthorEntity = authorService.updateAuthor(id, authorEntity);
        return new ResponseEntity<>(authorMapper.mapToDto(updatedAuthorEntity), org.springframework.http.HttpStatus.OK);
    }
}
