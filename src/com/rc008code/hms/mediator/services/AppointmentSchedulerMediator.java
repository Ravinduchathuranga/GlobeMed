package com.rc008code.hms.mediator.services;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.enums.AppointmentStatus;
import com.rc008code.hms.mediator.api.AppointmentMediator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AppointmentSchedulerMediator implements AppointmentMediator {
    private final PatientPortal patientPortal;
    private final DoctorCalendar doctorCalendar;
    private final NotificationService notificationService;


    private final Map<String, AppointmentDto> store = new HashMap<>();

    public AppointmentSchedulerMediator(PatientPortal patientPortal,
                                        DoctorCalendar doctorCalendar,
                                        NotificationService notificationService) {
        this.patientPortal = Objects.requireNonNull(patientPortal);
        this.doctorCalendar = Objects.requireNonNull(doctorCalendar);
        this.notificationService = Objects.requireNonNull(notificationService);

        this.patientPortal.setMediator(this);
        this.doctorCalendar.setMediator(this);
        this.notificationService.setMediator(this);
    }

    @Override
    public boolean scheduleAppointment(AppointmentDto dto) {
        if (dto == null || dto.getDoctorId() == null || dto.getPatientId() == null || dto.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Invalid appointment data");
        }
        Date slot = dto.getAppointmentDate();
        if (!doctorCalendar.isAvailable(dto.getDoctorId(), slot)) {
            notifyParticipants(dto, "Requested slot is not available.");
            return false;
        }
        // Reserve and store
        doctorCalendar.reserve(dto);
        dto.setStatus(AppointmentStatus.PENDING);
        store.put(dto.getAppointmentId(), dto);
        notifyParticipants(dto, "Appointment scheduled for " + slot);
        return true;
    }

    @Override
    public boolean cancelAppointment(String appointmentId) {
        AppointmentDto dto = store.remove(appointmentId);
        if (dto == null) return false;
        doctorCalendar.release(dto.getDoctorId(), dto.getAppointmentDate());
        notifyParticipants(dto, "Appointment canceled.");
        return true;
    }

    @Override
    public void notifyParticipants(AppointmentDto dto, String message) {
        notificationService.send(dto, message);
    }
}
