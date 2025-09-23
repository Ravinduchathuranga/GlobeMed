package com.rc008code.hms.reports.service;

import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.reports.api.ReportElement;
import com.rc008code.hms.reports.api.ReportVisitor;

public class PatientReportElement implements ReportElement {
    private final PatientDto patient;

    public PatientReportElement(PatientDto patient) {
        this.patient = patient;
    }

    public PatientDto getPatient() {
        return patient;
    }

    @Override
    public void accept(ReportVisitor visitor) {
        visitor.visit(this);
    }
}
