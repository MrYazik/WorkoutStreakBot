package mryazik.github.io.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mryazik.github.io.App;
import mryazik.github.io.Controllers.BaseController;
import mryazik.github.io.Controllers.MainVBOXController;

import java.io.IOException;

public class mainWindow {
    static public Stage stage;
    static public BorderPane rootLayout;

    static public void showBaseWindow(String titleWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(mainWindow.class.getResource("/maket/mainMenu.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle(titleWindow);

            // Устанавливаем стиль
            scene.getStylesheets().add(mainWindow.class.getResource("/styles/style.css").toExternalForm());

            // Загружаем VBox
            vBoxInMainWindow newVBox = new vBoxInMainWindow();
            newVBox.loadVBox("MainVBOX.fxml");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}