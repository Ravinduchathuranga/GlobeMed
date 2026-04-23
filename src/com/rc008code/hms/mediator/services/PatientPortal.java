package com.rc008code.hms.mediator.services;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.mediator.api.AppointmentColleague;
import com.rc008code.hms.mediator.api.AppointmentMediator;

/**
 * Colleague representing a patient-facing UI/service that requests scheduling.
 */
public class PatientPortal implements AppointmentColleague {
    private AppointmentMediator mediator;

    @Override
    public void setMediator(AppointmentMediator mediator) {
        this.mediator = mediator;
    }

    public boolean requestAppointment(AppointmentDto dto) {
        if (mediator == null) throw new IllegalStateException("Mediator not set");
        return mediator.scheduleAppointment(dto);
    }

    public boolean cancelAppointment(String appointmentId) {
        if (mediator == null) throw new IllegalStateException("Mediator not set");
        return mediator.cancelAppointment(appointmentId);
    }
}
