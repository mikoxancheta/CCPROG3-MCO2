package com.verdantsun;
import java.io.*;
import java.util.*;

public class HighScoreManager {

    private ArrayList<HighScoreEntry> highScores;

    public HighScoreManager() {
        highScores = new ArrayList<>();
    }

    public void loadScores() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/HighScores.json"));
            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            String content = json.toString();
            content = content.substring(1, content.length() - 1);

            if (content.trim().isEmpty()) return;

            String[] entries = content.split("},");

            for (String entry : entries) {

                String[] keySplit = entry.split(":\\{");
                String values = keySplit[1].replace("}", "");

                String[] attributes = values.split(",");

                String name = "";
                int savings = 0;

                for (String attr : attributes) {

                    String[] pair = attr.split(":");
                    String key = pair[0].replace("\"", "").trim();
                    String value = pair[1].replace("\"", "").trim();

                    if (key.equals("name")) name = value;
                    if (key.equals("savings")) savings = Integer.parseInt(value);
                }

                highScores.add(new HighScoreEntry(name, savings));
            }

        } catch (IOException e) {
            System.out.println("Error loading high scores.");
        }
    }

    public void saveScores() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/HighScores.json"));
            writer.write("{");

            for (int i = 0; i < highScores.size(); i++) {
                HighScoreEntry entry = highScores.get(i);
                writer.write("\"" + (i+1) + "\":{");
                writer.write("\"name\":\"" + entry.getPlayerName() + "\",");
                writer.write("\"savings\":" + entry.getSavings() + "}");
                if (i < highScores.size() - 1) writer.write(",");
            }

            writer.write("}");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving high scores.");
        }
    }

    public void addScore(String name, int savings) {

        highScores.add(new HighScoreEntry(name, savings));

        highScores.sort((a, b) -> b.getSavings() - a.getSavings());

        if (highScores.size() > 10) {
            highScores.remove(highScores.size() - 1);
        }
    }
}
