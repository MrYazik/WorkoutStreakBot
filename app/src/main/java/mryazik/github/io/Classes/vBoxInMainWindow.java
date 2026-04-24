package mryazik.github.io.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import mryazik.github.io.Classes.mainWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

public class vBoxInMainWindow {
    Logger logger = Logger.getLogger(getClass().getName());

    VBox vBox;
    public mainWindow window;
    public FXMLLoader loader;
    public FXMLLoader backLoader;


    public vBoxInMainWindow(mainWindow window)
    {
        this.window = window;
    }

    public void loadVBox(String name_fxml_file)
    {
        try {
            backLoader = loader;

            loader = new FXMLLoader(getClass().getResource("/maket/" + name_fxml_file));
            VBox rootLayout = loader.load();

            window.rootLayout.setCenter(rootLayout);
        } catch (Exception e)
        {
            logger.log(Level.WARNING, "Не удалось загрузить VBox: " + name_fxml_file, e);
        }
    }

    public void back()
    {
        try {
            VBox rootLayout = backLoader.load();

            window.rootLayout.setCenter(rootLayout);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Не удалось вернуться назад", e);
        }
    }
}
