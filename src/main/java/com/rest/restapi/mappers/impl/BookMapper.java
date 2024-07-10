package com.rest.restapi.mappers.impl;

import com.rest.restapi.domain.DTO.BookDto;
import com.rest.restapi.domain.Entities.BookEntity;
import com.rest.restapi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookDto mapToDto(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
