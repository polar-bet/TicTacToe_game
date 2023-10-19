package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Optional;


public class Stats {

    private final static String STATS_FILE_PATH = "./src/main/resources/user-info.json";

    public static void printStats() {
        JSONArray players = getStatsJSON();
        try {
            System.out.printf(Game.ANSI_BLUE + "-------------------------%n");
            System.out.printf("| %-10s | %-8s |%n", "ІМ'Я", "ОЧКИ");
            System.out.printf("-------------------------%n");
            for (Object objPlayer : players) {
                JSONObject player = (JSONObject) objPlayer;
                String name = (String) player.get("name");
                Long score = (Long) player.get("score");
                System.out.printf("| %-10s | %-8s |%n", name, score);
                if (players.indexOf(player) == players.size() - 1) {
                    System.out.printf("-------------------------%n" + Game.ANSI_RESET);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JSONArray getStatsJSON() {
        JSONParser parser = new JSONParser();
        JSONArray players = new JSONArray();
        try {
            FileReader reader = new FileReader(STATS_FILE_PATH);

            if (isFileEmpty()){
                return new JSONArray();
            }

            Object obj = parser.parse(reader);

            reader.close();

            JSONObject jsonObject = (JSONObject) obj;
            players = (JSONArray) jsonObject.get("players");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public static boolean isFileEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATS_FILE_PATH))) {
            return reader.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately in your code
        }
    }

    public static void addStats(String name, long score) {

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

    public static void writeToFile(JSONArray players) {
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
