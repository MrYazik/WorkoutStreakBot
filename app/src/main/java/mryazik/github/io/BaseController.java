package mryazik.github.io;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class BaseController
{
    private App appFx;
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

            URL fxmlLocation = getClass().getResource("/maket/MainVBOX.fxml");
            loader.setLocation(fxmlLocation);

            if (fxmlLocation == null) {
                throw new RuntimeException("Файл FXML не найден! Проверьте путь.");
            }

            rootLayout = loader.load();
            appFx.rootLayout.setCenter(rootLayout);
        } catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }
}
