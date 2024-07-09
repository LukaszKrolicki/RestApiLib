package com.rest.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.restapi.TestDataUtil;
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
public class AuthorControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

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

}
