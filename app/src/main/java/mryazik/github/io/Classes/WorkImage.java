package mryazik.github.io.Classes;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// Для созданиия данного класса было проезно прочтение данной статьи:
// https://javarush.com/groups/posts/2275-files-path

public class WorkImage {
    static Logger logger = Logger.getLogger(WorkImage.class.getName());

    public static void main (String args[])
    {
        File file = new File("/home/mryaz/Загрузки/8086268395.png");
        loadImage(file);
    }

    public static int loadImage(File image)
    {
        try {
            Random random = new Random();
            int randomImageId =  random.nextInt(1, 456600);
            String checkFile = "images/" + Integer.toString(randomImageId) + ".png";

            // Проверка, есть ли директория, если есть:
            if (Files.exists(Paths.get("images/"))) {
                Path path = Paths.get(checkFile);
                if (Files.exists(path)) // Если файл существует
                {
                    loadImage(image);
                } else {
                    Files.copy(image.toPath(), path);
                    return randomImageId;
                }
            } else
            {
                Files.createDirectories(Paths.get("images/"));
                loadImage(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Path getImage(int imageId)
    {

        String checkFile = "images/" + Integer.toString(imageId) + ".png";

        if (Files.exists(Paths.get(checkFile))) // если файл есть, то возращаем путь
        {
            return Paths.get(checkFile);
        } else
        {
            logger.log(Level.WARNING, "Нету изображения по ID: " + imageId);
        }

        return null;
    }
}
