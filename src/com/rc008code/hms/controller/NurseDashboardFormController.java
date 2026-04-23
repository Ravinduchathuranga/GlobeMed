package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class NurseDashboardFormController {
    public AnchorPane context;

    // Patients list UI controls
    public TableView<PatientTM> tblPatients; // wired from FXML
    public TableColumn<PatientTM, String> colPatientId;
    public TableColumn<PatientTM, String> colName;
    public TableColumn<PatientTM, Integer> colAge;
    public TableColumn<PatientTM, Gender> colGender;
    public TableColumn<PatientTM, String> colContact;
    public TableColumn<PatientTM, String> colEmail;
    public TableColumn<PatientTM, ButtonBar> colActions;
    public TextField txtSearch;

    private final PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
    private String searchText = "";

    public void initialize() {
        // Initialize columns if present
        if (colPatientId != null) colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colAge != null) colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        if (colGender != null) colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        if (colContact != null) colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        if (colActions != null) colActions.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        if (txtSearch != null) {
            txtSearch.textProperty().addListener((obs, o, n) -> {
                searchText = n == null ? "" : n.trim();
                loadPatientList();
            });
        }
        loadPatientList();
    }

    public void OnLogOutAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context,"LogInForm");
    }

    public void onPatientFormAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context,"NewPatientForm");
    }

    private void loadPatientList() {
        if (tblPatients == null) return;
        ObservableList<PatientTM> list = FXCollections.observableArrayList();
        try {
            List<PatientDto> patients = patientBo.search(searchText);
            for (PatientDto dto : patients) {
                ButtonBar bar = new ButtonBar();
                Button btnAddAppt = new Button("Add Appointment");
                bar.getButtons().add(btnAddAppt);

                PatientTM tm = new PatientTM(
                        dto.getPatientId(),
                        dto.getName(),
                        dto.getAge(),
                        dto.getAddress(),
                        dto.getGender(),
                        dto.getContact(),
                        dto.getEmail(),
                        bar
                );

                btnAddAppt.setOnAction(e -> {
                    // Pass patient id to appointment form and navigate
                    PatientAppointmentFormController.presetPatientId = dto.getPatientId();
                    try {
                        new CommonUtil().setUi(context, "PatientAppoinementForm");
                    } catch (IOException ex) {
                        new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE).show();
                    }
                });

                list.add(tm);
            }
            tblPatients.setItems(list);
        } catch (SQLException | ClassNotFoundException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE).show();
        }
    }
}
