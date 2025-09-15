package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DoctorDashboardFormController {
    public AnchorPane context;
    public TableView tblPatients;
    public TableColumn colPatientName;
    public TableColumn colPatientId;
    public TableColumn colPatientAge;
    public TableColumn colPatientGender;
    public TableColumn colPatientAddress;
    public TableColumn colPatientContactNo;
    public TableColumn colPatientEmail;
    public TableColumn colPatientAction;


    private final PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);

    public void initialize() {
        loadAllPatients();
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPatientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colPatientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPatientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPatientContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colPatientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPatientAction.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
    }

    private void loadAllPatients() {
        ObservableList<PatientTM> patientList = FXCollections.observableArrayList();
        try {
            List<PatientDto> allPatients = patientBo.readAll();

            for (PatientDto patient : allPatients) {
                ButtonBar buttonBar = new ButtonBar();
                Button btnMoreInfo = new Button("More Info");
                buttonBar.getButtons().addAll(btnMoreInfo);

                PatientTM tm = new PatientTM(
                        patient.getPatientId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getAddress(),
                        patient.getGender(),
                        patient.getContact(),
                        patient.getEmail(),
                        buttonBar
                );
//                // Set up delete button action
//                btnDelete.setOnAction(event -> handleDeletePatient(tm));
//
//                // Set up edit button action
//                btnEdit.setOnAction(event -> {
//                    btnSave.setText("Update Patient");
//                    selectedPatient = patient;
//                    populatePatientFields(patient);
//                });

                btnMoreInfo.setOnAction(event->{
                    try {
                        new CommonUtil().setUi(context,"PatientProfileForm");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                patientList.add(tm);
            }
            tblPatients.setItems(patientList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load patients: " + e.getMessage(), ButtonType.OK).show();
        }
    }

}
