package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PharmacistBo;
import com.rc008code.hms.dto.PharmacistDto;
import com.rc008code.hms.util.CommonUtil;
import com.rc008code.hms.view.tableModels.PharmacistTM;
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

public class PharmacistFormController {
    // Root context
    public AnchorPane context;

    // Input fields
    public TextField txtName;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtSearch;

    // TableView and its columns
    public TableView<PharmacistTM> tblPharmacists;
    public TableColumn<PharmacistTM, String> colName;
    public TableColumn<PharmacistTM, String> colContact;
    public TableColumn<PharmacistTM, String> colEmail;
    public TableColumn<PharmacistTM, ButtonBar> colActions;

    public Button btnSave;
    private String searchText = "";
    private PharmacistDto selectedPharmacist;
    private final PharmacistBo pharmacistBo = BoFactory.getInstance().getBo(BoFactory.BoType.PHARMACIST);

    public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        // Load all pharmacists
        loadAllPharmacists();

        // Add search listener
//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//            searchText = newValue.trim();
//            loadAllPharmacists();
//        });
    }

    public void OnSavePharmacistAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Pharmacist")) {
            try {
                if (validateFields()) {
                    PharmacistDto pharmacistDto = new PharmacistDto(
                            "P" + UUID.randomUUID().toString().substring(0, 5),
                            txtName.getText().trim(),
                            txtContact.getText().trim(),
                            txtEmail.getText().toLowerCase().trim(),
                            UUID.randomUUID().toString() // Temporary password, will be managed by backend
                    );
                    boolean isSaved = pharmacistBo.create(pharmacistDto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "Pharmacist has been saved successfully!", ButtonType.CLOSE).show();
                        clearFields();
                        loadAllPharmacists();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed to save pharmacist. Please try again.", ButtonType.CLOSE).show();
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        } else {
            // Update existing pharmacist
            if (selectedPharmacist != null) {
                try {
                    if (validateFields()) {
                        PharmacistDto pharmacistDto = new PharmacistDto(
                                selectedPharmacist.getPharmacistId(),
                                txtName.getText().trim(),
                                txtContact.getText().trim(),
                                txtEmail.getText().toLowerCase().trim(),
                                selectedPharmacist.getPassword() // Keep existing password
                        );
                        boolean isUpdated = pharmacistBo.update(pharmacistDto);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.INFORMATION, "Pharmacist has been updated successfully!", ButtonType.CLOSE).show();
                            btnSave.setText("Save Pharmacist");
                            clearFields();
                            loadAllPharmacists();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Failed to update pharmacist. Please try again.", ButtonType.CLOSE).show();
                        }
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a pharmacist to update.", ButtonType.CLOSE).show();
            }
        }
    }

    private void loadAllPharmacists() {
        ObservableList<PharmacistTM> pharmacistTMObservableList = FXCollections.observableArrayList();
        try {
            List<PharmacistDto> allPharmacists = pharmacistBo.search(searchText);

            for (PharmacistDto pharmacistDto : allPharmacists) {
                ButtonBar buttonBar = new ButtonBar();
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                buttonBar.getButtons().addAll(deleteButton, updateButton);

                PharmacistTM tm = new PharmacistTM(
                        pharmacistDto.getPharmacistId(),
                        pharmacistDto.getName(),
                        pharmacistDto.getContactNumber(),
                        pharmacistDto.getEmail(),
                        buttonBar
                );

                // Delete button action
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this pharmacist?",
                            ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = pharmacistBo.delete(tm.getPharmacistId());
                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Pharmacist has been deleted successfully!", ButtonType.CLOSE).show();
                                loadAllPharmacists();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Failed to delete pharmacist. Please try again.", ButtonType.CLOSE).show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });

                // Update button action
                updateButton.setOnAction(event -> {
                    btnSave.setText("Update Pharmacist");
                    selectedPharmacist = pharmacistDto;
                    txtName.setText(tm.getName());
                    txtContact.setText(tm.getContact());
                    txtEmail.setText(tm.getEmail());
                });

                pharmacistTMObservableList.add(tm);
            }
            tblPharmacists.setItems(pharmacistTMObservableList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void OnClearFields(ActionEvent actionEvent) {
        btnSave.setText("Save Pharmacist");
        clearFields();
    }

    private void clearFields() {
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        selectedPharmacist = null;
    }

    private boolean validateFields() {
        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter pharmacist name.", ButtonType.CLOSE).show();
            txtName.requestFocus();
            return false;
        }
        if (txtContact.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter contact number.", ButtonType.CLOSE).show();
            txtContact.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().isEmpty() || !txtEmail.getText().contains("@")) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid email address.", ButtonType.CLOSE).show();
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }

    public void OnBackToHome(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"DashboardForm");
    }
}
