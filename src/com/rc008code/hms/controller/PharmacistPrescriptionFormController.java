package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.business.custom.PrescriptionBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.dto.PrescriptionDto;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PharmacistPrescriptionFormController {
    public AnchorPane context;
    public Text txtPatientId;
    public Text txtPatientName;
    public Text txtPatientMedication;
    public Text txtPatientDosage;

    private final PrescriptionBo prescriptionBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRESCRIPTION);

    public void setPatientDetails(PatientDto patientDto) {
        txtPatientId.setText(patientDto.getPatientId());
        txtPatientName.setText(patientDto.getName());
        loadPrescriptionDetails(patientDto.getPatientId());
    }

    private void loadPrescriptionDetails(String patientId) {

        try {
            List<PrescriptionDto> byPatientId = prescriptionBo.findByPatientId(patientId);

            for (PrescriptionDto prescriptionDto : byPatientId) {
                txtPatientMedication.setText(prescriptionDto.getMedication());
                txtPatientDosage.setText(prescriptionDto.getDosage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void onIssueMedicationAction(ActionEvent event) throws IOException {
        new Alert(Alert.AlertType.INFORMATION, "Successfully issued medication").show();
        new CommonUtil().setUi(context,"PharmasistDashboardForm");
    }

    public void OnBackAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context, "PharmasistDashboardForm");
    }

}
