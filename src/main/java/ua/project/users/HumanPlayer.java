package ua.project.users;
import java.util.Set;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, Set<String> usedCities) {
        super(name, usedCities);
    }

    @Override
    public String getSmartCityStartingWith(char letter) {
        return null;
    }

    @Override
    public void addUsedCity(String city) {
        usedCities.add(city);
    }
}
