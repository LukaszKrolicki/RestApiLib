package com.rest.restapi.repositories;


import com.rest.restapi.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    @Query("SELECT a FROM Author a where a.age>?1")
    Iterable<Author> findAuthorsWithAgeGreaterThat(int age);
}
