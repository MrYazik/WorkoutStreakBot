package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class InTrainers {
    SelectTrainersVBOX backController;

    // Поля FXML
    @FXML
    Label name_trainers; // заголовок тренеровки
    @FXML
    Label name_ex; // ткущая тренеровка, имя упражнения
    @FXML
    ImageView image_ex; // Картинка изображения
    @FXML
    ProgressBar comp_ap; // Прогресс выполненных подходов
    @FXML
    ProgressBar proc_trainers; // Прогресс тренеровки
    @FXML
    Button done;
    @FXML
    Button back;

    public void setBackcontroller(SelectTrainersVBOX backController)
    {
        this.backController = backController;
    }
}
