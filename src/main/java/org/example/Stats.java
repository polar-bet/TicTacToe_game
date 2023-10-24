package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
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
        System.out.printf(Game.ANSI_BLUE + "▛----------------------------▜%n");
        System.out.printf("| %-2s | %-10s | %-8s |%n", "№", "ІМ'Я", "РАХУНОК");
        System.out.printf("------------------------------%n");
        try {
            JSONArray players = getStatsJSON();
            JSONArray playersTop = new JSONArray();
            playersTop.addAll(players.size() >= TOP_COUNT
                    ? players.subList(0, TOP_COUNT)
                    : players);
            for (Object objPlayer : playersTop) {
                JSONObject player = (JSONObject) objPlayer;
                String name = (String) player.get("name");
                long score = (long) player.get("score");
                System.out.printf("| %-2s | %-10s | %-8s |%n", players.indexOf(player) + 1, name, score);
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
            File file = new File(STATS_FILE_PATH);

            file.createNewFile();

            if (file.length() == 0) {
                return players;
            }

            FileReader reader = new FileReader(STATS_FILE_PATH);

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            reader.close();

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

    public JSONObject getExistedPlayer(JSONArray players, String name){
        for (Object playerObj : players) {
            JSONObject player = (JSONObject) playerObj;
            if (player.get("name").equals(name)) {
                return player;
            }
        }
        return new JSONObject();
    }
    public void addStats(String name, long score) {

        JSONArray players = getStatsJSON();
        try {
            JSONObject player = getExistedPlayer(players, name);

            if (!player.isEmpty()) {
                long currentScore = Long.parseLong(player.get("score").toString());
                player.replace("score", currentScore + score);
            }else {
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
