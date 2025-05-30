package com.api.v2.doctors.requests

import com.api.v2.people.requests.PersonRegistrationDTO
import jakarta.validation.Valid

data class DoctorRegistrationDTO(
    val licenseNumber: String,
    val person: @Valid PersonRegistrationDTO
)
