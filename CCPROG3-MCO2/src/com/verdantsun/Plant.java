package com.verdantsun;

/**
 * Represents a plant in the game. Tracks its name, growth, watering status,
 * preferred soil, yield, seed price, crop price, and symbol for display.
 */
public class Plant {

    private String name;
    private int seedPrice;
    private int yield;
    private int maxGrowth;
    private int currentGrowth;
    private String preferredSoil;
    private boolean watered;
    private int cropPrice;
    private char symbol;

    /**
     * Initializes a plant with the specified attributes.
     *
     * @param name Name of the plant.
     * @param seedPrice Cost of the seed.
     * @param yield Number of units the plant yields when harvested.
     * @param maxGrowth Maximum growth value to reach maturity.
     * @param preferredSoil Soil type that the plant grows best in.
     * @param cropPrice Selling price per unit of crop.
     */
    public Plant(String name, int seedPrice, int yield, int maxGrowth, String preferredSoil, int cropPrice) {
        this.name = name;
        this.seedPrice = seedPrice;
        this.yield = yield;
        this.maxGrowth = maxGrowth;
        this.preferredSoil = preferredSoil;
        this.cropPrice = cropPrice;
        this.symbol = generateSymbol(name);

        this.currentGrowth = 0;
        this.watered = false;
    }

    /**
     * Waters the plant, marking it as watered.
     */
    public void water() {
        this.watered = true;
    }

    /**
     * Increases the plant's growth if it has been watered.
     * Growth is influenced by preferred soil and fertilizer.
     *
     * @param soilType Soil type of the tile the plant is on.
     * @param fertilized True if the plant is fertilized or permanently fertilized.
     */
    public void grow(String soilType, boolean fertilized) {
        if (!this.watered) {
            return;
        }

        int growthAmount = 1;

        if (soilType.equalsIgnoreCase(this.preferredSoil)) {
            growthAmount++;
        }

        if (fertilized) {
            growthAmount++;
        }

        this.currentGrowth += growthAmount;

        if (this.currentGrowth > this.maxGrowth) {
            this.currentGrowth = this.maxGrowth;
        }
    }

    /**
     * Resets the watered status of the plant.
     */
    public void resetWater() {
        this.watered = false;
    }

    /**
     * Checks if the plant has reached maturity.
     *
     * @return true if current growth >= max growth, false otherwise.
     */
    public boolean isMature() {
        return this.currentGrowth >= this.maxGrowth;
    }

    /**
     * Checks if the plant is watered.
     *
     * @return true if watered, false otherwise.
     */
    public boolean isWatered() {
        return this.watered;
    }

    /**
     * Checks if the plant is on its preferred soil.
     *
     * @param soilType Soil type of the tile.
     * @return true if the soil matches preferred soil, false otherwise.
     */
    public boolean isInPreferredSoil(String soilType) {
        return soilType.equalsIgnoreCase(this.preferredSoil);
    }

    /**
     * Returns the plant's name.
     *
     * @return Name of the plant.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the seed.
     *
     * @return Seed price.
     */
    public int getSeedPrice() {
        return this.seedPrice;
    }

    /**
     * Returns the total yield value of the plant when harvested.
     *
     * @return Yield value = yield * cropPrice.
     */
    public int getYieldValue() {
        return this.yield * cropPrice;
    }

    /**
     * Returns the number of units the plant yields.
     *
     * @return Yield units.
     */
    public int getYield() {
        return this.yield;
    }

    /**
     * Returns the maximum growth value to reach maturity.
     *
     * @return Max growth.
     */
    public int getMaxGrowth() {
        return this.maxGrowth;
    }

    /**
     * Returns the current growth value.
     *
     * @return Current growth.
     */
    public int getCurrentGrowth() {
        return this.currentGrowth;
    }

    /**
     * Returns the preferred soil type.
     *
     * @return Preferred soil type.
     */
    public String getPreferredSoil() {
        return this.preferredSoil;
    }

    /**
     * Returns the crop price per unit.
     *
     * @return Crop price.
     */
    public int getCropPrice() {
        return this.cropPrice;
    }

    /**
     * Returns the symbol used to represent the plant on the field.
     *
     * @return Plant symbol character.
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Generates a symbol based on the plant's name.
     *
     * @param name Name of the plant.
     * @return Symbol character for the plant.
     */
    private char generateSymbol(String name) {

        return switch (name.toLowerCase()) {
            case "potato" -> 'P';
            case "tomato" -> 'O';
            case "turnip" -> 'U';
            case "wheat" -> 'W';
            case "thyme" -> 'T';
            default -> Character.toUpperCase(name.charAt(0));
        };
    }
}