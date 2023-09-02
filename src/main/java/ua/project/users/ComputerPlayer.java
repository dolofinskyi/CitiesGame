package ua.project.users;
import java.util.List;


public class ComputerPlayer extends Player {
    private Set<String> citiesList;


    public ComputerPlayer(String name, Set<String> citiesList) {
        super(name);
        this.citiesList = citiesList;
    }

    public String getCityStartWith(char letter) {
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