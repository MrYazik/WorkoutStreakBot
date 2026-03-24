package mryazik.github.io;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class BaseController
{
//    private App appFX;

    @FXML
    private Button stats_button;

    @FXML
    public void initialize()
    {
        stats_button.setOnAction(event -> {
            System.out.println("lox");
        });
    }
//
//    public void setAppFX(App appFX) {
//        this.appFX = appFX;
//    }
}
