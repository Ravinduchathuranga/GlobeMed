package com.rc008code.hms.reports.service;

import com.rc008code.hms.reports.api.ReportVisitor;
import com.rc008code.hms.reports.element.PatientReportElement;
import com.rc008code.hms.reports.util.MailManager;

public class EmailReportVisitor implements ReportVisitor {
    @Override
    public void visit(PatientReportElement patientElement) {
        try {
            String subject = "Welcome Email";
            String body = "Dear " + patientElement.getPatient().getName() + ",\n\n"
                    + "Welcome to Global_med! We're excited to have you join us.\n\n"
                    + "Here are your account details to get started:\n"
                    + "User ID: " + patientElement.getPatient().getPatientId() + "\n"
                    + "Email: " + patientElement.getPatient().getEmail() + "\n"
                    + "Please use this information to sign in for the first time. For security purposes, we recommend changing your password after your initial login.\n\n"
                    + "If you have any questions or need assistance, feel free to reach out to our support team.\n\n"
                    + "Best regards,\n"
                    + "Global_med Team";

            MailManager.sendMail(patientElement.getPatient().getEmail(), subject, body);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
