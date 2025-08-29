package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
    private CommonUtil commonUtil=new CommonUtil();
    public void initialize() {
    }

    public void onCreateAction(ActionEvent actionEvent) {
    }

    public void onBackToHomeAction(ActionEvent actionEvent) {
    }
}
