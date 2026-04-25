package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import mryazik.github.io.App;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import mryazik.github.io.Classes.mainWindow;
import mryazik.github.io.Classes.vBoxInMainWindow;

// Отвечает за то чтоб управлять кнопками MainVBOX:

public class MainVBOXController {
    @FXML private Button stats_button;
    @FXML private Button trainers_button;
    mainWindow window;
    VBox rootLayout;

    private Logger logger = Logger.getLogger(MainVBOXController.class.getName());



    public void initialize()
    {
        stats_button.setOnAction(event -> {
            System.out.println("Log: button \"Статистика\"");
        });
        trainers_button.setOnAction(event -> {
            try {
                System.out.println("Log: button \"Мои тренеровки\"");

                vBoxInMainWindow mainVbox = new vBoxInMainWindow();
                FXMLLoader loader = mainVbox.loadVBox("TrainersVBOX.fxml");

                TrainersVBOX controller = loader.getController();
                controller.initList();
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "Не удалось сменить rootLayout", e);
            }
        });
    }
}
