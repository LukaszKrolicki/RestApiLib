package com.rest.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.restapi.TestDataUtil;
import com.rest.restapi.domain.DTO.AuthorDto;
import com.rest.restapi.domain.Entities.AuthorEntity;
import com.rest.restapi.services.AuthorService;
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
public class AuthorControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthorService authorService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testThatCreateAuthorSuccessfullyReturns201() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .content(authorJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .content(authorJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge()));
    }

    @Test
    public void testThatListAuthorsReturnsHttp200() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        authorService.createAuthor(testAuthorA);

        mvc.perform(
                MockMvcRequestBuilders.get("/authors")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testAuthorA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(testAuthorA.getAge()));
    }

    @Test
    public void testThatAuthorByIdReturnsAuthorById() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        AuthorEntity savedAuthor = authorService.createAuthor(testAuthorA);

        mvc.perform(
                MockMvcRequestBuilders.get("/authors/" + savedAuthor.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge()));
    }

    @Test
    public void testThatPutAuthorReturns200() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        AuthorEntity savedAuthor = authorService.createAuthor(testAuthorA);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("New Name");
        authorDto.setAge(30);
        String authorJson = objectMapper.writeValueAsString(authorDto);
        mvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .content(authorJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPutAuthorCorrectlyChangesAuthorData() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthor();
        testAuthorA.setId(null);
        AuthorEntity savedAuthor = authorService.createAuthor(testAuthorA);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("New Name");
        authorDto.setAge(30);
        String authorJson = objectMapper.writeValueAsString(authorDto);
        mvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthor.getId())
                        .content(authorJson)
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge()));
    }
}
