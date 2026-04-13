package mryazik.github.io.Controllers;

// Данный класс отвечает за выбор определённой тренеровки

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectTrainersVBOX {
    public TrainersVBOX backController;

    // Инициализируем логгер
    Logger logger = Logger.getLogger(getClass().getName());

    @FXML
    private Label name_trainers;
    @FXML
    private VBox list_ep; // список всех тренеровок

    // Для доступа из вне
    public Map<String, Object> ex_map;
    public String name_trainers_text;

    public void initVBOX(String name_trainers, Map<String, Object> excercise)
    {
        this.ex_map = excercise;
        this.name_trainers_text = name_trainers;

        this.name_trainers.setText(name_trainers);

        // Перебираем упражнения и
        excercise.forEach((key, object) -> {
            VBox ex_menu = new VBox();
            ex_menu.getStyleClass().add("list_ex");

            ex_menu.getChildren().add(
                    new Label(key) // добавляем имя упражнения
            );

            // Текста последующих полей
            Label coutApporachies = new Label();
            Label coutComplite = new Label();

            // ДЛЯ работы с picsid в числе
            AtomicInteger picsIdInt = new AtomicInteger(0);
            Label picsId = new Label();

            ex_menu.getChildren().addAll(coutApporachies, coutComplite, picsId);

            logger.log(Level.INFO, object.toString());

            AtomicInteger count_ap = new AtomicInteger(0);
            AtomicInteger count_comp = new AtomicInteger(0);

            ((Map<String, Object>) object).forEach((keyes, objecte) -> {
                String textField = keyes + ": " + objecte.toString();

                if (keyes.equals("cout_apporachies")) // если поле соответсвует
                {
                    coutApporachies.setText(textField);

                    // получаем из поля значение
                    count_ap.set(Integer.parseInt(objecte.toString()));
                } else if (keyes.equals("cout_complite")) // если поле соответсвует
                {
                    coutComplite.setText(textField);

                    // Получаем значение из поля
                    count_comp.set(Integer.parseInt(objecte.toString()));
                }  else if (keyes.equals("pics_id")) // если поле соответсвует
                {
                    picsIdInt.set(Integer.parseInt(objecte.toString()));
                    picsId.setText(textField);
                }
            });

            // Кнопка настроить
            Button settings = new Button("Настроить");

            settings.setOnAction(action -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/maket/SettingsOneEx.fxml"));

                    VBox rootLayout = loader.load();

                    SettingsOneEx controller = loader.getController();
                    controller.setBackcontroller(this);
                    logger.log(Level.INFO, "pics id из конфига: " + picsId.getText());
                    controller.initOneEx(name_trainers, key, count_ap.get(), count_comp.get(), picsIdInt.get());

                    backController.app.rootLayout.setCenter(rootLayout);

                } catch (IOException e)
                {
                    logger.log(Level.SEVERE, "Ошибка в инициализации меню SettingsOneEx", e);
                }
            });

            ex_menu.getChildren().add(settings);

            // Добавляем в список новый VBox
            list_ep.getChildren().add(ex_menu);
        });
    }

    public void setBackController(TrainersVBOX controller)
    {
        this.backController = controller;
    }

    @FXML Button back;
    @FXML Button start;

    public void initialize()
    {
        back.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/maket/TrainersVBOX.fxml"));

                VBox rootLayout = loader.load();

                TrainersVBOX controller = loader.getController();
                controller.setApp(backController.app);
                controller.initList();

                backController.app.rootLayout.setCenter(rootLayout);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        start.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/maket/InTrainers.fxml"));

                VBox rootLayout = loader.load();
                InTrainers controller = loader.getController();

                controller.setBackcontroller(this);
                controller.init(name_trainers_text, ex_map);

                backController.app.rootLayout.setCenter(rootLayout);
            } catch (IOException e)
            {
                logger.log(Level.WARNING, "Ошибка при нажатии кнопки start", e);
            }
        });
    }
}
