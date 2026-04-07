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
import mryazik.github.io.Classes.WorkImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
    int pics_id;

    // id предыдущей добавленной картинки
    AtomicInteger oldImageId = new AtomicInteger(0);

    public void setBackcontroller(SelectTrainersVBOX backController)
    {
        this.backController = backController;
    }
    public void initOneEx(String name_trainers, String name_ex, int count_ap, int count_comp, int pics_id)
    {
        this.name_trainers = name_trainers;
        this.pics_id = pics_id;

        // Загружаем картинку
        if (pics_id != 0)
        {
            try {
                Image image1 = new Image(WorkImage.getImage(pics_id).toUri().toString());
                this.image.setImage(image1);

                oldImageId.set(pics_id);
            } catch (IllegalArgumentException e) { // если нет такого файла
                oldImageId.set(0);
            }
        } // Потом добавить загрузку стандартной картинки из resources

        ex_name.setText(name_ex);
        this.name_ex = name_ex;
        this.count_ap.setText(Integer.toString(count_ap));
        this.count_comp.setText(Integer.toString(count_comp));
    }

    public void initialize()
    {
        AtomicInteger count_ap = new AtomicInteger(0);
        AtomicInteger count_comp = new AtomicInteger(0);


        paste_image.setOnAction(action -> {
            // Если значение уже есть, то удаляем файл по id:

            if (oldImageId.get() != 0)
            {
                WorkImage.removeImage(oldImageId.get());
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Установите изображение для упражнения");

            File selectedFile = fileChooser.showOpenDialog(backController.backController.app.primaryStage);
            oldImageId.set(WorkImage.loadImage(selectedFile));


            Image image = new Image(WorkImage.getImage(oldImageId.get()).toUri().toString());
            this.image.setImage(image);
        });

        // Кнопка готово
        done.setOnAction(event -> {
            // собираем новую инфу
            try {
                count_ap.set(Integer.parseInt(this.count_ap.getText()));
                count_comp.set(Integer.parseInt(this.count_comp.getText()));
            } catch (NumberFormatException e)
            {
                logger.log(Level.WARNING, "Строка введённая пользователем - это строка", e);
            }

            Map<String, Object> newMap = Trainers.sampleEx(count_ap.get(), count_comp.get(), oldImageId.get());
            Map<String, Object> editTrainersMap = Trainers.addEx(this.name_trainers, this.name_ex, newMap);

            // Возвращаемся назад
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/maket/SelectedTrainersVBOX.fxml"));

                VBox rootLayout = loader.load();

                SelectTrainersVBOX controller = loader.getController();
                controller.setBackController(backController.backController);

                // Получим упражнения из тренировки
                editTrainersMap.forEach((key, object) -> {
                    if (key.equals(backController.name_trainers_text))
                    {
                        controller.initVBOX(backController.name_trainers_text, (Map<String, Object>)object);
                    }
                });

                backController.backController.app.rootLayout.setCenter(rootLayout);
            }catch (IOException e) {
                logger.log(Level.SEVERE, "Не удалось загрузить из SettingsOneEx обратно в SelectedTrainersVBOX.fxml");
            }
        });

        back.setOnAction(event ->
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/maket/SelectedTrainersVBOX.fxml"));

                VBox rootLayout = loader.load();

                SelectTrainersVBOX controller = loader.getController();
                controller.setBackController(backController.backController);

                // Получаем старую инфу из json и инициализируем меню
                Map<String, Object> oldMap = Trainers.getFromFile().getAllTrainings();

                oldMap.forEach((key, object) -> {
                    if (key.equals(backController.name_trainers_text))
                    {
                        controller.initVBOX(backController.name_trainers_text, (Map<String, Object>)object);
                    }
                });


                backController.backController.app.rootLayout.setCenter(rootLayout);
            } catch (IOException e)
            {
                logger.log(Level.WARNING, "Не удалось загрузить меню упражнения SelectTrainersVBOX.fxml", e);
            }
        });
    }
}
