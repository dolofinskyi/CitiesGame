package ua.project.users;

import ua.project.logic.MoveState;

import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public MoveState move(List<String> cities, String lastSymbol, String enteredCity) {
        String firstSymbol = enteredCity.substring(0, 1).toUpperCase();
        if (cities.contains(enteredCity.toUpperCase())
                && (firstSymbol.equals(lastSymbol) || lastSymbol.isEmpty())) {
            setEnteredCity(enteredCity);
            increaseMoves();
            return MoveState.CORRECT;
        }
        return MoveState.UNCORRECT;
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
