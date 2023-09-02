package ua.project.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineLoader {
    private static final String URL_CITY_OF_UKRAINE = "https://spravka109.net/ua/adres/ukraine/cities";

    private static String getCleanCityName(String str) {
        if (str.indexOf("(") > 0) {
            return str.substring(0, str.indexOf("(")).strip();
        }
        return str;
    }

    private static boolean cityNameIsCorrect(String str) {
        return !str.contains(".");
    }

    public static List<String> getOnlineCities() throws IOException {
        Document document = Jsoup.connect(URL_CITY_OF_UKRAINE).get();

        Elements cityElements = document.select("a.alist");
        List<String> cityNames = cityElements.stream()
                .map(Element::text)
                .filter(OnlineLoader::cityNameIsCorrect)
                .map(OnlineLoader::getCleanCityName)
                .collect(Collectors.toList());

        return cityNames;

    }
}
