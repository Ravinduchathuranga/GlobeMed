package com.rc008code.hms.controller;

import com.rc008code.hms.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NurseDashboardFormController {
    public AnchorPane context;

    public void OnLogOutAction(ActionEvent event) throws IOException {
        new CommonUtil().setUi(context,"LogInForm");
    }
}
