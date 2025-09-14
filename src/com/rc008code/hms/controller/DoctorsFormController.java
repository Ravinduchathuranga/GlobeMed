package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.DoctorBo;
import com.rc008code.hms.dto.DoctorDto;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.DoctorTM;
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


public class DoctorsFormController {
    // Root context
    public AnchorPane context;

    // Input fields
    public TextField txtName;
    public TextField txtSpecialty;
    public TextField txtContact;
    public ComboBox<Departments> cmbDepartment;
    public TextField txtEmail;

    // TableView and its columns
    public TableView<DoctorTM> tblDoctors;
    public TableColumn<DoctorTM, String> colName;
    public TableColumn<DoctorTM, String> colSpeciality;
    public TableColumn<DoctorTM, String> colContact;
    public TableColumn<DoctorTM, Departments> colDepartment;
    public TableColumn<DoctorTM, String> colEmail;
    public TableColumn<DoctorTM, ButtonBar> colActions;

    public Button btnSave;
    private String searchText = "";
    private DoctorDto selectedDoctorId;
    private final DoctorBo doctorBo = BoFactory.getInstance().getBo(BoFactory.BoType.DOCTOR);

    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
        loadAllDepartments();
        loadAllDoctors();
    }

    // load all departments to combobox
    ObservableList<Departments> departmentsList = null;

    private void loadAllDepartments() {
        departmentsList = FXCollections.observableArrayList();
        for (Departments departments : Departments.values()) {
            departmentsList.add(departments);
        }
        cmbDepartment.setItems(departmentsList);
    }

    public void OnSaveDoctorAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Doctor")) {
            try {
                DoctorDto doctorDto = new DoctorDto(
                        "D" + UUID.randomUUID().toString().substring(0, 5),
                        txtName.getText().trim(),
                        txtSpecialty.getText().trim(),
                        txtContact.getText().trim(),
                        Departments.valueOf(cmbDepartment.getSelectionModel().getSelectedItem().toString()),
                        txtEmail.getText().toLowerCase().trim(),
                        UUID.randomUUID().toString()
                );
                boolean isSaved = doctorBo.create(doctorDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Doctor has been saved..", ButtonType.CLOSE).show();
                    clearFields();
                    loadAllDoctors();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        } else {
            if (selectedDoctorId != null) {
                try {
                    DoctorDto doctorDto = new DoctorDto(
                            selectedDoctorId.getDoctorId(),
                            txtName.getText().trim(),
                            txtSpecialty.getText().trim(),
                            txtContact.getText().trim(),
                            Departments.valueOf(cmbDepartment.getSelectionModel().getSelectedItem().toString()),
                            txtEmail.getText().toLowerCase().trim(),
                            selectedDoctorId.getDoctorId()
                    );
                    boolean isUpdated = doctorBo.update(doctorDto);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Doctor has been updated..", ButtonType.CLOSE).show();
                        btnSave.setText("Save Doctor");
                        clearFields();
                        loadAllDoctors();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..", ButtonType.CLOSE).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a doctor to update..", ButtonType.CLOSE).show();
            }
        }

    }

    private void loadAllDoctors() {
        ObservableList<DoctorTM> doctorTMObservableList = FXCollections.observableArrayList();
        try {
            List<DoctorDto> allDoctors = doctorBo.search(searchText);

            for (DoctorDto doctorDto : allDoctors) {

                ButtonBar bar = new ButtonBar();
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                bar.getButtons().addAll(deleteButton, updateButton);

                DoctorTM tm = new DoctorTM(
                        doctorDto.getDoctorId(),
                        doctorDto.getName(),
                        doctorDto.getSpecialty(),
                        doctorDto.getContact(),
                        doctorDto.getDepartment(),
                        doctorDto.getEmail(),
                        bar
                );

                deleteButton.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure whether do you want to delete this item?",
                            ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = doctorBo.delete(tm.getDoctorId());

                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Doctor has been deleted..", ButtonType.CLOSE).show();
                                loadAllDoctors();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something went wrong and try again.", ButtonType.CLOSE).show();
                            }

                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });
                // settings for update button and filling feeded data to text fields
                updateButton.setOnAction(event -> {
                    btnSave.setText("Update Doctor");
                    selectedDoctorId = doctorDto;
                    txtName.setText(tm.getName());
                    txtSpecialty.setText(tm.getSpecialization());
                    txtContact.setText(tm.getContact());
                    cmbDepartment.getSelectionModel().select(tm.getDepartment());
                    txtEmail.setText(tm.getEmail());
                });
                doctorTMObservableList.add(tm);
            }
            tblDoctors.setItems(doctorTMObservableList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void OnClearFields(ActionEvent actionEvent) {
        btnSave.setText("Save Doctor");
        clearFields();
    }

    private void clearFields() {
        txtName.clear();
        txtSpecialty.clear();
        txtContact.clear();
        txtEmail.clear();
        cmbDepartment.getSelectionModel().clearSelection();
    }

    public void OnBackToHome(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"DashboardForm");
    }
}
