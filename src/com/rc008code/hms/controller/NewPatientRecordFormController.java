package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.MedicalRecordBo;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.MedicalRecordDto;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class NewPatientRecordFormController {

    public AnchorPane context;
    public TextArea txtDiagnosis;
    public TextArea txtTreatment;
    private final MedicalRecordBo medicalRecordBo = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICALRECORD);

    private String patientId;
    private String medicalRecordId;

    public void setPatientDetails(String patientId) {
        this.patientId = patientId;
    }


    public void OnSaveRecordAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String diagnosis = txtDiagnosis.getText();
        String treatments = txtTreatment.getText();

        MedicalRecordDto medicalRecordDto = new MedicalRecordDto(
                medicalRecordId = "MED" + UUID.randomUUID().toString().substring(0, 5),
                patientId,
                "D-001",
                diagnosis,
                treatments,
                new java.util.Date()
        );


        boolean b = medicalRecordBo.create(medicalRecordDto);
        if (b) {
            txtDiagnosis.clear();
            txtTreatment.clear();
            new Alert(Alert.AlertType.INFORMATION, "Record Saved Successfully").show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rc008code/hms/view/forms/NewPrescriptionsForm.fxml"));
            Parent parent = loader.load();
            NewPrescriptionsFormController controller = loader.getController();
            controller.setPatientDetails(patientId,medicalRecordId);

            context.getChildren().setAll(parent);
            AnchorPane.setTopAnchor(parent, 0.0);
            AnchorPane.setRightAnchor(parent, 0.0);
            AnchorPane.setBottomAnchor(parent, 0.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
        }
    }


    public void onBackAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context, "DoctorDashboardForm");
    }
}
