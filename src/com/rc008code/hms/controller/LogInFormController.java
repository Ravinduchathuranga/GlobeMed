package com.rc008code.hms.controller;

import com.rc008code.hms.auth.decorators.service.decorators.BasicAuthenticator;
import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
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
        authenticator.authenticate(txtUserName.getText(), txtPassword.getText());
        new CommonUtil().setUi(context, "PharmasistDashboardForm");
    }
}
