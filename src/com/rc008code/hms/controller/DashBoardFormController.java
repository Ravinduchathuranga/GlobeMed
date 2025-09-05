package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane context;

    private CommonUtil commonUtil=new CommonUtil();
    public void onPatientManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context,"PatientForm");
    }

    public void onDoctorsManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context,"DoctorForm");
    }

    public void onNursesManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context,"NurseForm");
    }

    public void onPharmacistsManagementAction(ActionEvent actionEvent) throws IOException {
        commonUtil.setUi(context,"PharmacistForm");
    }

    public void onPatientAction(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"PatientForm");
    }
}
