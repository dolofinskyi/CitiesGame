package ua.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.nio.charset.StandardCharsets.UTF_8;

public class OfflineLoader {
    public static String FILENAME = "src/main/resources/cities.json";

    public List<String> getOfflineCities() {

        try (FileInputStream file = new FileInputStream(FILENAME)){
            JsonArray jsonArray = JsonParser.parseReader(
                    new InputStreamReader(file, UTF_8)
            ).getAsJsonArray();

            Stream<JsonElement> stream = StreamSupport.stream(jsonArray.spliterator(), true);
            return stream
                    .map(element -> element.getAsJsonObject().get("object_name").getAsString())
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
