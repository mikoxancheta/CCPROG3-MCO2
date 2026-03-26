package com.verdantsun;

/**
 * Represents the farm field of the game. The field contains
 * a 10x10 grid of tiles where plants can be planted,
 * watered, fertilized, or affected by meteorites.
 * <p>
 * Precondition: Tile objects must be properly defined.
 */
public class Field {

    private Tile[][] tiles;

    /**
     * Initializes the field by creating a 10x10 grid of
     * tiles. Each tile is initially set with loam soil.
     */
    public Field() {
        this.tiles = new Tile[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.tiles[i][j] = new Tile("loam");
            }
        }
    }

    /**
     * Retrieves the tile located at the specified row and column.
     *
     * @param row The row index of the tile (0-based).
     * @param col The column index of the tile (0-based).
     * @return The tile located at the specified position.
     */
    public Tile getTile(int row, int col) {
        return this.tiles[row][col];
    }

    /**
     * Updates the state of every tile in the field for the
     * next day. If a tile contains a plant, the plant will
     * grow according to its growth rules.
     */
    public void nextDayUpdate() {
        for (Tile[] row : this.tiles) {
            for (Tile tile : row) {
                tile.growPlant();
            }
        }
    }

    /**
     * Applies a meteorite strike pattern on the field, affecting specific tiles.
     * Mature plants on affected tiles are automatically harvested for savings,
     * while immature plants are destroyed.
     *
     * @param player The player who will receive savings from harvested plants.
     */
    public void applyMeteoritePattern(Player player) {

        int[][] affectedTiles = {
                {1,1}, {1,4}, {1,5}, {1,8},
                {3,3}, {3,4}, {3,5}, {3,6},
                {4,1}, {4,3}, {4,4}, {4,5}, {4,6}, {4,8},
                {5,1}, {5,3}, {5,4}, {5,5}, {5,6}, {5,8},
                {6,3}, {6,4}, {6,5}, {6,6},
                {8,1}, {8,4}, {8,5}, {8,8}
        };

        for (int[] coord : affectedTiles) {
            int row = coord[0];
            int col = coord[1];

            Tile tile = this.tiles[row][col];

            tile.setMeteoriteAffected(true);

            if (tile.hasPlant()) {
                Plant plant = tile.getPlant();

                if (plant.isMature()) {
                    int harvestValue = tile.harvestPlant();

                    player.addSavings(harvestValue);

                    System.out.println(
                                    plant.getName() +
                                    " at (" + (row + 1) + "," + (col + 1) + ") was harvested by the meteorite! + " +
                                    harvestValue + " savings."
                    );
                } else {
                    System.out.println(
                                    plant.getName() +
                                    " at (" + (row + 1) + "," + (col + 1) + ") was destroyed by the meteorite."
                    );
                    tile.removePlant();
                }
            }
        }
    }

    /**
     * Checks if there is at least one plant currently
     * planted in the field.
     *
     * @return True if at least one tile contains a plant, otherwise false.
     */
    public boolean hasAnyPlant() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].hasPlant()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether there are plants in the field that
     * still need watering.
     *
     * @return True if at least one plant is not yet watered.
     */
    public boolean hasWaterablePlants() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                Tile tile = tiles[i][j];

                if (tile.hasPlant() && !tile.getPlant().isWatered()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if there are tiles currently affected
     * by meteorites in the field.
     *
     * @return True if any tile is meteorite affected.
     */
    public boolean hasMeteoriteTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].isMeteoriteAffected()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Displays the entire field grid including soil types,
     * plant symbols, and meteorite-affected tiles.
     */
    public void displayField() {

        System.out.println();

        System.out.print("\t");
        for (int c = 1; c <= 10; c++) {
            System.out.print(c + "\t");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {

            System.out.print((i + 1) + "\t");

            for (int j = 0; j < 10; j++) {

                Tile tile = this.tiles[i][j];

                if (tile.isMeteoriteAffected()) {
                    System.out.print("M\t");
                } else if (tile.hasPlant()) {
                    System.out.print(tile.getPlant().getSymbol() + "\t");
                } else {
                    switch (tile.getSoilType().toLowerCase()) {
                        case "loam": System.out.print("l\t"); break;
                        case "sand":  System.out.print("s\t"); break;
                        case "gravel":  System.out.print("g\t"); break;
                        default: System.out.print("?\t");
                    }
                }
            }
            System.out.println();
        }

        System.out.println("\nLegend:");
        System.out.println("l = Loam | s = Sand | g = Gravel | M = Meteorite");
        System.out.println("P,T,O,U,W = Plants\n");
    }

    /**
     * Displays detailed information about plants and
     * special tiles in the field including growth,
     * watering status, fertilizer effects, and soil
     * compatibility.
     */
    public void displayStatuses() {

        boolean plantHeader = false;
        boolean tileHeader = false;

        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {

                Tile tile = this.tiles[i][j];

                if (tile.hasPlant()) {
                    if (!plantHeader) {
                        System.out.println("\nPlant Status:");
                        plantHeader = true;
                    }

                    Plant p = tile.getPlant();

                    if (tile.isPermanentlyFertilized()) {
                        System.out.println("(" + (i+1) + "," + (j+1) + ") "
                                + p.getName()
                                + " | Growth: " + p.getCurrentGrowth() + "/" + p.getMaxGrowth()
                                + " | Watered: " + (p.isWatered() ? "Yes" : "No")
                                + " | Permanently Fertilized Tile"
                                + " | Preferred Soil: "
                                + (p.isInPreferredSoil(tile.getSoilType()) ? "Yes" : "No"));
                    } else {
                        String fertilizerInfo = "0/0";

                        if (tile.getFertilizer() != null) {
                            fertilizerInfo =
                                    tile.getFertilizer().getEffectDays()
                                            + "/" +
                                            tile.getFertilizer().getMaxEffectDays();
                        }

                        System.out.println("(" + (i+1) + "," + (j+1) + ") "
                                + p.getName()
                                + " | Growth: " + p.getCurrentGrowth() + "/" + p.getMaxGrowth()
                                + " | Watered: " + (p.isWatered() ? "Yes" : "No")
                                + " | Fertilizer Count: " + fertilizerInfo
                                + " | Preferred Soil: "
                                + (p.isInPreferredSoil(tile.getSoilType()) ? "Yes" : "No"));
                    }
                } else {
                    if (tile.isMeteoriteAffected()
                            || tile.isPermanentlyFertilized()
                            || tile.getFertilizer() != null) {

                        if (!tileHeader) {
                            System.out.println("\nTile Status:");
                            tileHeader = true;
                        }

                        if (tile.isMeteoriteAffected()) {

                            System.out.println("(" + (i+1) + "," + (j+1) + ") Meteorite Tile");
                        } else if (tile.isPermanentlyFertilized()) {

                            System.out.println("(" + (i+1) + "," + (j+1) + ") Permanently Fertilized Tile");
                        } else if (tile.getFertilizer() != null) {

                            System.out.println("(" + (i+1) + "," + (j+1) + ") Tile Fertilized: "
                                    + tile.getFertilizer().getEffectDays()
                                    + "/" + tile.getFertilizer().getMaxEffectDays());
                        }
                    }
                }
            }
        }
    }
}