package com.verdantsun;
import java.io.*;
import java.util.HashMap;

/**
 * Utility class for loading Fertilizer data from a JSON file.
 * <p>
 * This class reads fertilizer information from the specified JSON file and
 * returns a HashMap where each key is a fertilizer identifier and the value
 * is a Fertilizer object.
 * </p>
 */
public class FertilizerLoader {

    /**
     * Loads fertilizers from a JSON file and returns them as a HashMap.
     * <p>
     * Each fertilizer in the JSON file should contain the following attributes:
     * <ul>
     *   <li>name - the fertilizer's name</li>
     *   <li>price - the cost of the fertilizer</li>
     *   <li>days - the number of days the fertilizer remains effective</li>
     * </ul>
     * </p>
     *
     * @param filePath the path to the JSON file containing fertilizer data
     * @return a HashMap mapping fertilizer keys to Fertilizer objects
     */
    public static HashMap<String, Fertilizer> loadFertilizers(String filePath) {

        HashMap<String, Fertilizer> fertilizers = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            String content = json.toString();
            content = content.substring(1, content.length() - 1);

            String[] entries = content.split("},");

            for (String entry : entries) {

                String[] keySplit = entry.split(":\\{");
                String key = keySplit[0].replace("\"", "").trim();
                String values = keySplit[1].replace("}", "");

                String[] attributes = values.split(",");

                String name = "";
                int price = 0;
                int days = 0;

                for (String attr : attributes) {

                    String[] pair = attr.split(":");
                    String attrKey = pair[0].replace("\"", "").trim();
                    String attrValue = pair[1].replace("\"", "").trim();

                    switch (attrKey) {
                        case "name": name = attrValue; break;
                        case "price": price = Integer.parseInt(attrValue); break;
                        case "days": days = Integer.parseInt(attrValue); break;
                    }
                }

                fertilizers.put(key, new Fertilizer(name, price, days));
            }

        } catch (IOException e) {
            System.out.println("Error loading fertilizers file.");
        }

        return fertilizers;
    }
}