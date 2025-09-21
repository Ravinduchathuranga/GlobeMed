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

   public void initialize() {

   }

    public void OnBackToHome(ActionEvent event) {
    }
}
