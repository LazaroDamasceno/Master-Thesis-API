package com.api.v1.medical_slots.domain;

import com.api.v1.medical_slots.MedicalSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicalSlotCrudRepository extends MongoRepository<MedicalSlot, String> {

    @Query("{ 'doctor._id': ?0 }")
    Page<MedicalSlot> findAllByDoctor(String doctorId, Pageable pageable);

    @Query("{ 'doctor._id': ?0, 'availableAt': ?1, 'status': 'ACTIVE' }")
    Optional<MedicalSlot> findActiveByDoctorAndAvailable(String doctorId, LocalDateTime availableAt);
}
