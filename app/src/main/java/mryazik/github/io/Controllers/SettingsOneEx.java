package mryazik.github.io.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import mryazik.github.io.App;

import java.io.File;

public class SettingsOneEx {
    // @FXML поля
    @FXML
    Label ex_name;

    @FXML
    Button paste_image;
    @FXML
    ImageView image;

    SelectTrainersVBOX backController;

    public void setBackcontroller(SelectTrainersVBOX backController)
    {
        this.backController = backController;
    }
    public void initOneEx(String name_ex)
    {
        ex_name.setText(name_ex);
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
    }
}
