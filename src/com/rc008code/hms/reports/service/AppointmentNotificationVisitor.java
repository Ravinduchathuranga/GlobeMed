package com.rc008code.hms.reports.service;

import com.rc008code.hms.reports.api.ReportVisitor;
import com.rc008code.hms.reports.element.AppointmentReportElement;
import com.rc008code.hms.reports.element.PatientReportElement;
import com.rc008code.hms.reports.util.MailManager;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AppointmentNotificationVisitor implements ReportVisitor {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    @Override
    public void visit(PatientReportElement patientElement) {
        // We keep the method to adhere to the ReportVisitor interface.
    }

    @Override
    public void visit(AppointmentReportElement appointmentElement) {
        try {
            String formattedDate = formatDate(appointmentElement.getAppointment().getAppointmentDate());

            // Email to Patient
            String pSubject = "Appointment Confirmation: " + appointmentElement.getAppointment().getAppointmentId();
            String pBody = "Dear " + safe(appointmentElement.getPatient().getName()) + ",\n\n" +
                    "Your appointment has been scheduled." + "\n" +
                    "Appointment ID : " + safe(appointmentElement.getAppointment().getAppointmentId()) + "\n" +
                    "Doctor         : " + safe(appointmentElement.getDoctor().getName()) + " (" + safe(appointmentElement.getDoctor().getSpecialty()) + ")\n" +
                    "Date & Time    : " + formattedDate + "\n" +
                    "Description    : " + safe(appointmentElement.getAppointment().getDescription()) + "\n\n" +
                    "Please arrive 10 minutes early.\n\n" +
                    "Best regards,\nGlobal_med Team";
            MailManager.sendMail(appointmentElement.getPatient().getEmail(), pSubject, pBody);

            // Email to Doctor
            String dSubject = "New Appointment Scheduled: " + appointmentElement.getAppointment().getAppointmentId();
            String dBody = "Dear Dr. " + safe(appointmentElement.getDoctor().getName()) + ",\n\n" +
                    "A new appointment has been scheduled." + "\n" +
                    "Appointment ID : " + safe(appointmentElement.getAppointment().getAppointmentId()) + "\n" +
                    "Patient        : " + safe(appointmentElement.getPatient().getName()) + " (" + safe(appointmentElement.getPatient().getPatientId()) + ")\n" +
                    "Date & Time    : " + formattedDate + "\n" +
                    "Description    : " + safe(appointmentElement.getAppointment().getDescription()) + "\n\n" +
                    "Please review your calendar.\n\n" +
                    "Best regards,\nGlobal_med Team";
            MailManager.sendMail(appointmentElement.getDoctor().getEmail(), dSubject, dBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    private String safe(Object o) {
        return o == null ? "" : String.valueOf(o);
    }
}
