package com.rc008code.hms.controller;

import com.rc008code.hms.appointments.builder.AppointmentDtoBuilder;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.AppointmentBo;
import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.enums.AppointmentStatus;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.UUID;

public class PatientAppointmentFormController {
    public AnchorPane context;
    public TextField txtPatientId;
    public TextField txtDoctorId;
    public DatePicker dpDate;
    public TextField txtTime; // HH:mm
    public TextArea txtDescription;
    public Button btnSave;

    private final AppointmentBo appointmentBo = BoFactory.getInstance().getBo(BoFactory.BoType.APPOINTMENT);

    public void onSaveAppointment(ActionEvent actionEvent) {
        try {
            // Basic validation
            String patientId = txtPatientId.getText().trim();
            String doctorId = txtDoctorId.getText().trim();
            LocalDate date = dpDate.getValue();
            String timeStr = txtTime.getText().trim();
            if (patientId.isEmpty() || doctorId.isEmpty() || date == null || timeStr.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill Patient ID, Doctor ID, Date and Time.", ButtonType.CLOSE).show();
                return;
            }

            LocalTime time;
            try {
                time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                new Alert(Alert.AlertType.WARNING, "Invalid time. Use 24h format HH:mm (e.g., 14:30)", ButtonType.CLOSE).show();
                return;
            }

            LocalDateTime ldt = LocalDateTime.of(date, time);
            Date when = new Date(ldt.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());

            AppointmentDto dto = AppointmentDtoBuilder.builder()
                    .withId("A" + UUID.randomUUID().toString().substring(0, 6))
                    .withPatientId(patientId)
                    .withDoctorId(doctorId)
                    .at(when)
                    .withDescription(txtDescription.getText() == null ? "" : txtDescription.getText().trim())
                    .withStatus(AppointmentStatus.PENDING)
                    .build();

            boolean ok = appointmentBo.create(dto);
            if (ok) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment created successfully.", ButtonType.CLOSE).show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Could not create appointment. Try again.", ButtonType.CLOSE).show();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void onClear(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        if (txtPatientId != null) txtPatientId.clear();
        if (txtDoctorId != null) txtDoctorId.clear();
        if (dpDate != null) dpDate.setValue(null);
        if (txtTime != null) txtTime.clear();
        if (txtDescription != null) txtDescription.clear();
    }
}
