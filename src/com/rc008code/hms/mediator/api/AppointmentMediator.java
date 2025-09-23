package com.rc008code.hms.mediator.api;

import com.rc008code.hms.dto.AppointmentDto;

public interface AppointmentMediator {
    boolean scheduleAppointment(AppointmentDto dto);

    boolean cancelAppointment(String appointmentId);

    void notifyParticipants(AppointmentDto dto, String message);
}
