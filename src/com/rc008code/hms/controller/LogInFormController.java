package com.rc008code.hms.controller;

import com.rc008code.hms.auth.decorators.service.BasicAuthenticator;
import com.rc008code.hms.auth.decorators.service.PasswordManager;
import com.rc008code.hms.auth.handlers.model.ValidationResult;
import com.rc008code.hms.auth.handlers.services.LengthValidator;
import com.rc008code.hms.auth.handlers.services.NotNullValidator;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogInFormController {
    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void onLogInAction(ActionEvent actionEvent) throws IOException {
        new CommonUtil().setUi(context,"DoctorDashboardForm");
    }
}
