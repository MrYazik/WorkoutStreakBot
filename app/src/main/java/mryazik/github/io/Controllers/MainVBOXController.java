package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import mryazik.github.io.App;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

// Отвечает за то чтоб управлять кнопками MainVBOX:

public class MainVBOXController {
    @FXML private Button stats_button;
    @FXML private Button trainers_button;
    App app;
    VBox rootLayout;

    private Logger logger = Logger.getLogger(MainVBOXController.class.getName());

    public void initialize()
    {
        stats_button.setOnAction(event -> {
            System.out.println("Log: button \"Статистика\"");
        });
        trainers_button.setOnAction(event -> {
            try {
                System.out.println("Log: button \"Мои тренеровки\"");

                FXMLLoader loader = new FXMLLoader();
                URL fxmlLocation = getClass().getResource("/maket/TrainersVBOX.fxml");
                loader.setLocation(fxmlLocation);

                rootLayout = loader.load();

//                 Загружаем ещё один контроллер, меню моих тренеровок
                TrainersVBOX controller = loader.getController();
                controller.setApp(app);

                controller.initList(); // добавляем кнопки по тренеровкам

                app.rootLayout.setCenter(rootLayout);
            }
            catch (IOException e) {
                logger.log(Level.SEVERE, "Не удалось сменить rootLayout", e);
            }
        });
    }

    public void setApp(App app)
    {
        this.app = app;
    }
}
