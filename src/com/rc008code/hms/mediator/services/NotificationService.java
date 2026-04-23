package com.rc008code.hms.mediator.services;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.mediator.api.AppointmentColleague;
import com.rc008code.hms.mediator.api.AppointmentMediator;

public class NotificationService implements AppointmentColleague {
    private AppointmentMediator mediator;

    @Override
    public void setMediator(AppointmentMediator mediator) {
        this.mediator = mediator;
    }

    public void send(AppointmentDto dto, String message) {
        System.out.println("[NOTIFY] To Patient " + dto.getPatientId() + ", Doctor " + dto.getDoctorId() + ": " + message);
    }
}
