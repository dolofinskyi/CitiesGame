package ua.project.users;
import ua.project.logic.MoveState;

import java.util.List;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public MoveState process(List<String> cities, String lastSymbol, String enteredCity) {
        String firstSymbol = enteredCity.substring(0, 1);
        if (cities.contains(enteredCity)
                && (firstSymbol.equals(lastSymbol) || lastSymbol.isEmpty())) {
            setCurrentCity(enteredCity);
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
