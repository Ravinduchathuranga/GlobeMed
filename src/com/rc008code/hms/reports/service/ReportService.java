package com.rc008code.hms.reports.service;

import com.rc008code.hms.dto.PatientDto;

import java.util.List;

/**
 * Service that provides simple reporting features using the Visitor pattern.
 */
public class ReportService {

    /**
     * Generates a simple text report for the provided patients.
     * @param patients list of patients; nulls will be ignored
     * @return report content as String (never null)
     */
    public String generatePatientReport(List<PatientDto> patients) {
        SimpleReportVisitor visitor = new SimpleReportVisitor();
        visitor.startReport("Patients Report");
        if (patients != null) {
            for (PatientDto dto : patients) {
                if (dto == null) continue;
                new PatientReportElement(dto).accept(visitor);
            }
        }
        return visitor.build();
    }
}
