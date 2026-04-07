package com.verdantsun;

public class HighScoreEntry {

    private String playerName;
    private int savings;

    public HighScoreEntry(String playerName, int savings) {
        this.playerName = playerName;
        this.savings = savings;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getSavings() {
        return this.savings;
    }
}
