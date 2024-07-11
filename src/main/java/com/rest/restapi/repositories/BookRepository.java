package com.rest.restapi.repositories;


import com.rest.restapi.domain.Entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String>, PagingAndSortingRepository<BookEntity,String> {
    List<BookEntity> findByAuthorId(Long id);
}
