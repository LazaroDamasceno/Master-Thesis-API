package com.api.v2

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DoctorRehiringTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Order(1)
    @Test
    fun `should return no content when successful`() {
        val licenseNumber = ""
        mockMvc.perform(
            patch("/api/v2/doctors/$licenseNumber/rehiring")
        ).andExpect(status().isNoContent)
    }

    @Order(2)
    @Test
    fun `should return conflict when doctor is active`() {
        val licenseNumber = ""
        mockMvc.perform(
            patch("/api/v2/doctors/$licenseNumber/rehiring")
        ).andExpect(status().isConflict)
    }

    @Order(3)
    @Test
    fun `should return not found when doctor was not found`() {
        val licenseNumber = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 10)
        mockMvc.perform(
            patch("/api/v2/doctors/$licenseNumber/rehiring")
        ).andExpect(status().isNotFound)
    }
}