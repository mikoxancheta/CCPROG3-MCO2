package com.verdantsun;

/**
 * Represents a single tile in the farm field.
 * A tile can hold a plant, fertilizer, and may be affected by meteorites or permanent fertilization.
 */
public class Tile {

    private String soilType;
    private Plant plant;
    private Fertilizer fertilizer;
    private boolean meteoriteAffected;
    private boolean permanentlyFertilized;
    private String originalSoilType;

    /**
     * Initializes a tile with a specified soil type and default values for plant, fertilizer,
     * meteorite effect, and permanent fertilization.
     *
     * @param soilType The type of soil for this tile.
     */
    public Tile(String soilType) {
        this.soilType = soilType;
        this.originalSoilType = soilType;
        this.plant = null;
        this.fertilizer = null;
        this.meteoriteAffected = false;
        this.permanentlyFertilized = false;
    }

    /**
     * Plants a seed on this tile if empty and not affected by a meteorite.
     *
     * @param plant The plant to be planted.
     * @return true if planting is successful, false otherwise.
     */
    public boolean plantSeed(Plant plant) {
        if (this.plant != null) {
            return false;
        }

        if(this.meteoriteAffected) {
            return false;
        }

        this.plant = plant;
        return true;
    }

    /**
     * Removes the plant from this tile.
     */
    public void removePlant() {
        this.plant = null;
    }

    /**
     * Harvests the plant if it is mature, returns its yield value, and removes it from the tile.
     *
     * @return The yield value of the plant, or 0 if no plant or not mature.
     */
    public int harvestPlant() {
        if (this.plant != null && plant.isMature()) {
            int value = plant.getYieldValue();
            this.plant = null;
            return value;
        }
        return 0;
    }

    /**
     * Waters the plant on this tile if it exists and is not already watered.
     *
     * @return true if the plant was watered successfully, false otherwise.
     */
    public boolean waterPlant() {
        if (this.plant == null) {
            return false;
        }

        if (this.plant.isWatered()) {
            return false;
        }

        this.plant.water();
        return true;
    }

    /**
     * Applies fertilizer to this tile if none exists.
     *
     * @param fertilizer The fertilizer to apply.
     * @return true if fertilizer applied successfully, false if tile already has fertilizer.
     */
    public boolean applyFertilizer(Fertilizer fertilizer) {
        if (this.fertilizer != null) {
            return false;
        }

        this.fertilizer = fertilizer;
        return true;
    }

    /**
     * Advances the plant's growth based on water status, soil type, and fertilizer effect.
     */
    public void growPlant() {
        if (this.plant == null) {
            return;
        }

        boolean fertilized = this.permanentlyFertilized || (this.fertilizer != null && !fertilizer.isExpired());

        boolean wasWatered = this.plant.isWatered();

        plant.grow(this.soilType, fertilized);

        if (wasWatered && this.fertilizer != null) {
            this.fertilizer.decreaseEffect();;

            if (this.fertilizer.isExpired()) {
                this.fertilizer = null;
            }
        }

        plant.resetWater();
    }

    /**
     * Excavates the tile, resetting soil type, clearing meteorite effects, and making fertilizer permanent.
     */
    public void excavate() {
        this.meteoriteAffected = false;
        this.soilType = this.originalSoilType;
        this.permanentlyFertilized = true;
    }

    /**
     * Checks if the tile has a plant.
     *
     * @return true if there is a plant, false otherwise.
     */
    public boolean hasPlant() {
        return this.plant != null;
    }

    /**
     * Checks if the tile is empty.
     *
     * @return true if no plant exists, false otherwise.
     */
    public boolean isEmpty() {
        return this.plant == null;
    }

    /**
     * Checks if the tile was affected by a meteorite.
     *
     * @return true if meteorite affected, false otherwise.
     */
    public boolean isMeteoriteAffected() {
        return meteoriteAffected;
    }

    /**
     * Checks if the tile is permanently fertilized.
     *
     * @return true if permanently fertilized, false otherwise.
     */
    public boolean isPermanentlyFertilized() {
        return permanentlyFertilized;
    }

    /**
     * Gets the current soil type of the tile.
     *
     * @return The soil type.
     */
    public String getSoilType() {
        return this.soilType;
    }

    /**
     * Sets the soil type of the tile and updates the original soil type.
     *
     * @param soilType The new soil type.
     */
    public void setSoilType(String soilType) {
        this.soilType = soilType;
        this.originalSoilType = soilType;
    }

    /**
     * Sets the meteorite affected status of the tile.
     *
     * @param value true if meteorite affected, false otherwise.
     */
    public void setMeteoriteAffected(boolean value) {
        this.meteoriteAffected = value;
    }

    /**
     * Returns the plant on this tile.
     *
     * @return The plant object, or null if none exists.
     */
    public Plant getPlant() {
        return this.plant;
    }

    /**
     * Returns the fertilizer on this tile.
     *
     * @return The fertilizer object, or null if none exists.
     */
    public Fertilizer getFertilizer() {
        return this.fertilizer;
    }
}