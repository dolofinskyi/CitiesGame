package ua.project.logic;

import ua.project.users.ComputerPlayer;
import ua.project.users.HumanPlayer;
import ua.project.users.Player;
import ua.project.utils.LoadManager;

import java.util.*;

public class Game extends Thread {
    public Deque<Player> players = new ArrayDeque<>();
    public List<String> allCities;
    public boolean isOnline;
    private String curCity;
    private String lastSymbol = "";
    private final List<String> INCORRECT_SYMBOL = Arrays.asList("И", "Й", "Ї", "Ь", "-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ");
    private final HashMap<Player, PlayerState> PLAYERS_PROGRESS = new HashMap<>();

    public Game() {
        // default settings
        players.add(new HumanPlayer("User"));
        players.add(new ComputerPlayer("Comp"));
        allCities = LoadManager.load(false);
        isOnline = false;
    }

    public void reloadCities(boolean isOnline) {
        this.isOnline = isOnline;
        allCities = LoadManager.load(isOnline);
    }

    public Player getPlayerByNickname(String nickname) {
        for (Player player : players) {
            if (player.getName().equals(nickname)) {
                return player;
            }
        }
        return null;
    }

    private void setCurCity(String curCity) {
        this.curCity = curCity.toUpperCase();
    }

    public String getCurCity() {
        return curCity;
    }

    private void setLastSymbol(String cityName) {
        int index = 1;
        int length = cityName.length();
        String symbol = cityName.substring(length - index, length - index + 1).toUpperCase();
        while (INCORRECT_SYMBOL.contains(symbol) && length - index >= 0) {
            index++;
            symbol = cityName.substring(length - index, length - index + 1).toUpperCase();
        }

        this.lastSymbol = String.valueOf(symbol.charAt(0));
        allCities.remove(cityName);
    }

    public String getLastSymbol() {
        return lastSymbol;
    }

    public String checkPlayersStatus() {
        StringBuilder summary = new StringBuilder();
        summary.append("<html>");
        for (Map.Entry<Player, PlayerState> entry : PLAYERS_PROGRESS.entrySet()) {
            String colorText = entry.getKey().isHuman() ? "green" : "red";
            if (entry.getKey().getPlayerState().equals(PlayerState.INGAME)) {
                summary.append("<font color='" + colorText + "'>")
                        .append(entry.getKey().getName() + "</font>")
                        .append(": походив ")
                        .append(entry.getKey().getMoves())
                        .append("<br>");
            } else {
                summary.append("<font color='" + colorText + "'>")
                        .append(entry.getKey().getName() + "</font>")
                        .append(": ")
                        .append(entry.getKey().getPlayerState())
                        .append("<br>");
            }
        }
        summary.append("</html>");
        return summary.toString();
    }

    private void setPlayerLose(Player curPlayer) {
        curPlayer.setPlayerState(PlayerState.LOSE);
        PLAYERS_PROGRESS.put(curPlayer, curPlayer.getPlayerState());
        players.pop();
    }

    private void setWinnerAndLose(Player curPlayer) {
        curPlayer.setPlayerState(PlayerState.WIN);
        PLAYERS_PROGRESS.put(curPlayer, curPlayer.getPlayerState());
        List<Player> collect = players.stream()
                .filter(pl -> pl.getPlayerState() != PlayerState.WIN)
                .toList();
        for (Player player : collect) {
            setPlayerLose(player);
        }
    }

    public Player getWinner() {
        for (Map.Entry<Player, PlayerState> playerState : PLAYERS_PROGRESS.entrySet()) {
            if (playerState.getValue().equals(PlayerState.WIN)) {
                return playerState.getKey();
            }
        }
        return null;
    }

    private void backOfQueue(Player curPlayer) {
        PLAYERS_PROGRESS.put(curPlayer, curPlayer.getPlayerState());
        players.pop();
        players.addLast(curPlayer);
    }

    public boolean processGame(String enteredValue) {
        Player curPlayer = players.peekFirst();
        assert curPlayer != null;
        if (enteredValue.equals(MoveState.GIVEUP.name())) {
            setPlayerLose(curPlayer);
            enteredValue = "";
            curPlayer = players.getFirst();
            if (curPlayer.isHuman()) {
                return true;
            }
        }
        if (gameCanGoOn()) {
            MoveState result = curPlayer.move(allCities, lastSymbol, enteredValue);
            switch (result) {
                case CORRECT -> {
                    setCurCity(curPlayer.getEnteredCity());
                    setLastSymbol(curPlayer.getEnteredCity());
                    if (gameCanGoOn()) {
                        backOfQueue(curPlayer);
                    } else {
                        setWinnerAndLose(curPlayer);
                    }
                    return true;
                }
                case UNCORRECT -> {
                    return false;
                }
                case NOMOVE -> {
                    setPlayerLose(curPlayer);
                    return true;
                }
            }
        } else {
            setWinnerAndLose(curPlayer);
        }
        return false;
    }

    public boolean gameCanGoOn() {
        long count = allCities.stream()
                .filter(str -> str.startsWith(String.valueOf(lastSymbol)))
                .count();
        return (count > 0 && players.size() > 1);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!players.getFirst().isHuman()) {
                processGame("");
            }
        }
    }
}
