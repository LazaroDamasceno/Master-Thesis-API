package com.api.v1.medical_slots;

import com.api.v1.doctors.DoctorResponseDTO;
import com.api.v1.medical_appointments.MedicalAppointment;
import com.api.v1.medical_appointments.MedicalAppointmentResponseDTO;
import com.api.v1.medical_slots.enums.MedicalSlotStatus;

import java.time.LocalDateTime;

public final class CompletedDefaultMedicalSlotResponseDTO extends DefaultMedicalSlotResponseDTO {

    private final LocalDateTime completedAt;
    private final MedicalAppointmentResponseDTO medicalAppointment;

    CompletedDefaultMedicalSlotResponseDTO(String id,
                                           MedicalSlotStatus status,
                                           DoctorResponseDTO doctor,
                                           LocalDateTime availableAt,
                                           LocalDateTime createdAt,
                                           LocalDateTime completedAt,
                                           MedicalAppointmentResponseDTO medicalAppointment
    ) {
        super(id, status, doctor, availableAt, createdAt);
        this.completedAt = completedAt;
        this.medicalAppointment = medicalAppointment;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public MedicalAppointmentResponseDTO getMedicalAppointment() {
        return medicalAppointment;
    }
}
