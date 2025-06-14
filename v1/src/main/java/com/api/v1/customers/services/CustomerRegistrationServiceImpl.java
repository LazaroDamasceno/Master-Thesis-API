package com.api.v1.customers.services;

import com.api.v1.customers.Customer;
import com.api.v1.customers.domain.CustomerCrudRepository;
import com.api.v1.customers.CustomerResponseDTO;
import com.api.v1.people.Person;
import com.api.v1.people.exceptions.DuplicatedEmailException;
import com.api.v1.people.exceptions.DuplicatedSINException;
import com.api.v1.people.PersonRegistrationDTO;
import com.api.v1.people.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final CustomerCrudRepository repository;
    private final PersonRegistrationService personRegistrationService;

    public CustomerRegistrationServiceImpl(CustomerCrudRepository repository,
                                           PersonRegistrationService personRegistrationService
    ) {
        this.repository = repository;
        this.personRegistrationService = personRegistrationService;
    }

    @Override
    public ResponseEntity<CustomerResponseDTO> register(@Valid PersonRegistrationDTO registrationDTO) {
        validate(registrationDTO);
        Person savedPerson = personRegistrationService.register(registrationDTO);
        Customer newCustomer = Customer.of(savedPerson);
        Customer savedCustomer = repository.save(newCustomer);
        CustomerResponseDTO responseDto = savedCustomer.toDto();
        return ResponseEntity
                .created(URI.create("/api/v1/customers"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
    }

    private void validate(PersonRegistrationDTO registrationDto) {
        if (repository.findBySIN(registrationDto.sin()).isPresent()) {
            throw new DuplicatedSINException();
        }
        if (repository.findByEmail(registrationDto.email()).isPresent()) {
            throw new DuplicatedEmailException();
        }
    }
}
