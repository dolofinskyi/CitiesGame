package ua.project.users;
import java.util.List;

public abstract class Player {
    protected String name;
    private int moves = 0;

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

    public abstract void process(List<String> cities, String lastSymbol, String enteredValue);
    public abstract boolean isHuman();

}
