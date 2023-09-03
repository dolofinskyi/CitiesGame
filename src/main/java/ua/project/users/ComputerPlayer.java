package ua.project.users;

import ua.project.logic.MoveState;
import ua.project.logic.PlayerState;

import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public MoveState process(List<String> cities, String lastSymbol, String enteredCity) {
        if (!lastSymbol.isEmpty()) {
            String cityStartingWith = getCityStartWith(lastSymbol.charAt(0), cities);
            if (cityStartingWith != null) {
                setCurrentCity(cityStartingWith);
                increaseMoves();
                return MoveState.CORRECT;
            } else {
                setPlayerState(PlayerState.LOSE);
                return MoveState.NOMOVE;
            }
        } else {
            if (cities.isEmpty()) {
                setPlayerState(PlayerState.LOSE);
                return MoveState.NOMOVE;
            } else {
                Random rnd = new Random();
                int index = rnd.nextInt(cities.size());
                String randomCity = cities.get(index);
                setCurrentCity(randomCity);
                increaseMoves();
                return MoveState.CORRECT;
            }
        }
    }

    private String getCityStartWith(char letter, List<String> cities) {
        for (String city : cities) {
            if (Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(letter)) {
                return city;
            }
        }
        return null;
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
