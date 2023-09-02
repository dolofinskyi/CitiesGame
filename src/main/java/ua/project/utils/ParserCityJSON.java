package org.example.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ParserCityJSON {

    public List<String> getListName() {
        return listName;
    }

    static List<String> listName = new ArrayList<>();

    public static void parseJson() throws FileNotFoundException {
        JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(new FileInputStream("src/main/resources/City.json"), UTF_8)).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            String objectName = jsonObject.get("object_name").getAsString();
            listName.add(objectName.toLowerCase());
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        parseJson();
    }
}
