package com.rc008code.hms.mediator;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.enums.AppointmentStatus;
import com.rc008code.hms.mediator.services.AppointmentSchedulerMediator;
import com.rc008code.hms.mediator.services.DoctorCalendar;
import com.rc008code.hms.mediator.services.NotificationService;
import com.rc008code.hms.mediator.services.PatientPortal;

import java.util.Date;
import java.util.UUID;

/**
 * Simple demo showing how to schedule an appointment using the Mediator pattern.
 */
public class AppointmentMediatorDemo {
    public static void main(String[] args) {
        // Create colleagues
        PatientPortal patientPortal = new PatientPortal();
        DoctorCalendar doctorCalendar = new DoctorCalendar();
        NotificationService notificationService = new NotificationService();

        // Wire mediator
        AppointmentSchedulerMediator mediator = new AppointmentSchedulerMediator(
                patientPortal, doctorCalendar, notificationService
        );

        // Create a sample appointment request
        AppointmentDto dto = new AppointmentDto();
        dto.setAppointmentId(UUID.randomUUID().toString());
        dto.setPatientId("P-001");
        dto.setDoctorId("D-001");
        dto.setAppointmentDate(new Date());
        dto.setDescription("General checkup");
        dto.setStatus(AppointmentStatus.PENDING);

        // Request scheduling via the patient portal (colleague)
        boolean ok = patientPortal.requestAppointment(dto);
        System.out.println("Scheduled? " + ok);

        // Try to schedule the same slot again to see conflict handling
        AppointmentDto conflict = new AppointmentDto();
        conflict.setAppointmentId(UUID.randomUUID().toString());
        conflict.setPatientId("P-002");
        conflict.setDoctorId("D-001");
        conflict.setAppointmentDate(dto.getAppointmentDate());
        conflict.setDescription("Conflicting slot");
        boolean ok2 = patientPortal.requestAppointment(conflict);
        System.out.println("Scheduled second appointment? " + ok2);

        // Cancel the first one
        boolean canceled = patientPortal.cancelAppointment(dto.getAppointmentId());
        System.out.println("Canceled first? " + canceled);

        // Now try again after cancel — should succeed
        boolean ok3 = patientPortal.requestAppointment(conflict);
        System.out.println("Scheduled after cancel? " + ok3);
    }
}
