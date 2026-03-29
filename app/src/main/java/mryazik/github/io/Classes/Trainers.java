package mryazik.github.io.Classes;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Наша задача:
    - Переименовать все методы -
    - Создать добавление упражнений в уже готовую тренеровку -
    - Создать изменение упражнения (Я думаю, мы получаем определённую тренеровку,
                                    Получаем там все упражнения, и нужнное меняем

)*/

// Класс для работы с самим json

public class Trainers
{
    // Logger
    private static final Logger logger = Logger.getLogger(Trainers.class.getName());

    public static void main(String[] args)
    {
/*        // getTrainersFromFile();
        Map<String, Object> map = sampleEx(
                4,
                15,
                1
        );

        // Создаём тренеровку
        createTrainers("Моя Вторая тренеровка");

        addEx("Моя Вторая тренеровка", "Бабочка", map);*/
    }

    public static Excercise getFromFile()
    {
        try {
            File file = new File("Trainers.json");

            if(!file.exists())
            {
                file.createNewFile();
                Files.writeString(file.toPath(), "{}", StandardCharsets.UTF_8);
            }

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(file, Excercise.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Ошибка в получении Map из файла", e);
        }

        return null;
    }

    public static void addToFile(String name_trainers, Map<String, Object> map)
    {
        Map<String, Object> fromFile = Objects.requireNonNull(getFromFile(), "Файл Trainers.json не может быть пустым").getAllTrainings();

        fromFile.put(name_trainers, map); // Добавляем свою тренеровку
        writeToFile(fromFile);
    }

    public static void writeToFile(Map<String, Object> map) // Записывание в файл
    {
        try
        {
            File file = new File("Trainers.json");
            //это объект Jackson, который выполняет сериализацию
            ObjectMapper mapper = new ObjectMapper();

            // writerWithDefaultPrettyPrinter() -- красивый вывод
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, map);

            System.out.println(map);
        } catch (IOException e) {
            /*
Иерархия уровней (от высшего к низшему):
    Level.SEVERE — Критическая ошибка (программа падает или важная функция не работает).
    Level.WARNING — Предупреждение (что-то пошло не так, но программа жива).
    Level.INFO — Информационное сообщение (например, «Бот успешно запущен»).
    Level.CONFIG — Настройки конфигурации.
    Level.FINE / FINER / FINEST — Отладочная информация (детальный лог для поиска багов).

             */

            logger.log(Level.WARNING, "Ошибка при записи информации с Trainers.json", e);
        }
    }

    public static void addEx(String name_trainers, String name_ex, Map<String, Object> ex) // Добавление упражнения по id тренеровки
    {
        Map<String, Object> trainers = Objects.requireNonNull(getFromFile(), "Нету упражнений в файле Trainers.json").getAllTrainings();

        trainers.forEach((key, object) -> {
            if (key.equals(name_trainers))
            {
                Map<String, Object> editObject = (Map<String, Object>)object;
                editObject.put(name_ex, ex);

                // Если имя тренеровки совпадает - то он просто заменяет
                trainers.put(name_trainers, editObject);

                // Записываем в файл
                writeToFile(trainers);

                logger.log(Level.INFO, "addEx() in class Trainers: created new ex: %s in trainers");
            }
        });
    }

    public static void createTrainers(String name_trainers)
    {
        addToFile(name_trainers, new LinkedHashMap<>()); // Пустая тренеровка

        logger.log(Level.INFO, "createTrainers() in classTrainers: created New Null Trainers");
    }


    // Шаблон для создания упражнения
    public static Map<String, Object> sampleEx(int cout_apporachies, int cout_complite, int pics_id)
    {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("cout_apporachies", cout_apporachies);
        map.put("cout_complite", cout_complite);
        map.put("pics_id", pics_id);

        return map;
    }
}