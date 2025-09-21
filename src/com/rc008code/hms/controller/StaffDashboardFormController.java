package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StaffDashboardFormController {
    public AnchorPane context;

    public void initialize() {

    }
    public void OnClickToPatient(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"PatientForm");
    }
}
