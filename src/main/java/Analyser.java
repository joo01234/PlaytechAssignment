package main.java;

import main.java.utils.Reader;
import main.java.utils.Writer;

import java.util.*;

public class Analyser {
    public static void main(String[] args) {
        Reader r = new Reader();
        r.readFromFile("src/main/resources/game_data.txt");
        Map<Integer, Session> sessionMap = new TreeMap<>();
        String line;
        while ((line=r.readLine()) != null) {
            if (!InputChecker.isCorrectLineFormat(line)) {
                continue;
            }
            addToSession(sessionMap, line);
        }
        r.close();
        sortTurns(sessionMap);
        analyseSessions(sessionMap);
        /*for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            ArrayList<Turn> turns = e.getValue().getTurns();
            for (Turn turn : turns) {
                System.out.println(turn.getDealerHandValue() + "," + turn.getPlayerHandValue() + "," + turn.getTimestamp() + "," + e.getValue() .getSessionId() + "," + turn.getAction() + "," + turn.getDealerHand() + "," + turn.getPlayerHand());
            }
        }*/
    }

    public static void analyseSessions(Map<Integer, Session> sessionMap) {
        Writer w = new Writer();
        w.writeToFile("analyzer_results.txt");
        for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            for (Turn turn : e.getValue().getTurns()) {
                if (!Turn.isValidTurn(turn)) {
                    String line = turn.getTimestamp() + "," + e.getValue().getSessionId() + "," + turn.getPlayerId() + ","
                            + turn.getAction() + "," + turn.getDealerHand() + "," + turn.getPlayerHand() + "," + turn.getDealerHandValue() + "," + turn.getPlayerHandValue();
                    System.out.println(line);
                    w.writeLine(line);
                    break;
                }
            }
        }
        w.close();
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

    public static void sortTurns(Map<Integer, Session> sessionMap) {
        for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            ArrayList<Turn> turns = e.getValue().getTurns();
            turns.sort(Comparator.comparingInt(Turn::getTimestamp));
        }
    }
}