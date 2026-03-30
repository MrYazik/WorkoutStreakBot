package mryazik.github.io.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import mryazik.github.io.App;

import java.io.IOException;
import java.net.URL;

// Base controller отвечает за то чтоб загружать MainVBOX:
// Статистика, мои тренеровки

public class BaseController
{
    App appFx;
    private VBox rootLayout;

    @FXML
    public void initialize()
    {

    }

    public void setAppFX(App appFX) {
        this.appFx = appFX;
    }

    public void defaultLoad()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/maket/MainVBOX.fxml"));

            rootLayout = loader.load();

            // Загружаем контроллер кнопок MainVBOXController
            MainVBOXController controllerMainVBox = loader.getController();
            controllerMainVBox.setApp(appFx);

            appFx.rootLayout.setCenter(rootLayout);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
