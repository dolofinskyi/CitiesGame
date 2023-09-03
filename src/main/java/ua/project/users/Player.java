package ua.project.users;
import ua.project.logic.MoveState;
import ua.project.logic.PlayerState;

import java.util.List;

public abstract class Player {
    protected String name;
    private int moves = 0;
    private PlayerState playerState;
    private String eneteredValue;

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getMoves() {
        return moves;
    }

    public void increaseMoves() {
        this.moves++;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void setCurrentCity(String eneteredValue) {
        this.eneteredValue = eneteredValue;
    }

    public abstract MoveState process(List<String> cities, String lastSymbol, String enteredCity);

    public abstract boolean isHuman();

}
