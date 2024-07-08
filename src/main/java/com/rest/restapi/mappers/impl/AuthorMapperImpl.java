package com.rest.restapi.mappers.impl;

import com.rest.restapi.domain.DTO.AuthorDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private ModelMapper mapper;

    public AuthorMapperImpl(ModelMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public AuthorDto mapToDto(AuthorEntity authorEntity) {
        return mapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapToEntity(AuthorDto authorDto) {
        return mapper.map(authorDto, AuthorEntity.class);
    }
}


