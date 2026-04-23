package com.rc008code.hms.reports.api;

import com.rc008code.hms.reports.element.PatientReportElement;
import com.rc008code.hms.reports.element.AppointmentReportElement;

public interface ReportVisitor {
    void visit(PatientReportElement patientElement);
    void visit(AppointmentReportElement appointmentElement);
}
