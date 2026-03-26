package com.verdantsun;
import java.io.*;

/**
 * Utility class for loading farm field maps from a JSON file.
 * <p>
 * This class reads a map layout from the specified JSON file and
 * sets the soil type for each tile in the given Field object.
 * Supported soil symbols:
 * <ul>
 *   <li>l = loam</li>
 *   <li>s = sand</li>
 *   <li>g = gravel</li>
 * </ul>
 * </p>
 */
public class MapLoader {

    /**
     * Loads a map from a JSON file and updates the Field's tiles.
     * <p>
     * The JSON file must contain a "map" array with symbols representing soil types.
     * Each symbol is mapped to a soil type in the Field's tiles.
     * </p>
     *
     * @param field the Field object to populate with soil types
     * @param filePath the path to the JSON file containing the map layout
     */
    public static void loadMap(Field field, String filePath) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            String content = json.toString();

            String mapPart = content.split("\"map\":\\[")[1];
            mapPart = mapPart.substring(0, mapPart.lastIndexOf("]"));

            String[] rows = mapPart.split("\\],\\[");

            for (int i = 0; i < rows.length; i++) {

                String cleanRow = rows[i].replace("[", "").replace("]", "");
                String[] cells = cleanRow.split(",");

                for (int j = 0; j < cells.length; j++) {

                    String symbol = cells[j].replace("\"", "").trim();
                    String soil = "";

                    if (symbol.equals("l")) soil = "loam";
                    if (symbol.equals("s")) soil = "sand";
                    if (symbol.equals("g")) soil = "gravel";

                    field.getTile(i, j).setSoilType(soil);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading map.");
        }
    }
}