package com.verdantsun;
import java.io.*;
import java.util.HashMap;

/**
 * Utility class for loading Plant data from a JSON file.
 * <p>
 * This class reads plant information from the specified JSON file
 * and converts it into a HashMap of Plant objects keyed by their identifier.
 * </p>
 */
public class PlantLoader {

    /**
     * Loads plants from a JSON file and returns them as a HashMap.
     * <p>
     * Each entry in the JSON file should contain attributes:
     * name, price, yield, max_growth, preferred_soil, and crop_price.
     * </p>
     *
     * @param filePath the path to the JSON file containing plant data
     * @return a HashMap mapping plant keys to Plant objects
     */
    public static HashMap<String, Plant> loadPlants(String filePath) {

        HashMap<String, Plant> plants = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            String content = json.toString();

            content = content.substring(1, content.length() - 1); // remove { }

            String[] entries = content.split("},");

            for (String entry : entries) {

                String[] keySplit = entry.split(":\\{");
                String key = keySplit[0].replace("\"", "").trim();
                String values = keySplit[1].replace("}", "");

                String[] attributes = values.split(",");

                String name = "";
                int price = 0;
                int yield = 0;
                int maxGrowth = 0;
                String preferredSoil = "";
                int cropPrice = 0;

                for (String attr : attributes) {

                    String[] pair = attr.split(":");
                    String attrKey = pair[0].replace("\"", "").trim();
                    String attrValue = pair[1].replace("\"", "").trim();

                    switch (attrKey) {
                        case "name": name = attrValue; break;
                        case "price": price = Integer.parseInt(attrValue); break;
                        case "yield": yield = Integer.parseInt(attrValue); break;
                        case "max_growth": maxGrowth = Integer.parseInt(attrValue); break;
                        case "preferred_soil": preferredSoil = attrValue; break;
                        case "crop_price": cropPrice = Integer.parseInt(attrValue); break;
                    }
                }

                plants.put(key, new Plant(name, price, yield, maxGrowth, preferredSoil, cropPrice));
            }

        } catch (IOException e) {
            System.out.println("Error loading plants file.");
        }

        return plants;
    }
}