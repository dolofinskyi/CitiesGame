package ua.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.UTF_8;

public class OfflineLoader {
    public static String FILENAME = "src/main/resources/cities.json";

    public ArrayList<String> getOfflineCities() {
        ArrayList<String> citiesList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(FILENAME)){
            JsonArray jsonArray = JsonParser.parseReader(
                    new InputStreamReader(file, UTF_8)
            ).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String objectName = jsonObject.get("object_name").getAsString();
                citiesList.add(objectName.toLowerCase());
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return citiesList;
    }

}
