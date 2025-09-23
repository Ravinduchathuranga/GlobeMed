package com.rc008code.hms.patients.service;

import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.patients.builder.PatientDtoBuilder;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Simple patient management facade that demonstrates the Builder pattern usage
 * for creating and updating PatientDto objects. This module is self-contained
 * and does not modify existing business/UI layers.
 */
public class PatientManagementService {

    /**
     * Creates a new PatientDto with a generated UUID as id using the Builder pattern.
     */
    public PatientDto createPatient(String name,
                                    String address,
                                    int age,
                                    Gender gender,
                                    String contact,
                                    String email) {
        String id = UUID.randomUUID().toString();
        return PatientDtoBuilder.builder()
                .withId(id)
                .withName(name)
                .withAddress(address)
                .withAge(age)
                .withGender(gender)
                .withContact(contact)
                .withEmail(email)
                .build();
    }

    /**
     * Returns an updated copy of the given PatientDto by applying the provided builder customizer.
     * The original instance remains unchanged (immutability style API over a mutable DTO).
     */
    public PatientDto updatePatient(PatientDto existing, Consumer<PatientDtoBuilder> customizer) {
        PatientDtoBuilder builder = PatientDtoBuilder.from(existing);
        if (customizer != null) customizer.accept(builder);
        return builder.build();
    }

    /** Converts an entity instance to DTO using the Builder. */
    public PatientDto fromEntity(Patient entity) {
        if (entity == null) return null;
        return PatientDtoBuilder.builder()
                .withId(entity.getPatientId())
                .withName(entity.getName())
                .withAddress(entity.getAddress())
                .withAge(entity.getAge())
                .withGender(entity.getGender())
                .withContact(entity.getContact())
                .withEmail(entity.getEmail())
                .build();
    }

    /** Converts a DTO to entity. */
    public Patient toEntity(PatientDto dto) {
        if (dto == null) return null;
        return new Patient(
                dto.getPatientId(),
                dto.getName(),
                dto.getAddress(),
                dto.getAge(),
                dto.getGender(),
                dto.getContact(),
                dto.getEmail()
        );
    }
}
