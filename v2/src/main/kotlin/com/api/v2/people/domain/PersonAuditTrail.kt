package com.api.v2.people.domain

import com.api.v2.people.domain.exposed.Person
import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.util.UUID

data class PersonAuditTrail(
    @Id val id: String,
    val person: Person,
    val createdAt: LocalDateTime
) {

    companion object {
        fun of(person: Person): PersonAuditTrail {
            return PersonAuditTrail(
                UUID.randomUUID().toString(),
                person,
                LocalDateTime.now()
            )
        }
    }
}
