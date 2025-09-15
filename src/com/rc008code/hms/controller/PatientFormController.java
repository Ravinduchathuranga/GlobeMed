package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.util.AlertBuilder;
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
import java.util.Optional;
import java.util.UUID;

public class PatientFormController {

    // Root context
    public AnchorPane context;

    // Input fields
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtContactNo;
    public TextField txtEmail;
    public RadioButton rbtnMale;
    public RadioButton rbtnFemale;
    public ToggleGroup genderGroup;

    // TableView and columns
    public TableView<PatientTM> tblPatients;
    public TableColumn<PatientTM, String> colPatientId;
    public TableColumn<PatientTM, String> colName;
    public TableColumn<PatientTM, Integer> colAge;
    public TableColumn<PatientTM, Gender> colGender;
    public TableColumn<PatientTM, String> colAddress;
    public TableColumn<PatientTM, String> colContact;
    public TableColumn<PatientTM, String> colEmail;
    public TableColumn<PatientTM, ButtonBar> colAction;

    public TextField txtSearchPatients;
    public Button btnSave;

    // Private properties
    private final CommonUtil commonUtil = new CommonUtil();
    private final PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
    private PatientDto selectedPatient;

    public void initialize() {
        // Initialize table columns
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        // Set default gender
        rbtnMale.setSelected(true);

        // Load all patients
        loadAllPatients();
    }

    private void loadAllPatients() {
        ObservableList<PatientTM> patientList = FXCollections.observableArrayList();
        try {
            List<PatientDto> allPatients = patientBo.readAll();

            for (PatientDto patient : allPatients) {
                ButtonBar buttonBar = new ButtonBar();
                Button btnEdit = new Button("Edit");
                Button btnDelete = new Button("Delete");
                buttonBar.getButtons().addAll(btnEdit, btnDelete);

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

                // Set up delete button action
                btnDelete.setOnAction(event -> handleDeletePatient(tm));

                // Set up edit button action
                btnEdit.setOnAction(event -> {
                    btnSave.setText("Update Patient");
                    selectedPatient = patient;
                    populatePatientFields(patient);
                });

                patientList.add(tm);
            }

            tblPatients.setItems(patientList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load patients: " + e.getMessage(), ButtonType.OK).show();
        }
    }
    public void onBackToHomeAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context,"Dashboard");
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtAge.clear();
        txtContactNo.clear();
        txtEmail.clear();
    }

    public void onSaveAction(ActionEvent actionEvent) {
        if (!validateFields()) {
            return;
        }
        try {
            PatientDto patientDto = new PatientDto(
                selectedPatient != null ? selectedPatient.getPatientId() : "P" + UUID.randomUUID().toString().substring(0, 5),
                txtName.getText().trim(),
                txtAddress.getText().trim(),
                Integer.parseInt(txtAge.getText().trim()),
                rbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE,
                txtContactNo.getText().trim(),
                txtEmail.getText().toLowerCase().trim()
            );

            boolean isSaved;
            if (selectedPatient == null) {
                isSaved = patientBo.create(patientDto);
            } else {
                isSaved = patientBo.update(patientDto);
            }

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,
                    selectedPatient == null ? "Patient saved successfully!" : "Patient updated successfully!",
                    ButtonType.OK).show();
                clearFields();
                loadAllPatients();
                btnSave.setText("Save Patient");
                selectedPatient = null;
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save patient. Please try again.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    public void onClearAction(ActionEvent actionEvent) {
        clearFields();
        btnSave.setText("Save Patient");
        selectedPatient = null;
    }

    public void onSearchAction(ActionEvent actionEvent) {
        // Implementation for search functionality
        // This can be enhanced based on specific search requirements
        String searchText = txtSearchPatients.getText().trim();
        if (searchText.isEmpty()) {
            loadAllPatients();
            return;
        }

        // Filter the current table items based on search text
        ObservableList<PatientTM> filteredList = FXCollections.observableArrayList();
        for (PatientTM patient : tblPatients.getItems()) {
            if (patient.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                patient.getPatientId().toLowerCase().contains(searchText.toLowerCase()) ||
                patient.getContact().contains(searchText) ||
                patient.getEmail().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(patient);
            }
        }
        tblPatients.setItems(filteredList);
    }

    private void handleDeletePatient(PatientTM patient) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Patient");
        alert.setContentText("Are you sure you want to delete " + patient.getName() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean isDeleted = patientBo.delete(patient.getPatientId());
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!", ButtonType.OK).show();
                        loadAllPatients();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete patient.", ButtonType.OK).show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Error deleting patient: " + e.getMessage(), ButtonType.OK).show();
                }
            }
        });
    }

    private void populatePatientFields(PatientDto patient) {
        txtName.setText(patient.getName());
        txtAddress.setText(patient.getAddress());
        txtAge.setText(String.valueOf(patient.getAge()));
        txtContactNo.setText(patient.getContact());
        txtEmail.setText(patient.getEmail());

        if (patient.getGender() == Gender.MALE) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }
    }

    private boolean validateFields() {
        if (txtName.getText().trim().isEmpty() ||
            txtAddress.getText().trim().isEmpty() ||
            txtAge.getText().trim().isEmpty() ||
            txtContactNo.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "Please fill in all required fields.", ButtonType.OK).show();
            return false;
        }

        try {
            int age = Integer.parseInt(txtAge.getText().trim());
            if (age <= 0 || age > 120) {
                new Alert(Alert.AlertType.WARNING, "Please enter a valid age (1-120).", ButtonType.OK).show();
                return false;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid number for age.", ButtonType.OK).show();
            return false;
        }

        if (!txtEmail.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid email address.", ButtonType.OK).show();
            return false;
        }

        if (!txtContactNo.getText().trim().matches("^\\+?[0-9]{10,15}$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid contact number (10-15 digits, may start with +).", ButtonType.OK).show();
            return false;
        }

        return true;
    }

    public void OnBackToHome(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"AdminDashboardForm");
    }
}
