package ua.project.logic;

import ua.project.users.ComputerPlayer;
import ua.project.users.HumanPlayer;
import ua.project.users.Player;
import ua.project.utils.LoadManager;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Game {
    public Deque<Player> players = new ArrayDeque<>();
    public List<String> allCities;
    public boolean isOnline;

    public Game(){
        // default settings
        players.add(new HumanPlayer("User"));
        players.add(new ComputerPlayer("Comp"));
        allCities = LoadManager.load(false);
        isOnline = false;
    }

    public void reloadCities(boolean isOnline){
        this.isOnline = isOnline;
        allCities = LoadManager.load(isOnline);
    }

    public Player getPlayerByNickname(String nickname){
        for (Player player: players) {
            if (player.getName().equals(nickname)){
                return player;
            }
        }
        return null;
    }

}
