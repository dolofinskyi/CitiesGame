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
    public MoveState move(List<String> cities, String lastSymbol, String enteredCity) {
        String randCity = getRandomCity(lastSymbol, cities);

        if (randCity != null) {
            setEnteredCity(randCity);
            increaseMoves();
            return MoveState.CORRECT;
        } else {
            setPlayerState(PlayerState.LOSE);
            return MoveState.NOMOVE;
        }
    }

    private String getRandomCity(String letter, List<String> cities) {
        Random rand = new Random();

        if (letter.isEmpty()){
            return cities.get(rand.nextInt(cities.size()));
        }

        List<String> availableCities = cities.stream().filter(city ->
                String.valueOf(city.toLowerCase().charAt(0)).equals(letter.toLowerCase())
        ).toList();

        if (availableCities.size() == 0) return null;
        return availableCities.get(rand.nextInt(availableCities.size()));
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
