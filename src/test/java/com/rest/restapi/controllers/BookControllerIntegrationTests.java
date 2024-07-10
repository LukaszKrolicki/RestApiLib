package com.rest.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.restapi.TestDataUtil;
import com.rest.restapi.domain.DTO.BookDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatCreateBookSuccessfullyReturns201() throws Exception {
        BookDto bookDto = TestDataUtil.createBook(null);
        String createBookJson=objectMapper.writeValueAsString(bookDto);

        mvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .content(createBookJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testThatBookIsCreatedRetrievesBook() throws Exception {
        BookDto bookDto = TestDataUtil.createBook(null);
        String createBookJson=objectMapper.writeValueAsString(bookDto);
        mvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .content(createBookJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor()));
    }

    @Test
    public void testThatBookRetrievesOkStatusAndListOfBooks() throws Exception {
        BookDto bookDto = TestDataUtil.createBook(null);
        String createBookJson=objectMapper.writeValueAsString(bookDto);
        mvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .content(createBookJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders.get("/books")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookDto.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(bookDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(bookDto.getAuthor()));
    }

}
