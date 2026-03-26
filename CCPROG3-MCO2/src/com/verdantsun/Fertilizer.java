package com.verdantsun;

/**
 * Represents a fertilizer item that can be applied to a tile to boost plant growth.
 * The fertilizer has a name, price, and a limited effect duration.
 */
public class Fertilizer {


    private String name;
    private int price;
    private int effectDays;
    private int maxEffectDays;

    /**
     * Initializes a fertilizer with a given name, price, and effect duration.
     *
     * @param name Name of the fertilizer.
     * @param price Cost of the fertilizer.
     * @param effectDays Number of days the fertilizer remains effective.
     */
    public Fertilizer(String name, int price, int effectDays) {
        this.name = name;
        this.price = price;
        this.effectDays = effectDays;
        this.maxEffectDays = effectDays;
    }

    /**
     * Decreases the remaining effect duration of the fertilizer by one day.
     * Effect days cannot go below zero.
     */
    public void decreaseEffect() {
        if (this.effectDays > 0) {
            this.effectDays--;
        }
    }

    /**
     * Checks if the fertilizer has expired.
     *
     * @return true if the remaining effect days are zero or less, false otherwise.
     */
    public boolean isExpired() {
        return this.effectDays <= 0;
    }

    /**
     * Returns the name of the fertilizer.
     *
     * @return Name of the fertilizer.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the fertilizer.
     *
     * @return Price of the fertilizer.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns the remaining effect duration of the fertilizer.
     *
     * @return Remaining effect days.
     */
    public int getEffectDays() {
        return this.effectDays;
    }

    /**
     * Returns the original maximum effect duration of the fertilizer.
     *
     * @return Maximum effect days.
     */
    public int getMaxEffectDays() {
        return this.maxEffectDays;
    }
}