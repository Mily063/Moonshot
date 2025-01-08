package org.example.moonshot.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.moonshot.models.Mission;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class DataLoader {
    public static List<Mission> loadMissions(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Mission>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
