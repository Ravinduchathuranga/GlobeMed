package com.rc008code.hms.controller;


import com.rc008code.hms.entity.Patient;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.util.AlertBuilder;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    public ToggleGroup genderGrop;
    public TextField txtSearchPatients;

    // TableView and its columns
    public TableView tblePatients;
    public TableColumn columnName;
    public TableColumn columnAddress;
    public TableColumn columnAge;
    public TableColumn columnGender;
    public TableColumn columnContactNo;
    public TableColumn columnEmail;
    public TableColumn columnOptions;

    // private properties
    private CommonUtil commonUtil = new CommonUtil();

    public void initialize() {
        loadAllPatients();
    }

    private void loadAllPatients(){

    }
    // patient creation
    public void onCreateAction(ActionEvent actionEvent) {
//        try {
//
//            Patient patient = new Patient(
//                    UUID.randomUUID().toString(),
//                    txtName.getText(),
//                    txtAddress.getText(),
//                    Integer.parseInt(txtAge.getText()),
//                    rbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE,
//                    txtContactNo.getText(),
//                    txtEmail.getText()
//            );
//            DbConnection dbConnection = new DbConnection();
//            boolean isSaved = dbConnection.createPatient(patient);
//            if (isSaved) {
//                new Alert(Alert.AlertType.INFORMATION, "Patient Created Successfully", ButtonType.OK).show();
//                clearFields();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
}
