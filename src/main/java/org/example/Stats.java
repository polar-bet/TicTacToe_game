package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Stats {

    private final static String STATS_FILE_PATH = "./src/main/resources/user-info.json";
    static Stats INSTANCE;

    private Stats() {

    }

    public static Stats getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Stats();
        }

        return INSTANCE;
    }

    public void printStats() {
        final int TOP_COUNT = 10;
        int index = 1;
        System.out.printf(Game.ANSI_BLUE + "▛----------------------------▜%n");
        System.out.printf("| %-2s | %-10s | %-8s |%n", "№", "ІМ'Я", "РАХУНОК");
        System.out.printf("------------------------------%n");
        try {
            JSONArray players = getStatsJSON();
            JSONArray playersTop = new JSONArray();
            playersTop.addAll(players.size() > 10
                    ? players.subList(0, TOP_COUNT)
                    : players);
            for (Object objPlayer : playersTop) {
                JSONObject player = (JSONObject) objPlayer;
                String name = (String) player.get("name");
                long score = (long) player.get("score");
                System.out.printf("| %-2s | %-10s | %-8s |%n", index++, name, score);
                if (players.indexOf(player) == playersTop.size() - 1) {
                    System.out.printf("▙----------------------------▟%n" + Game.ANSI_RESET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JSONArray getStatsJSON() {
        JSONParser parser = new JSONParser();
        JSONArray players = new JSONArray();
        try {
            FileReader reader = new FileReader(STATS_FILE_PATH);

            if (isFileEmpty()) {
                return players;
            }

            Object obj = parser.parse(reader);

            reader.close();

            JSONObject jsonObject = (JSONObject) obj;
            players = (JSONArray) jsonObject.get("players");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortArray(players);
    }

    public JSONArray sortArray(JSONArray jsonArray) {

        JSONArray sortedJsonArray = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonValues.add((JSONObject) jsonArray.get(i));
        }

        jsonValues.sort(new Comparator<JSONObject>() {
            private static final String KEY = "score";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                Long scoreA = (Long) a.get(KEY);
                Long scoreB = (Long) b.get(KEY);

                // Для сортування за спаданням змініть порядок scoreB і scoreA
                return scoreB.compareTo(scoreA);
            }
        });

        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }

        return sortedJsonArray;
    }

    public boolean isFileEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATS_FILE_PATH))) {
            return reader.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately in your code
        }
    }

    public void addStats(String name, long score) {

        boolean isPlayerExists = false;
        JSONArray players = getStatsJSON(); // Initialize an empty array
        try {
            for (Object playerObj : players) {
                JSONObject player = (JSONObject) playerObj;
                if (player.get("name").equals(name)) {
                    isPlayerExists = true;
                    long currentScore = Long.parseLong(player.get("score").toString());
                    player.replace("score", currentScore + score);
                }
            }

            if (!isPlayerExists) {
                JSONObject player = new JSONObject();
                player.put("name", name);
                player.put("score", score);
                players.add(player);
            }

            writeToFile(players);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(JSONArray players) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("players", players);

            FileWriter fileWriter = new FileWriter(STATS_FILE_PATH);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
