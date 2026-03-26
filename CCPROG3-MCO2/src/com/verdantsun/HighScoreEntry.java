package com.verdantsun;

/**
 * Represents a single high score entry with a player's name and savings.
 */
public class HighScoreEntry {

    private String playerName;
    private int savings;

    /**
     * Initializes a high score entry with the given player name and savings.
     *
     * @param playerName Name of the player.
     * @param savings Savings value to record.
     */
    public HighScoreEntry(String playerName, int savings) {
        this.playerName = playerName;
        this.savings = savings;
    }

    /**
     * Returns the name of the player.
     *
     * @return Player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Returns the savings value of the player.
     *
     * @return Player's savings.
     */
    public int getSavings() {
        return this.savings;
    }
}