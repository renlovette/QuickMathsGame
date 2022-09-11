package model;

public class Player {
    private String playerName; // name to identify players
    private int playerNumber; // to identify if player 1 or 2


    // REQUIRES: playerName to be non-empty string, playerNumber either 1 or 2
    // EFFECTS: Constructs a player of playerName and playerNumber
    public Player(String name, int playerNumber) {
        this.playerName = name;
        this.playerNumber = playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
