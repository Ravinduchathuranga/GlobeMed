package com.rc008code.hms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CommonUtil {
    // to change the UI from one to another
    // context - current window, location - the new window to be opened
    public void setUi(AnchorPane context, String location) throws IOException {
        URL resource = getClass().getResource("/com/rc008code/hms/view/forms/" + location + ".fxml");
        Stage stage = (Stage) context.getScene().getWindow();
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(resource)));
        stage.setTitle(location);
    }
}
