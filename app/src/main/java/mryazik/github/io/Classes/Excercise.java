package mryazik.github.io.Classes;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

// Класс, который используется для работы с Json, в качестве типа класса в форме которого упаковывается
public class Excercise // Упржнение тренеровки
{
    private Map<String, Object> trainings = new LinkedHashMap<>();

    // Любые последующие значения в json будут туда подставляться
    // Только для чтения
    @JsonAnySetter
    public void addTrainers(String key, Object value) {
        this.trainings.put(key, value);
    }


    Excercise() {
    }

    public Excercise(Map<String, Object> map) {
        this.trainings = map;
    }

    public Map<String, Object> getAllTrainings() {
        return trainings;
    }
}
