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
    public Stage stage;
    public BorderPane rootLayout;

    public void showBaseWindow(String titleWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/maket/mainMenu.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle(titleWindow);

            // Устанавливаем стиль
            scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

            // Загружаем VBox
            vBoxInMainWindow newVBox = new vBoxInMainWindow(this);
            newVBox.loadVBox("MainVBOX.fxml");

            MainVBOXController controller = newVBox.loader.getController();
            controller.setWindow(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}