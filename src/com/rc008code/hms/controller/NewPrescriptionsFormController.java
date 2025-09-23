package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PrescriptionBo;
import com.rc008code.hms.dto.PrescriptionDto;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class NewPrescriptionsFormController {
    public AnchorPane context;
    public TextArea txtDiagnosis;
    public TextArea txtTreatment;

    private final PrescriptionBo prescriptionBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRESCRIPTION);
    private String patientId;
    private String medicalRecordId;

    public void initialize() {

    }

    public void setPatientDetails(String patientId,String medicalRecordId) {
        this.patientId = patientId;
        this.medicalRecordId = medicalRecordId;
    }

    public void OnSavePrescriptionAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String diagnosis = txtDiagnosis.getText();
        String treatments = txtTreatment.getText();

        PrescriptionDto prescriptionDto = new PrescriptionDto(
                "PR-" + UUID.randomUUID().toString().substring(0, 5),
                medicalRecordId,
                patientId,
                "PH-001",
                diagnosis,
                treatments,
                new java.util.Date()
        );

        boolean b = prescriptionBo.create(prescriptionDto);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Prescription Saved Successfully").show();
            new CommonUtil().setUi(context, "DoctorDashboardForm");
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save prescription").show();
        }

    }

    public void onBackAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context, "PatientDashboardForm");
    }
}
