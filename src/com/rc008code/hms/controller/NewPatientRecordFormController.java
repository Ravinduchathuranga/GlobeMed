package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NewPatientRecordFormController {

    public AnchorPane context;

    public void onBackAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context,"DoctorDashboardForm");
    }
}
