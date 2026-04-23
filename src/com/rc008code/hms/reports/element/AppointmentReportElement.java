package com.rc008code.hms.reports.element;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.reports.api.ReportElement;
import com.rc008code.hms.reports.api.ReportVisitor;


public class AppointmentReportElement implements ReportElement {
    private final AppointmentDto appointment;
    private final PatientDto patient;
    private final DoctorDto doctor;

    public AppointmentReportElement(AppointmentDto appointment, PatientDto patient, DoctorDto doctor) {
        this.appointment = appointment;
        this.patient = patient;
        this.doctor = doctor;
    }

    public AppointmentDto getAppointment() {
        return appointment;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
