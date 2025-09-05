package com.rc008code.hms.builders;

import com.rc008code.hms.enums.AppointmentStatus;

import java.util.Date;

public class AppointmentBuilder {
    private final String appointmentId;
    private final String patientId;
    private final String doctorId;
    private final String adminStaffId;
    private final Date appointmentDate;
    private final AppointmentStatus status;

    private AppointmentBuilder(Builder builder) {
        this.appointmentId = builder.appointmentId;
        this.patientId = builder.patientId;
        this.doctorId = builder.doctorId;
        this.adminStaffId = builder.adminStaffId;
        this.appointmentDate = builder.appointmentDate;
        this.status = builder.status;
    }

    // Getters (no setters as the class is immutable)
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getAdminStaffId() {
        return adminStaffId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public static class Builder {
        // Required parameters
        private final String appointmentId;
        private final String patientId;
        private final String doctorId;

        // Optional parameters - initialized to default values
        private String adminStaffId = "";
        private Date appointmentDate = new Date();
        private AppointmentStatus status = AppointmentStatus.PENDING;

        public Builder(String appointmentId, String patientId, String doctorId) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
        }

        public Builder adminStaffId(String adminStaffId) {
            this.adminStaffId = adminStaffId;
            return this;
        }

        public Builder appointmentDate(Date appointmentDate) {
            this.appointmentDate = new Date(appointmentDate.getTime()); // Defensive copy
            return this;
        }

        public Builder status(AppointmentStatus status) {
            this.status = status;
            return this;
        }

        public AppointmentBuilder build() {
            return new AppointmentBuilder(this);
        }
    }
}
