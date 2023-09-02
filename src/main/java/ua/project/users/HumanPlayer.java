package ua.project.users;
import java.util.List;
import java.util.Set;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void process(List<String> cities, String lastSymbol, String enteredValue) {

    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
