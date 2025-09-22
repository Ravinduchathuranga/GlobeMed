package com.rc008code.hms.controller;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.PatientBo;
import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class PatientProfileFormController {
    public AnchorPane context;
    public Text txtPatientId;
    public Text txtPatientName;
    public Text txtPatientAge;
    public Text txtPatientContactNo;
    public Text txtPatientAddress;
    public Text txtPatientEmail;


    private PatientBo patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);

    public void initialize() {

    }

    // pass data from doctorDashboard to patientProfile
    public void setPatientDetails(PatientDto patientDto) throws SQLException {
        txtPatientId.setText(patientDto.getPatientId());
        txtPatientName.setText(patientDto.getName());
        txtPatientAge.setText(String.valueOf(patientDto.getAge()));
        txtPatientContactNo.setText(String.valueOf(patientDto.getContact()));
        txtPatientAddress.setText(patientDto.getAddress());
        txtPatientEmail.setText(patientDto.getEmail());

    }


    public void onClickHome(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context,"DoctorDashboardForm");
    }
}
