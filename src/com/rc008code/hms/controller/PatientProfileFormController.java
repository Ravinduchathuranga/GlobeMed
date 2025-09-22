package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.MedicalRecordBo;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.MedicalRecordDto;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.MedicalRecordTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientProfileFormController {
    public AnchorPane context;
    public Text txtPatientId;
    public Text txtPatientName;
    public Text txtPatientAge;
    public Text txtPatientContactNo;
    public Text txtPatientAddress;
    public Text txtPatientEmail;

    // table view and its columns
    public TableView tblRecordHistory;
    public TableColumn colRecordId;
    public TableColumn colDoctorName;
    public TableColumn colDignosis;
    public TableColumn colTreatment;
    public TableColumn colRecordDate;
    public TableColumn colAction;

    private final MedicalRecordBo medicalRecordBo = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICALRECORD);

    public void initialize() throws SQLException, ClassNotFoundException {
        colRecordId.setCellValueFactory(new PropertyValueFactory<>("record_id"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        colDignosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colTreatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        colRecordDate.setCellValueFactory(new PropertyValueFactory<>("record_date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
    }

    // pass data from doctorDashboard to patientProfile
    public void setPatientDetails(PatientDto patientDto) throws SQLException, ClassNotFoundException {
        txtPatientId.setText(patientDto.getPatientId());
        txtPatientName.setText(patientDto.getName());
        txtPatientAge.setText(String.valueOf(patientDto.getAge()));
        txtPatientContactNo.setText(String.valueOf(patientDto.getContact()));
        txtPatientAddress.setText(patientDto.getAddress());
        txtPatientEmail.setText(patientDto.getEmail());
        loadMedicalRecords(patientDto.getPatientId());

    }

    private void loadMedicalRecords(String patientId) throws SQLException, ClassNotFoundException {

        ObservableList<MedicalRecordTM> medicalRecordList = FXCollections.observableArrayList();
        try {
            List<MedicalRecordDto> allMedicalRecords = medicalRecordBo.findRecordByPatient(patientId);
            for (MedicalRecordDto record : allMedicalRecords) {

                ButtonBar buttonBar = new ButtonBar();
                Button btnMoreInfo = new Button("More Info");
                buttonBar.getButtons().addAll(btnMoreInfo);

                MedicalRecordTM tm = new MedicalRecordTM(
                        record.getRecordId(),
                        record.getDoctorId(),
                        record.getDiagnosis(),
                        record.getTreatment(),
                        record.getRecordDate(),
                        buttonBar
                );
                btnMoreInfo.setOnAction(event -> {
                    try {
                        new CommonUtil().setUi(context, "MedicalRecordForm");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                medicalRecordList.add(tm);
            }
            tblRecordHistory.setItems(medicalRecordList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickHome(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context, "DoctorDashboardForm");
    }
}
