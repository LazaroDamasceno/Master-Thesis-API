package com.api.v1.integration_tests;

import com.api.v1.people.dtos.Address;
import com.api.v1.people.enums.Gender;
import com.api.v1.people.PersonRegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class DoctorRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    PersonRegistrationDTO doctorDTO = new PersonRegistrationDTO(
            "Willian",
            "",
            "Belfast",
            UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 10),
            LocalDate.of(2000,12,12),
                    "william@drbelfast.com",
                    Gender.MALE,
            new Address(
                    "Downtown",
                    "LA",
                    "90012"
            ),
            "1234567890"
    );

    String licenseNumber = UUID
            .randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 10);

    @Order(1)
    @Test
    void shouldReturnCreatedWhenSuccessful() throws Exception {
        mockMvc.perform(post("/api/v1/doctors/%s".formatted(licenseNumber))
                .content(objectMapper.writeValueAsString(doctorDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Order(2)
    @Test
    void shouldReturnConflictWhenMedicalLicenseNumber() throws Exception {
        mockMvc.perform(post("/api/v1/doctors/%s".formatted(licenseNumber))
                .content(objectMapper.writeValueAsString(doctorDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }

    PersonRegistrationDTO duplicatedSinDTO = new PersonRegistrationDTO(
            "Willian",
            "",
            "Belfast",
            UUID.randomUUID()
                            .toString()
                            .replace("-", "")
                            .substring(0, 10),
            LocalDate.of(2000,12,12),
            "william@drbelfast.com",
            Gender.MALE,
            new Address(
                    "Downtown",
                    "LA",
                    "90012"
            ),
            "1234567890"
    );

    @Order(3)
    @Test
    void shouldReturnConflictWhenSinIsDuplicated() throws Exception {
        String randomLicenseNumber = UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
        mockMvc.perform(post("/api/v1/doctors/%s".formatted(randomLicenseNumber))
                .content(objectMapper.writeValueAsString(duplicatedSinDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }

    PersonRegistrationDTO duplicatedEmailDTO = new PersonRegistrationDTO(
            "Willian",
            "",
            "Belfast",
            UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 10),
            LocalDate.of(2000,12,12),
            "william@drbelfast.com",
            Gender.MALE,
            new Address(
                    "Downtown",
                    "LA",
                    "90012"
            ),
            "1234567890"
    );

    @Order(4)
    @Test
    void shouldReturnConflictWhenEmailIsDuplicated() throws Exception {
        String randomLicenseNumber = UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
        mockMvc.perform(post("/api/v1/doctors/%s".formatted(randomLicenseNumber))
                .content(objectMapper.writeValueAsString(duplicatedEmailDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }

}
