package main.java;

import main.java.utils.Reader;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Analyser {
    public static void main(String[] args) {
        Reader r = new Reader();
        r.readFile("src/main/resources/game_data.txt");
        Map<Integer, Session> sessionMap = new TreeMap<>();
        String line;
        while ((line=r.readLine()) != null) {
            if (!InputChecker.isCorrectLineFormat(line)) {
                continue;
            }
            addToSession(sessionMap, line);
        }
        for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            ArrayList<Turn> value = e.getValue().getTurns();
            for (Turn turn : value) {
                System.out.println(turn.getAction());
            }
        }
    }

    public static void addToSession(Map<Integer, Session> sessionMap, String line) {
        String[] lineArr = line.split(",");
        int sessionId = 0;
        try {
            sessionId = Integer.parseInt(lineArr[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Turn turn = createTurn(lineArr);
        if (!sessionMap.containsKey(sessionId)) {
            Session session = new Session(sessionId, turn);
            sessionMap.put(sessionId, session);
            return;
        }
        Session session = sessionMap.get(sessionId);
        session.addTurn(turn);
    }


    public static Turn createTurn(String[] lineArr) {
        Turn turn;
        int timestamp = 0, playerId = 0;
        try {
            timestamp = Integer.parseInt(lineArr[0]);
            playerId = Integer.parseInt(lineArr[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        turn = new Turn(timestamp, playerId, lineArr[3], lineArr[4], lineArr[5]);
        return turn;
    }
}