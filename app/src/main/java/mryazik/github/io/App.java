package mryazik.github.io;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mryazik.github.io.Controllers.BaseController;
import mryazik.github.io.Controllers.MainVBOXController;
import mryazik.github.io.Controllers.TrainersVBOX;
// Для работы с самим URL ресурса

// Для загрузки FXML-файлов (самый частый сценарий)

// Для создания изображений из ресурсов

import java.io.IOException;

import mryazik.github.io.Classes.mainWindow;

public class App extends Application {
    public Stage primaryStage;
    public BorderPane rootLayout;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainWindow mainWindow = new mainWindow();

        mainWindow.stage = primaryStage;
        mainWindow.showBaseWindow("Приложение для тренировок");
    }


}

