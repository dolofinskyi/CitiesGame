package ua.project.users;
import java.util.List;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name) {
        super(name);
    }

    public String getCityStartWith(char letter, List<String> citiesList) {
        for (String city : citiesList) {
            if (Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(letter)) {
                return city;
            }
        }
        return null;
    }

    @Override
    public void process(List<String> cities, String lastSymbol, String enteredValue) {

    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
