package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
                btnMoreInfo.setOnAction(event -> {
                    try {
//                        PatientProfileFormController controller = new PatientProfileFormController();
//                        new CommonUtil().setUi(context, "PatientProfileForm");
//                        controller.initialize(patient);

                        // Load the PatientProfileForm FXML and get its controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rc008code/hms/view/forms/PatientProfileForm.fxml"));
                        Parent view = loader.load();
                        PatientProfileFormController controller = loader.getController();
                        controller.setPatientDetails(patient);
                        // Set the view into the current context
                        context.getChildren().setAll(view);
                        AnchorPane.setTopAnchor(view, 0.0);
                        AnchorPane.setRightAnchor(view, 0.0);
                        AnchorPane.setBottomAnchor(view, 0.0);
                        AnchorPane.setLeftAnchor(view, 0.0);
                    } catch (IOException | SQLException e) {
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
