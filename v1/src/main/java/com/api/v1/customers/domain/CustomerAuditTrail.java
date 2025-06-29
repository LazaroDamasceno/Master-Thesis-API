package com.api.v1.customers.domain;

import com.api.v1.customers.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "CustomerAuditTrail")
public record CustomerAuditTrail(
        @Id
        String id,
        Customer customer,
        LocalDateTime createdAt
) {

    public static CustomerAuditTrail of(Customer customer) {
        return new CustomerAuditTrail(
                UUID.randomUUID().toString(),
                customer,
                LocalDateTime.now()
        );
    }
}
