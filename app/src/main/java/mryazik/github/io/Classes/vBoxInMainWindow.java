package mryazik.github.io.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import mryazik.github.io.Classes.mainWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

public class vBoxInMainWindow {
    static Logger logger = Logger.getLogger(vBoxInMainWindow.class.getName());

    VBox vBox;
    public static mainWindow window;
    public static FXMLLoader loader;
    public static VBox rootLayout;
    public static VBox backElement;

    public FXMLLoader loadVBox(String name_fxml_file)
    {
        try {
            backElement = rootLayout;

            loader = new FXMLLoader(getClass().getResource("/maket/" + name_fxml_file));
            rootLayout = loader.load();

            mainWindow.rootLayout.setCenter(rootLayout);

            return loader;
        } catch (Exception e)
        {
            logger.log(Level.WARNING, "Не удалось загрузить VBox: " + name_fxml_file, e);
        }

        return loader;
    }

    public static void back()
    {
        try {
            window.rootLayout.setCenter(backElement);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Не удалось вернуться назад", e);
        }
    }

    public static void showAlert(String name_message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Приложение для тренировок");
        alert.setContentText(name_message);
        alert.showAndWait();
    }
}
