package com.verdantsun;

/**
 * Represents a watering can used to water plants. Tracks maximum
 * and current water levels.
 */
public class WateringCan {

    private int maxWaterLevel;
    private int currentWaterLevel;

    /**
     * Initializes the watering can with a specified maximum water level.
     * The can starts fully filled.
     *
     * @param maxWaterLevel Maximum water capacity of the can (should be greater than 0).
     */
    public WateringCan(int maxWaterLevel) {
        this.maxWaterLevel = maxWaterLevel;
        this.currentWaterLevel = maxWaterLevel;
    }

    /**
     * Uses one unit of water if available.
     *
     * @return true if water was used successfully, false if the can is empty.
     */
    public boolean water() {
        if (this.currentWaterLevel > 0) {
            this.currentWaterLevel--;
            return true;
        }
        return false;
    }

    /**
     * Refills the watering can to its maximum water level.
     */
    public void refill() {
        this.currentWaterLevel = this.maxWaterLevel;
    }

    /**
     * Checks if the watering can is empty.
     *
     * @return true if current water level is 0, false otherwise.
     */
    public boolean isEmpty() {
        return this.currentWaterLevel == 0;
    }

    /**
     * Returns the current water level of the watering can.
     *
     * @return Current water level.
     */
    public int getCurrentWaterLevel() {
        return this.currentWaterLevel;
    }
}