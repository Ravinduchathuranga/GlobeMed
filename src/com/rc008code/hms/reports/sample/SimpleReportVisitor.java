package com.rc008code.hms.reports.sample;

import com.rc008code.hms.reports.api.ReportVisitor;
import com.rc008code.hms.reports.element.PatientReportElement;
import com.rc008code.hms.reports.element.AppointmentReportElement;

public class SimpleReportVisitor implements ReportVisitor {
    private final StringBuilder builder = new StringBuilder();

    public void startReport(String title) {
        builder.append("==== ").append(title).append(" ====")
               .append(System.lineSeparator());
    }

    @Override
    public void visit(PatientReportElement patientElement) {
        builder.append("Patient ID: ").append(nullSafe(patientElement.getPatient().getPatientId())).append(System.lineSeparator())
               .append("Name      : ").append(nullSafe(patientElement.getPatient().getName())).append(System.lineSeparator())
               .append("Age       : ").append(patientElement.getPatient().getAge()).append(System.lineSeparator())
               .append("Gender    : ").append(patientElement.getPatient().getGender()).append(System.lineSeparator())
               .append("Contact   : ").append(nullSafe(patientElement.getPatient().getContact())).append(System.lineSeparator())
               .append("Email     : ").append(nullSafe(patientElement.getPatient().getEmail())).append(System.lineSeparator())
               .append("Address   : ").append(nullSafe(patientElement.getPatient().getAddress())).append(System.lineSeparator())
               .append("----------------------------------------").append(System.lineSeparator());
    }

    @Override
    public void visit(AppointmentReportElement appointmentElement) {
        builder.append("Appointment ID: ").append(nullSafe(appointmentElement.getAppointment().getAppointmentId())).append(System.lineSeparator())
               .append("Patient       : ").append(nullSafe(appointmentElement.getPatient().getName())).append(System.lineSeparator())
               .append("Doctor        : ").append(nullSafe(appointmentElement.getDoctor().getName())).append(System.lineSeparator())
               .append("When          : ").append(String.valueOf(appointmentElement.getAppointment().getAppointmentDate())).append(System.lineSeparator())
               .append("Description   : ").append(nullSafe(appointmentElement.getAppointment().getDescription())).append(System.lineSeparator())
               .append("----------------------------------------").append(System.lineSeparator());
    }

    public String build() {
        return builder.toString();
    }

    private String nullSafe(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
