package mryazik.github.io.Controllers;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import mryazik.github.io.App;
import mryazik.github.io.Classes.Excercise;
import mryazik.github.io.Classes.Trainers;
import mryazik.github.io.Classes.mainWindow;
import mryazik.github.io.Classes.vBoxInMainWindow;

// Отвечает за VBox с тренеровками: Создать тренеровку, список моих

public class TrainersVBOX {
    private static Logger logger = Logger.getLogger(Trainers.class.getName());

    @FXML
    public VBox list_vbox;

    public void initList()
    {
        Excercise trainers = Trainers.getFromFile();
        Map<String, Object> listTrainers = Objects.requireNonNull(trainers.getAllTrainings(), "TrainersVBOX.java: список тренеровок пустой");

        // Загружаем VBOX определённой тренеровки
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/maket/SelectedTrainersVBOX.fxml"));

        listTrainers.forEach((key, object) -> {
            Button btn = new Button(key);
            btn.getStyleClass().add("list_button_el");
            VBox.setMargin(btn, new Insets(0, 0, 15, 0));

            list_vbox.getChildren().add(
                    btn
            ); // добавляем кнопку

            // Добавляем функционал
            btn.setOnAction(event -> {
                try {
                    VBox rootLayout;
                    rootLayout = loader.load();

                    SelectTrainersVBOX controller = loader.getController();
                    controller.setBackController(this);
                    controller.initVBOX(key, (Map<String, Object>) object);

                    mainWindow.rootLayout.setCenter(rootLayout);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });

            logger.log(Level.INFO, "Created new button", key);

        });
    }

    @FXML Button back;
    @FXML 
    Button create_trainers;
    public void initialize()
    {
        back.setOnAction(event -> {
            try {
                vBoxInMainWindow.back();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        create_trainers.setOnAction(event -> {
            try {
                vBoxInMainWindow vBox = new vBoxInMainWindow();
                FXMLLoader vBoxLoader = vBox.loadVBox("CreateTrainers.fxml");
            } catch (Exception e) {
                logger.log(Level.WARNING, "Не удалось загрузить меню создания тренеровки", e);
            }
        });
    }
}
