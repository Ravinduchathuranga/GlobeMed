package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.NurseBo;
import com.rc008code.hms.dto.NurseDto;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.NurseTM;
import com.rc008code.hms.reports.service.StaffOnboardingNotifier;
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

public class NursesFormController {
    // Root context
    public AnchorPane context;

    // Input fields
    public TextField txtName;
    public TextField txtContact;
    public ComboBox<Departments> cmbDepartment;
    public TextField txtEmail;
    public Button btnSave;
    public TextField txtSearch; // from FXML

    // TableView and columns
    public TableView<NurseTM> tblNurses;
    public TableColumn<NurseTM, String> colNurseId;
    public TableColumn<NurseTM, String> colName;
    public TableColumn<NurseTM, String> colContact;
    public TableColumn<NurseTM, String> colDepartment;
    public TableColumn<NurseTM, String> colEmail;
    public TableColumn<NurseTM, ButtonBar> colActions;

    private String searchText = "";
    private NurseDto selectedNurse;
    private final NurseBo nurseBo = BoFactory.getInstance().getBo(BoFactory.BoType.NURSE);

    public void initialize() {
        // Initialize table columns (null-safe like other forms)
        if (colNurseId != null) {
            colNurseId.setCellValueFactory(new PropertyValueFactory<>("nurseId"));
            colNurseId.setVisible(false); // Hide the ID column as it's managed by the backend
        }
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colContact != null) colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        if (colDepartment != null) colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        if (colActions != null) colActions.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        // bind search if available
        if (txtSearch != null) {
            txtSearch.textProperty().addListener((obs, oldV, newV) -> {
                searchText = newV == null ? "" : newV.trim();
                loadAllNurses();
            });
        }

        loadAllDepartments();
        loadAllNurses();
    }

    private void loadAllDepartments() {
        ObservableList<Departments> departmentsList = FXCollections.observableArrayList(Departments.values());
        cmbDepartment.setItems(departmentsList);
    }

    public void onSaveNurseAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Nurse")) {
            try {
                NurseDto nurseDto = new NurseDto(
                        "N" + UUID.randomUUID().toString().substring(0, 5),
                        txtName.getText().trim(),
                        cmbDepartment.getValue(),
                        txtContact.getText().trim(),
                        txtEmail.getText().toLowerCase().trim(),
                        UUID.randomUUID().toString()
                );

                boolean isSaved = nurseBo.create(nurseDto);
                if (isSaved) {
                    // Send onboarding email with generated password
                    StaffOnboardingNotifier.sendWelcome(nurseDto);

                    new Alert(Alert.AlertType.INFORMATION, "Nurse has been saved successfully!", ButtonType.CLOSE).show();
                    clearFields();
                    loadAllNurses();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed to save nurse. Please try again.", ButtonType.CLOSE).show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        } else {
            // Update existing nurse
            if (selectedNurse != null) {
                try {
                    NurseDto nurseDto = new NurseDto(
                            selectedNurse.getNurseId(),
                            txtName.getText().trim(),
                            cmbDepartment.getValue(),
                            txtContact.getText().trim(),
                            txtEmail.getText().toLowerCase().trim(),
                            selectedNurse.getPassword()
                    );

                    boolean isUpdated = nurseBo.update(nurseDto);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Nurse has been updated successfully!", ButtonType.CLOSE).show();
                        btnSave.setText("Save Nurse");
                        clearFields();
                        loadAllNurses();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed to update nurse. Please try again.", ButtonType.CLOSE).show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a nurse to update.", ButtonType.CLOSE).show();
            }
        }
    }

    private void loadAllNurses() {
        ObservableList<NurseTM> nurseTMObservableList = FXCollections.observableArrayList();
        try {
            List<NurseDto> allNurses = nurseBo.search(searchText);

            for (NurseDto nurseDto : allNurses) {
                ButtonBar buttonBar = new ButtonBar();
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                buttonBar.getButtons().addAll(updateButton, deleteButton);

                NurseTM tm = new NurseTM(
                        nurseDto.getNurseId(),
                        nurseDto.getName(),
                        nurseDto.getContact(),
                        nurseDto.getEmail(),
                        nurseDto.getDepartment().toString(),
                        buttonBar
                );

                // Set up delete button action
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this nurse?",
                            ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = nurseBo.delete(tm.getNurseId());
                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Nurse has been deleted successfully!", ButtonType.CLOSE).show();
                                loadAllNurses();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Failed to delete nurse. Please try again.", ButtonType.CLOSE).show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });

                // Set up update button action
                updateButton.setOnAction(event -> {
                    btnSave.setText("Update Nurse");
                    selectedNurse = nurseDto;
                    txtName.setText(tm.getName());
                    txtContact.setText(tm.getContact());
                    cmbDepartment.getSelectionModel().select(Departments.valueOf(tm.getDepartment()));
                    txtEmail.setText(tm.getEmail());
                });

                nurseTMObservableList.add(tm);
            }

            tblNurses.setItems(nurseTMObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void onClearFields(ActionEvent actionEvent) {
        clearFields();
        btnSave.setText("Save Nurse");
        selectedNurse = null;
    }

    private void clearFields() {
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        cmbDepartment.getSelectionModel().clearSelection();
    }

    public void OnBackToHome(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"AdminDashboardForm");
    }
}
