package com.api.v2.medical_slots.controllers

import com.api.v2.medical_slots.DefaultMedicalSlotResponseDTO
import com.api.v2.medical_slots.services.MedicalSlotManagementService
import com.api.v2.medical_slots.services.MedicalSlotRegistrationService
import com.api.v2.medical_slots.services.MedicalSlotRetrievalService
import jakarta.validation.constraints.NotNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v2/medical-slots")
class MedicalSlotController(
    private val retrievalService: MedicalSlotRetrievalService,
    private val registrationService: MedicalSlotRegistrationService,
    private val managementService: MedicalSlotManagementService
) {

    @GetMapping("{id}/{medicalLicenseNumber}")
    fun findByIdAndDoctor(
        @PathVariable id: String,
        @PathVariable medicalLicenseNumber: String
    ): ResponseEntity<DefaultMedicalSlotResponseDTO> {
        return retrievalService.findByIdAndDoctor(id, medicalLicenseNumber)
    }

    @GetMapping("{medicalLicenseNumber}")
    fun findAllByDoctor(
        @PathVariable medicalLicenseNumber: String,
        pageable: Pageable
    ): ResponseEntity<Page<DefaultMedicalSlotResponseDTO>> {
        return retrievalService.findAllByDoctor(medicalLicenseNumber, pageable)
    }

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<DefaultMedicalSlotResponseDTO>> {
        return retrievalService.findAll( pageable)
    }

    @PostMapping("{medicalLicenseNumber}/{availableAt}")
    fun register(
        @PathVariable medicalLicenseNumber: String,
        @PathVariable availableAt: @NotNull LocalDateTime
    ): ResponseEntity<DefaultMedicalSlotResponseDTO> {
        return registrationService.register(medicalLicenseNumber, availableAt)
    }

    @PatchMapping("{medicalLicenseNumber}/{slotId}/cancellation")
    fun cancel(
        @PathVariable medicalLicenseNumber: String,
        @PathVariable slotId: String
    ): ResponseEntity<Unit> {
        return managementService.cancel(medicalLicenseNumber, slotId)
    }

    @PatchMapping("{medicalLicenseNumber}/{slotId}/completion")
    fun complete(
        @PathVariable medicalLicenseNumber: String,
        @PathVariable slotId: String
    ): ResponseEntity<Unit> {
        return managementService.complete(medicalLicenseNumber, slotId)
    }
}