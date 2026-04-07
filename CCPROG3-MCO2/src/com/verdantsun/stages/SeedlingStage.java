package com.verdantsun.stages;

public class SeedlingStage extends Stage {

    public SeedlingStage() {
        super("Seedling");
    }

    @Override
    public boolean canGrow(boolean watered) {
        return watered;
    }

    @Override
    public int getGrowthBonus(boolean soilMatch, boolean fertilized) {
        int bonus = 1;

        if (soilMatch) {
            bonus += 1;
        }

        if (fertilized) {
            bonus += 1;
        }

        if (soilMatch && fertilized) {
            bonus += 1;
        }

        return bonus;
    }

    @Override
    public double getYieldMultiplier() {
        return 0;
    }

    @Override
    public int getFertilizerConsumption(boolean watered) {
        return watered ? 1 : 0;
    }

    @Override
    public boolean allowsWatering() {
        return true;
    }

    @Override
    public boolean isFinalStage() {
        return false;
    }

    @Override
    public boolean canHarvest() {
        return false;
    }

    @Override
    public boolean needsWater() {
        return true;
    }
}
