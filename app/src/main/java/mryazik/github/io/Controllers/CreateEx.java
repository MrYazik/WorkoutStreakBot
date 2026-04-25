package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mryazik.github.io.Classes.Trainers;
import mryazik.github.io.Classes.mainWindow;
import mryazik.github.io.Classes.vBoxInMainWindow;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateEx {
    Logger logger = Logger.getLogger(CreateEx.class.getName());

    @FXML
    TextField name_ex;
    @FXML
    TextField count_ap;
    @FXML
    TextField count_comp;

    @FXML Button paste_impage;
    @FXML Button done;
    @FXML Button back;
    @FXML ImageView image;

    String name_trainers;

    public void init(String name_trainers)
    {
        this.name_trainers = name_trainers;
    }

    public void initialize()
    {
        // Работа с картинкой


        back.setOnAction(event -> {
            vBoxInMainWindow.back();
        });

        done.setOnAction(event -> {
            Trainers.createTrainers(name_trainers);

            try {
                Trainers.addEx(name_trainers, name_ex.getText().toString(),
                        Trainers.sampleEx(Integer.parseInt(count_ap.getText()),
                                            Integer.parseInt(count_comp.getText()),
                                            0)
                );

                vBoxInMainWindow.back();
            } catch (NumberFormatException e)
            {
                logger.log(Level.INFO, "Не удалось перевести количество подходов или выполнений в число");
                vBoxInMainWindow.showAlert("Вы указали количество подходов или повторений неправильно");
            }
        });
    }
}
