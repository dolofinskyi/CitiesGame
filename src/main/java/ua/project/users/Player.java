package ua.project.users;

import ua.project.logic.MoveState;
import ua.project.logic.PlayerState;

import java.util.List;

public abstract class Player {
    protected String name;
    private int moves = 0;
    private PlayerState playerState;
    private String enteredCity;

    public Player(String name) {
        this.name = name;
        this.playerState = PlayerState.INGAME;
    }

    public String getName() {
        return name;
    }

    public String getEnteredCity() {
        return enteredCity;
    }

    public void setEnteredCity(String enteredCity) {
        this.enteredCity = enteredCity.toUpperCase();
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

    public PlayerState getPlayerState() {
        return playerState;
    }

    public abstract MoveState move(List<String> cities, String lastSymbol, String enteredCity);

    public abstract boolean isHuman();

}
