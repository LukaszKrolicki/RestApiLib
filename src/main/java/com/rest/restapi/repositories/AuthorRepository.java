package com.rest.restapi.repositories;



import com.rest.restapi.domain.Entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("SELECT a FROM AuthorEntity a where a.age>?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThat(int age);
}
