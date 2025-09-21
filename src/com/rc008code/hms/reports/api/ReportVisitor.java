package com.rc008code.hms.reports.api;

import com.rc008code.hms.reports.service.PatientReportElement;

public interface ReportVisitor {
    void visit(PatientReportElement patientElement);
}
