package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Stats {

    public static void printStats() {
        JSONParser parser = new JSONParser();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("user-info.json");

            InputStreamReader reader = new InputStreamReader(inputStream);

            Object obj = parser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray players = (JSONArray) jsonObject.get("players");

            System.out.printf(Game.ANSI_BLUE + "-------------------------%n");
            System.out.printf("| %-10s | %-8s |%n", "ІМ'Я", "ОЧКИ");
            System.out.printf("-------------------------%n");
            for (Object player : players) {
                JSONObject objPlayer = (JSONObject) player;
                String name = (String) objPlayer.get("name");
                Long score = (Long) objPlayer.get("score");
                System.out.printf("| %-10s | %-8s |%n", name, score);
                if (players.indexOf(player) == players.size() - 1) {
                    System.out.printf("-------------------------%n" + Game.ANSI_RESET);
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
