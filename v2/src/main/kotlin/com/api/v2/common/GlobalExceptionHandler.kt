package com.api.v2.common

import com.api.v2.customers.exceptions.CustomerNotFoundException
import com.api.v2.doctors.exceptions.ActiveDoctorException
import com.api.v2.doctors.exceptions.DoctorNotFoundException
import com.api.v2.doctors.exceptions.TerminatedDoctorException
import com.api.v2.medical_appointment.exceptions.CancelledMedicalAppointmentException
import com.api.v2.medical_appointment.exceptions.CompletedMedicalAppointmentException
import com.api.v2.medical_appointment.exceptions.InaccessibleMedicalAppointmentException
import com.api.v2.medical_appointment.exceptions.MedicalAppointmentNotFoundException
import com.api.v2.medical_slots.exceptions.CancelledMedicalSlotException
import com.api.v2.medical_slots.exceptions.CompletedMedicalSlotException
import com.api.v2.medical_slots.exceptions.InaccessibleMedicalSlotException
import com.api.v2.medical_slots.exceptions.MedicalSlotNotFoundException
import com.api.v2.people.exceptions.DuplicatedEmailException
import com.api.v2.people.exceptions.DuplicatedSINException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedSINException::class)
    fun handleException(ex: DuplicatedSINException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(DuplicatedEmailException::class)
    fun handleException(ex: DuplicatedEmailException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(DuplicatedLicenseNumberException::class)
    fun handleException(ex: DuplicatedLicenseNumberException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(CustomerNotFoundException::class)
    fun handleException(ex: CustomerNotFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(DuplicatedBookingDateTimeException::class)
    fun handleException(ex: DuplicatedBookingDateTimeException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(DoctorNotFoundException::class)
    fun handleException(ex: DoctorNotFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(TerminatedDoctorException::class)
    fun handleException(ex: TerminatedDoctorException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(ActiveDoctorException::class)
    fun handleException(ex: ActiveDoctorException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(MedicalSlotNotFoundException::class)
    fun handleException(ex: MedicalSlotNotFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(InaccessibleMedicalSlotException::class)
    fun handleException(ex: InaccessibleMedicalSlotException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(CompletedMedicalSlotException::class)
    fun handleException(ex: CompletedMedicalSlotException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(CancelledMedicalSlotException::class)
    fun handleException(ex: CancelledMedicalSlotException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(CancelledMedicalAppointmentException::class)
    fun handleException(ex: CancelledMedicalAppointmentException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(CompletedMedicalAppointmentException::class)
    fun handleException(ex: CompletedMedicalAppointmentException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(InaccessibleMedicalAppointmentException::class)
    fun handleException(ex: InaccessibleMedicalAppointmentException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(MedicalAppointmentNotFoundException::class)
    fun handleException(ex: MedicalAppointmentNotFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}