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
import mryazik.github.io.Classes.vBoxInMainWindow;

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

    public void initialize()
    {
        create_ex.setOnAction(event -> {
            try {
                String name_trainers_fromTextFiled = name_trainers.getText();

                if (name_trainers_fromTextFiled.equals("")) // Если пустая строка
                {
                    vBoxInMainWindow.showAlert("Нету имени тренировки, чтоб добавить упражнение - нужно имя тренировки");
                } else
                {
                    CreateEx create_ex = new CreateEx();
                    create_ex.createWindow(backController);
                }
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Ошибка в загрузки настройки (создания упражнения)", ex);
            }

        });

        back.setOnAction(event -> {
            vBoxInMainWindow.back();
        });
    }
}
