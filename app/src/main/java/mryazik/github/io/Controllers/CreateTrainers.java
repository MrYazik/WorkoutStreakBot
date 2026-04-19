package mryazik.github.io.Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateTrainers {
    Logger logger = Logger.getLogger(getClass().getName());

    // FXML элементы
    @FXML
    TextField name_trainers;
    @FXML
    Button create_ex;
    @FXML
    Button done;
    @FXML
    Button back;

    String name_trainersText;

    public TrainersVBOX backController;

    public void setBackController (TrainersVBOX backController) {this.backController = backController;}

    public void init()
    {

    }

    public void initialize()
    {
        create_ex.setOnAction(event -> {
            try {
                String name_trainers_fromTextFiled = name_trainers.getText();

                if (name_trainers_fromTextFiled.equals("")) // Если пустая строка
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("WorkoutStreak");
                    alert.setContentText("Нету имени тренеровки, чтоб добавить упражнение - нужно имя тренеровки");
                    alert.showAndWait();
                } else
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/maket/SettingsOneEx.fxml"));

                    VBox rootLayout = loader.load();
                    SettingsOneEx controller = loader.getController();
                    controller.setBackcontroller(controller.backController);
                    controller.initOneEx(name_trainers_fromTextFiled, "", 0, 0, 0);

                    backController.app.rootLayout.setCenter(rootLayout);
                }


            
            } catch (IOException e) {
                logger.log(Level.WARNING, "Ошибка в загрузки настройки (создания упражнения)", e);
            }

        });
    }
}
