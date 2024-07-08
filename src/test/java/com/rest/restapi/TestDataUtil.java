package com.rest.restapi;


import com.rest.restapi.domain.Author;
import com.rest.restapi.domain.Book;

public final class TestDataUtil {

    private TestDataUtil() {
    }


    public static Author createTestAuthor() {
        return Author.builder().id(1L).name("John Doe").age(29).build();
    }

    public static Author createTestAuthor2() {
        return Author.builder().id(2L).name("John Do").age(31).build();
    }

    public static Author createTestAuthor3() {
        return Author.builder().id(3L).name("John D").age(32).build();
    }


    public static Book createBook(Author author) {
        return Book.builder().isbn("xd").title("Book Title").author(author).build();
    }
    public static Book createBook2(Author author) {
        return Book.builder().isbn("xd2").title("Book Title").author(author).build();
    }
    public static Book createBook3(Author author) {
        return Book.builder().isbn("xd3").title("Book Title").author(author).build();
    }

}
