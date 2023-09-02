package ua.project.logic;

import ua.project.users.ComputerPlayer;
import ua.project.users.HumanPlayer;
import ua.project.users.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<Player> players = new ArrayList<>();

    public Game(){
        // default settings
        players.add(new HumanPlayer("user1"));
        players.add(new ComputerPlayer("Comp"));
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
