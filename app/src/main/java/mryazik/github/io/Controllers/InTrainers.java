package mryazik.github.io.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mryazik.github.io.Classes.WorkImage;
import mryazik.github.io.Classes.mainWindow;
import mryazik.github.io.Classes.vBoxInMainWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InTrainers {
    SelectTrainersVBOX backController;
    Logger logger = Logger.getLogger(getClass().getName());

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
    @FXML
    Label count_co; // количество выполнений (текст)

    // Переменные необходимые объекту
    // Мы модем позволить static, ведь архитектура ПО не предполагает однвременного запуска нескольких программ
    static int max_ap = 1; // максимальное количество подходов
    static double current_ap; // текущее количество выполненых подходов
    static int current_ex; // index в list_ex упражнения
    static double current_ex_proc; // index в list_ex упражнения


    static List<String> list_ex = new ArrayList<>();
    static Map<String, Object> ex;

    public void init(String name_trainers, Map<String, Object> ex)
    {
        this.name_trainers.setText(name_trainers);
        this.ex = ex;

        ex.forEach((key, object) -> {
                list_ex.add(key);
        });

        logger.log(Level.INFO, "Последний элемент при добавлении в список тренеровок:" + list_ex.getLast());

        changeEx(current_ex); // по умолчанию там 0
    }

    public void changeEx(int current_ex_id)
    {
        String name_ex = list_ex.get(current_ex_id);
        Map<String, Object> ex_info = (Map<String, Object>) ex.get(name_ex);

        this.name_ex.setText(name_ex);

        // Значение бара тренеровки
        current_ex_proc += (double) 1 / list_ex.size();
        proc_trainers.setProgress(current_ex_proc);

        ex_info.forEach((key, object) -> {
            if (key.equals("cout_apporachies")) // если количество подходов
            {
                max_ap = Integer.parseInt(object.toString());
            } else if (key.equals("cout_complite"))
            {
                count_co.setText(object.toString());
            } else if (key.equals("pics_id")) {
                try {

                    java.nio.file.Path path_to_image = WorkImage.getImage(Integer.parseInt(object.toString()));
                    Image image = new Image(path_to_image.toUri().toString());
                    image_ex.setImage(image);
                } catch (IllegalArgumentException e)
                {
                    logger.log(Level.WARNING, "Нету изображения при загрузке упражнения");
                    image_ex.setImage(WorkImage.getStandartImage());
                }
            }
        });


        // Значение progress bar-а по умолчанию
        current_ap = 0;
        comp_ap.setProgress(0);

        // Реакция кнопок
        done.setOnAction(event -> {
            current_ap += Math.round((double) 1 / max_ap * 100.0) / 100.0;

            if (current_ap <= 1)
            {
                logger.log(Level.INFO, "Количество выполненных подходов: " + current_ap);
                comp_ap.setProgress(current_ap);

                if (current_ap >= 1)
                {
                    done.setText("Выполнил упражнение");
                }
            } else
            {
                done.setText("Выполнил подход");
                current_ex++;

                if (current_ex > list_ex.size())
                {
                    vBoxInMainWindow.back();
                } else
                {
                    changeEx(current_ex);
                }
            }
        });
    }

    public void initialize()
    {
        back.setOnAction(event -> vBoxInMainWindow.back());
    }
}
