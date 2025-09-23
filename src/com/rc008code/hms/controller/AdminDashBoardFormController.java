package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashBoardFormController {
    public AnchorPane context;

    private CommonUtil commonUtil = new CommonUtil();

    public void onDoctorsManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context, "AdminDoctorForm");
    }

    public void onNursesManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context, "AdminNurseForm");
    }

    public void onPharmacistsManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context, "AdminPharmacistForm");
    }

    public void OnLogOutAction(ActionEvent event) throws IOException {
        commonUtil.setUi(context, "LogInForm");
    }
}
