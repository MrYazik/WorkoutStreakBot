package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mryazik.github.io.Classes.mainWindow;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateEx {
    Logger logger = Logger.getLogger(CreateEx.class.getName());

    @FXML
    TextField name_ex;
    @FXML
    TextField count_ap;
    @FXML
    TextField count_comp;

    @FXML Button paste_impage;
    @FXML Button done;
    @FXML Button back;
    @FXML ImageView image;


    public void createWindow (TrainersVBOX trainersVBOX) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/maket/CreateEx.fxml"));

            VBox rootLayout = loader.load();
            mainWindow.rootLayout.setCenter(rootLayout);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ну удалось загрузить меню CreateEx.java", e);
        }
    };
}
