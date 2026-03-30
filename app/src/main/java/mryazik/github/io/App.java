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

public class App extends Application {
    public Stage primaryStage;
    public BorderPane rootLayout;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        showBaseWindow();
    }

    public void showBaseWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/maket/MainMenu.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

            BaseController controller = loader.getController();
            controller.setAppFX(this);

            controller.defaultLoad();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

