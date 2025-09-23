package com.rc008code.hms.appointments.builder;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.enums.AppointmentStatus;

import java.util.Date;

/**
 * Fluent Builder for AppointmentDto
 */
public class AppointmentDtoBuilder {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private Date appointmentDate;
    private String description;
    private AppointmentStatus status;

    private AppointmentDtoBuilder() {}

    public static AppointmentDtoBuilder builder() {
        return new AppointmentDtoBuilder();
    }

    public AppointmentDtoBuilder withId(String id) {
        this.appointmentId = id;
        return this;
    }

    public AppointmentDtoBuilder withPatientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public AppointmentDtoBuilder withDoctorId(String doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public AppointmentDtoBuilder at(Date date) {
        this.appointmentDate = date;
        return this;
    }

    public AppointmentDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public AppointmentDtoBuilder withStatus(AppointmentStatus status) {
        this.status = status;
        return this;
    }

    public AppointmentDto build() {
        return new AppointmentDto(appointmentId, patientId, doctorId, appointmentDate, description, status);
        }
}
