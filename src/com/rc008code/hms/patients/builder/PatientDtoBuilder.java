package com.rc008code.hms.patients.builder;

import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.enums.Gender;

/**
 * Fluent Builder for PatientDto.
 *
 * Usage:
 * PatientDto dto = PatientDtoBuilder.builder()
 *      .withId("P-001")
 *      .withName("Jane Doe")
 *      .withAddress("123 Main St")
 *      .withAge(28)
 *      .withGender(Gender.FEMALE)
 *      .withContact("+1-202-555-0100")
 *      .withEmail("jane@example.com")
 *      .build();
 */
public class PatientDtoBuilder {
    private String patientId;
    private String name;
    private String address;
    private int age;
    private Gender gender;
    private String contact;
    private String email;

    private PatientDtoBuilder() {}

    public static PatientDtoBuilder builder() {
        return new PatientDtoBuilder();
    }

    public PatientDtoBuilder withId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public PatientDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PatientDtoBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public PatientDtoBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public PatientDtoBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PatientDtoBuilder withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public PatientDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public PatientDto build() {
        return new PatientDto(patientId, name, address, age, gender, contact, email);
    }

    public static PatientDtoBuilder from(PatientDto existing) {
        return builder()
                .withId(existing.getPatientId())
                .withName(existing.getName())
                .withAddress(existing.getAddress())
                .withAge(existing.getAge())
                .withGender(existing.getGender())
                .withContact(existing.getContact())
                .withEmail(existing.getEmail());
    }
}
