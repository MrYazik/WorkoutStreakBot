package mryazik.github.io.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import mryazik.github.io.App;
import mryazik.github.io.Classes.Trainers;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsOneEx {
    Logger logger = Logger.getLogger(getClass().getName());

    // @FXML поля
    @FXML
    Label ex_name;

    @FXML
    Button paste_image;
    @FXML
    Button done;
    @FXML Button back;

    @FXML
    ImageView image;

    // Поля для ввода
    @FXML
    TextField count_ap;
    @FXML
    TextField count_comp;

    SelectTrainersVBOX backController;

    String name_trainers;
    String name_ex;

    public void setBackcontroller(SelectTrainersVBOX backController)
    {
        this.backController = backController;
    }
    public void initOneEx(String name_trainers, String name_ex, int count_ap, int count_comp)
    {
        this.name_trainers = name_trainers;

        ex_name.setText(name_ex);
        this.name_ex = name_ex;
        this.count_ap.setText(Integer.toString(count_ap));
        this.count_comp.setText(Integer.toString(count_comp));
    }

    public void initialize()
    {
        paste_image.setOnAction(action -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Установите изображение для упражнения");

            File selectedFile = fileChooser.showOpenDialog(backController.backController.app.primaryStage);
            Image image = new Image(selectedFile.toURI().toString());

            this.image.setImage(image);
        });

        // Кнопка готово
        done.setOnAction(event -> {
            AtomicInteger count_ap = new AtomicInteger(0);
            AtomicInteger count_comp = new AtomicInteger(0);

            // собираем новую инфу
            try {
                count_ap.set(Integer.parseInt(this.count_ap.getText()));
                count_comp.set(Integer.parseInt(this.count_comp.getText()));
            } catch (NumberFormatException e)
            {
                logger.log(Level.WARNING, "Строка введённоая пользователем - это строка", e);
            }

            Trainers.addEx(this.name_trainers, this.name_ex,
                    Trainers.sampleEx(count_ap.get(), count_comp.get(), 1)
                    );
        });

        back.setOnAction(event ->
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/maket/SelectedTrainersVBOX.fxml"));

                VBox rootLayout = loader.load();

                SelectTrainersVBOX controller = loader.getController();
                controller.setBackController(backController.backController);
                controller.initVBOX(backController.name_trainers_text, backController.ex_map);

                backController.backController.app.rootLayout.setCenter(rootLayout);
            } catch (IOException e)
            {
                logger.log(Level.WARNING, "Не удалось загрузить меню упражнения SelectTrainersVBOX.fxml", e);
            }
        });
    }
}
