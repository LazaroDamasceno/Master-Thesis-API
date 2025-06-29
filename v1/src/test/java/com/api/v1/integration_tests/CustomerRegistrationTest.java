package com.api.v1.integration_tests;

import com.api.v1.people.dtos.Address;
import com.api.v1.people.enums.Gender;
import com.api.v1.people.PersonRegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    PersonRegistrationDTO customerDTO  = new PersonRegistrationDTO(
            "Leonard",
            "",
            "Smith",
            UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 10),
            LocalDate.of(2000,12,12),
            "leosmith@mail.com",
            Gender.MALE,
            new Address(
                    "Downtown",
                    "LA",
                    "90012"
            ),
            "1234567890"
    );

    PersonRegistrationDTO duplicatedEmailDTO  = new PersonRegistrationDTO(
            "Leonard",
            "",
            "Smith",
            UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 10),
            LocalDate.of(2000,12,12),
            "leosmith@mail.com",
            Gender.MALE,
            new Address(
                    "Downtown",
                    "LA",
                    "90012"
            ),
            "1234567890"
    );

    @Test
    @Order(1)
    void shouldReturnCreatedWhenSuccessful() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void shouldReturnConflictWhenSinIsDuplicated() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(3)
    void shouldReturnConflictWhenEmailIsDuplicated() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicatedEmailDTO)))
                .andExpect(status().isConflict());
    }
}