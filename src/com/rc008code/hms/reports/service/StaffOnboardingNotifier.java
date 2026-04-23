package com.rc008code.hms.reports.service;

import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.dto.NurseDto;
import com.rc008code.hms.dto.PharmacistDto;
import com.rc008code.hms.reports.util.MailManager;

/**
 * Sends onboarding emails to staff users (Doctor, Nurse, Pharmacist)
 * with their login email and generated password.
 */
public class StaffOnboardingNotifier {

    public static void sendWelcome(DoctorDto d) {
        if (d == null || d.getEmail() == null || d.getEmail().isEmpty()) return;
        String subject = "Global_med Account Created (Doctor)";
        String body = "Dear Dr. " + safe(d.getName()) + ",\n\n" +
                "Your Global_med doctor account has been created.\n\n" +
                "Login details:\n" +
                "User ID : " + safe(d.getDoctorId()) + "\n" +
                "Email   : " + safe(d.getEmail()) + "\n" +
                "Password: " + safe(d.getPassword()) + "\n\n" +
                "For security, please change your password after your first sign-in.\n\n" +
                "Best regards,\nGlobal_med Team";
        try {
            MailManager.sendMail(d.getEmail(), subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendWelcome(NurseDto n) {
        if (n == null || n.getEmail() == null || n.getEmail().isEmpty()) return;
        String subject = "Global_med Account Created (Nurse)";
        String body = "Dear " + safe(n.getName()) + ",\n\n" +
                "Your Global_med nurse account has been created.\n\n" +
                "Login details:\n" +
                "User ID : " + safe(n.getNurseId()) + "\n" +
                "Email   : " + safe(n.getEmail()) + "\n" +
                "Password: " + safe(n.getPassword()) + "\n\n" +
                "For security, please change your password after your first sign-in.\n\n" +
                "Best regards,\nGlobal_med Team";
        try {
            MailManager.sendMail(n.getEmail(), subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendWelcome(PharmacistDto p) {
        if (p == null || p.getEmail() == null || p.getEmail().isEmpty()) return;
        String subject = "Global_med Account Created (Pharmacist)";
        String body = "Dear " + safe(p.getName()) + ",\n\n" +
                "Your Global_med pharmacist account has been created.\n\n" +
                "Login details:\n" +
                "User ID : " + safe(p.getPharmacistId()) + "\n" +
                "Email   : " + safe(p.getEmail()) + "\n" +
                "Password: " + safe(p.getPassword()) + "\n\n" +
                "For security, please change your password after your first sign-in.\n\n" +
                "Best regards,\nGlobal_med Team";
        try {
            MailManager.sendMail(p.getEmail(), subject, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String safe(Object o) {
        return o == null ? "" : String.valueOf(o);
    }
}
