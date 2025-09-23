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
import java.util.Optional;
import java.util.UUID;

public class NewPatientFormController {

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

    private final PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
    private String searchText = "";
    private PatientDto selectedPatient;

    public void initialize() {
        // setup table columns
        if (colPatientId != null) colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colAge != null) colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        if (colGender != null) colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        if (colAddress != null) colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        if (colContact != null) colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        if (colAction != null) colAction.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        // search binding
        if (txtSearchPatients != null) {
            txtSearchPatients.textProperty().addListener((obs, oldV, newV) -> {
                searchText = newV == null ? "" : newV.trim();
                loadAllPatients();
            });
        }
        loadAllPatients();
    }

    public void OnSavePatientAction(ActionEvent actionEvent) {
        // Determine gender
        Gender gender = null;
        if (rbtnMale != null && rbtnMale.isSelected()) gender = Gender.MALE;
        else if (rbtnFemale != null && rbtnFemale.isSelected()) gender = Gender.FEMALE;

        try {
            int age = Integer.parseInt(txtAge.getText().trim());
            if (btnSave.getText().equalsIgnoreCase("Save Patient")) {
                PatientDto dto = new PatientDto(
                        "P" + UUID.randomUUID().toString().substring(0, 5),
                        txtName.getText().trim(),
                        txtAddress.getText().trim(),
                        age,
                        gender,
                        txtContactNo.getText().trim(),
                        txtEmail.getText().toLowerCase().trim()
                );
                boolean ok = patientBo.create(dto);
                if (ok) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient has been saved..", ButtonType.CLOSE).show();
                    clearFields();
                    loadAllPatients();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                }
            } else {
                if (selectedPatient != null) {
                    PatientDto dto = new PatientDto(
                            selectedPatient.getPatientId(),
                            txtName.getText().trim(),
                            txtAddress.getText().trim(),
                            age,
                            gender,
                            txtContactNo.getText().trim(),
                            txtEmail.getText().toLowerCase().trim()
                    );
                    boolean ok = patientBo.update(dto);
                    if (ok) {
                        new Alert(Alert.AlertType.INFORMATION, "Patient has been updated..", ButtonType.CLOSE).show();
                        btnSave.setText("Save Patient");
                        clearFields();
                        loadAllPatients();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Please select a patient to update..", ButtonType.CLOSE).show();
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid age value", ButtonType.CLOSE).show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    private void loadAllPatients() {
        ObservableList<PatientTM> list = FXCollections.observableArrayList();
        try {
            List<PatientDto> patients = patientBo.search(searchText);
            for (PatientDto dto : patients) {
                ButtonBar bar = new ButtonBar();
                Button btnDelete = new Button("Delete");
                Button btnUpdate = new Button("Update");
                bar.getButtons().addAll(btnDelete, btnUpdate);

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

                btnDelete.setOnAction(ev -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure whether do you want to delete this item?",
                            ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        try {
                            boolean deleted = patientBo.delete(tm.getPatientId());
                            if (deleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Patient has been deleted..", ButtonType.CLOSE).show();
                                loadAllPatients();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something went wrong and try again.", ButtonType.CLOSE).show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });

                btnUpdate.setOnAction(ev -> {
                    btnSave.setText("Update Patient");
                    selectedPatient = dto;
                    txtName.setText(tm.getName());
                    txtAddress.setText(tm.getAddress());
                    txtAge.setText(String.valueOf(tm.getAge()));
                    txtContactNo.setText(tm.getContact());
                    txtEmail.setText(tm.getEmail());
                    if (tm.getGender() == Gender.MALE) {
                        rbtnMale.setSelected(true);
                    } else if (tm.getGender() == Gender.FEMALE) {
                        rbtnFemale.setSelected(true);
                    } else {
                        if (genderGroup != null) genderGroup.selectToggle(null);
                    }
                });

                list.add(tm);
            }
            tblPatients.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void OnClearFields(ActionEvent actionEvent) {
        btnSave.setText("Save Patient");
        clearFields();
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtAge.clear();
        txtContactNo.clear();
        txtEmail.clear();
        if (genderGroup != null) genderGroup.selectToggle(null);
    }

    public void OnBackToHome(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context, "NurseDashboardForm");
    }
}
