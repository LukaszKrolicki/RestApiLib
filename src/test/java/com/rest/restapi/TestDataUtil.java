package com.rest.restapi;



import com.rest.restapi.domain.DTO.AuthorDto;
import com.rest.restapi.domain.DTO.BookDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.domain.Entities.BookEntity;

public final class TestDataUtil {

    private TestDataUtil() {
    }


    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder().id(1L).name("John Doe").age(29).build();
    }

    public static AuthorEntity createTestAuthor2() {
        return AuthorEntity.builder().id(2L).name("John Do").age(31).build();
    }

    public static AuthorEntity createTestAuthor3() {
        return AuthorEntity.builder().id(3L).name("John D").age(32).build();
    }


    public static BookDto createBook(AuthorDto author) {
        return BookDto.builder().isbn("xd").title("Book Title").author(author).build();
    }
    public static BookDto createBook2(AuthorDto author) {
        return BookDto.builder().isbn("xd2").title("Book Title").author(author).build();
    }
    public static BookDto createBook3(AuthorDto author) {
        return BookDto.builder().isbn("xd3").title("Book Title").author(author).build();
    }

}
