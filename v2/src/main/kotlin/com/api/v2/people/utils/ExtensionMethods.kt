package com.api.v2.people.utils

import com.api.v2.people.domain.exposed.Person

fun Person.fullName(): String {
    if (this.middleName == null) {
        return "${this.firstName} ${this.lastName}"
    }
    return "${this.firstName} ${this.middleName} ${this.lastName}"
}