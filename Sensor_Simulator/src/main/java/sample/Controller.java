package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Timer;


public class Controller {

    @FXML
    private Button buttonRead;

    @FXML
    private TextField textfieldDeviceId;

    @FXML
    private Label errorLabel;


    private int deviceId;

    @FXML
    void clickRead(ActionEvent event) throws InterruptedException {
        if(textfieldDeviceId.getText().isEmpty())
        {
            errorLabel.setVisible(true);
        }else{
            errorLabel.setVisible(false);
            deviceId = Integer.parseInt(textfieldDeviceId.getText());
            System.out.println(deviceId);
            new Timer().scheduleAtFixedRate(new ReadSensor(deviceId), 0, 5000);

            for (int i = 0; i < 3; i++) {
                Thread.sleep(5000);
            }
        }
    }
}
