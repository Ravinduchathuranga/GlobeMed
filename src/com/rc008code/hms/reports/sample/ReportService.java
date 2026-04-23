package com.rc008code.hms.reports.sample;

import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.reports.element.PatientReportElement;

import java.util.List;


public class ReportService {


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
