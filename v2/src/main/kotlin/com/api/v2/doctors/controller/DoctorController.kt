package com.api.v2.doctors.controller

import com.api.v2.doctors.DoctorResponseDTO
import com.api.v2.doctors.services.*
import com.api.v2.people.PersonRegistrationDTO
import com.api.v2.people.PersonUpdateDTO
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v2/doctors")
class DoctorController(
    private val registrationService: DoctorRegistrationService,
    private val managementService: DoctorManagementService,
    private val retrievalService: DoctorRetrievalService
) {

    @PostMapping("{licenseNumber}")
    fun register(
        @PathVariable licenseNumber: String,
        @RequestBody registrationDTO: @Valid PersonRegistrationDTO
    ): ResponseEntity<DoctorResponseDTO> {
        return registrationService.register(licenseNumber, registrationDTO)
    }

    @PatchMapping("{licenseNumber}/termination")
    fun terminate(@PathVariable licenseNumber: String): ResponseEntity<Unit> {
        return managementService.terminate(licenseNumber)
    }

    @PatchMapping("{licenseNumber}/rehiring")
    fun rehire(@PathVariable licenseNumber: String): ResponseEntity<Unit> {
        return managementService.rehire(licenseNumber)
    }

    @PatchMapping("{licenseNumber}/updating")
    fun update(
        @PathVariable licenseNumber: String,
        @RequestBody updateDTO: @Valid PersonUpdateDTO
    ): ResponseEntity<Unit> {
        return managementService.update(licenseNumber, updateDTO)
    }

    @GetMapping("{licenseNumber}")
    fun findByLicenseNumber(@PathVariable licenseNumber: String): ResponseEntity<DoctorResponseDTO> {
        return retrievalService.findByLicenseNumber(licenseNumber)
    }

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<DoctorResponseDTO>> {
        return retrievalService.findAll(pageable)
    }


}