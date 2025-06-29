package com.api.v2.medical_slots.domain

import com.api.v2.medical_slots.MedicalSlot
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDateTime

interface MedicalSlotCrudRepository: MongoRepository<MedicalSlot, String> {

    @Query("{ 'doctor._id': ?0 }")
    fun findAllByDoctor(doctorId: String, pageable: Pageable): Page<MedicalSlot>

    @Query("{ 'doctor._id': ?0, 'availableAt': ?1, 'status': 'ACTIVE' }")
    fun findActiveByDoctorAndAvailableAt(doctorId: String, availableAt: LocalDateTime): MedicalSlot?
}