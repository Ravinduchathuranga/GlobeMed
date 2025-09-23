package com.rc008code.hms.controller;

import com.rc008code.hms.auth.decorators.service.decorators.BasicAuthenticator;
import com.rc008code.hms.auth.decorators.service.model.ValidationResult;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogInFormController {
    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void onLogInAction(ActionEvent actionEvent) throws IOException {
        BasicAuthenticator authenticator = new BasicAuthenticator();
        ValidationResult authenticate = authenticator.authenticate(txtUserName.getText(), txtPassword.getText());
        if (authenticate.isValid()) {
            String message = authenticate.getMessage();
            switch (message) {
                case "admin":
                    new CommonUtil().setUi(context, "AdminDashboardForm");
                case "doctor":
                    new CommonUtil().setUi(context, "DoctorDashboardForm");
                case "nurse":
                    new CommonUtil().setUi(context, "NurseDashboardForm");
                case "pharmacist":
                    new CommonUtil().setUi(context, "PharmasistDashboardForm");
            }
        } else {
            new Alert(Alert.AlertType.ERROR, authenticate.getMessage()).show();
        }
    }
}
