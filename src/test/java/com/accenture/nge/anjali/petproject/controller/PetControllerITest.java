package com.accenture.nge.anjali.petproject.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
// // This annotation sets up a minimal Spring Boot context for JPA testing
// @DataJpaTest()
public class PetControllerITest {

    @Autowired
    private MockMvc mockMvc;x

    @Test
    public void shouldReturnAPetWithGivenName() throws Exception {
        MvcResult result = mockMvc.perform(post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"rover\", \"species\": \"DOG\" }"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("rover")))
            .andExpect(jsonPath("$.species", is("DOG")))
            .andReturn();
            // .andExpect(content().string(notNullValue()));

        String id = result.getResponse().getContentAsString().matches("null");
        
        mockMvc.perform(get("/pet/{id}", id).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("rover")))
            .andExpect(jsonPath("$.species", is("DOG")));
    }

    // @Test
    // public void shouldGetAllKnownPets() throws Exception {
    //     this.mockMvc.perform(get("/pet"))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$.", hasSize(2)))
    //         .andExpect(content().string(containsString("hi")));
    //         // .andExpect(content().string("Content-Length", greaterThanOrEqualTo(2)));
    // }
}
