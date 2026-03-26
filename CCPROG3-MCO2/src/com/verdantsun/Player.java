package com.verdantsun;

/**
 * Represents a player in the game. The player has a name and a savings balance
 * that can be increased or decreased through game actions.
 */
public class Player {

    private String name;
    private int savings;

    /**
     * Initializes a player with a given name and starting savings of 1000.
     *
     * @param name Name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.savings = 1000;
    }

    /**
     * Increases the player's savings by the specified amount.
     *
     * @param amount Amount to add to savings (should be non-negative).
     */
    public void addSavings(int amount) {
        this.savings += amount;
    }

    /**
     * Deducts the specified amount from the player's savings if sufficient funds exist.
     *
     * @param amount Amount to deduct from savings (should be non-negative).
     */
    public void deductSavings(int amount) {
        if (this.savings >= amount) {
            this.savings -=amount;
        }
    }

    /**
     * Checks if the player has enough savings to afford a given amount.
     *
     * @param amount Amount to check against savings (should be non-negative).
     * @return true if the player can afford the amount, false otherwise.
     */
    public boolean canAfford(int amount) {
        return this.savings >= amount;
    }

    /**
     * Returns the player's current savings balance.
     *
     * @return Current savings of the player.
     */
    public int getSavings() {
        return this.savings;
    }

    /**
     * Returns the player's name.
     *
     * @return Name of the player.
     */
    public String getName() {
        return this.name;
    }
}