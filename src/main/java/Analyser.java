package main.java;

import main.java.utils.Reader;
import main.java.utils.Writer;

import java.util.*;

public class Analyser {
    /**
     * This is the main method of the program, which reads game data from a file, constructs sessions from the data,
     * sorts the turns of each session chronologically, and analyzes the sessions to identify invalid turns.
     *
     * @param args an array of command-line arguments that are not used in this program
     */
    public static void main(String[] args) {
        Reader r = new Reader("src/main/resources/game_data.txt");
        Map<Integer, Session> sessionMap = new TreeMap<>();
        String line;
        while ((line=r.readLine()) != null) {
            if (!FormatChecker.isCorrectLineFormat(line)) {
                continue;
            }
            addToSession(sessionMap, line);
        }
        r.close();
        sortTurns(sessionMap);
        analyseSessions(sessionMap);
    }

    /**
     * Analyses the turns in each session of the provided session map and writes any invalid turns to a file.
     * An invalid turn is a turn that is not allowed according to the game rules or the session state.
     *
     * @param sessionMap the map of sessions to be analysed
     * @throws NullPointerException if the sessionMap parameter is null
     */
    private static void analyseSessions(Map<Integer, Session> sessionMap) {
        Writer w = new Writer("analyzer_results.txt");
        for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            for (Turn turn : e.getValue().getTurns()) {
                if (turn.getAction().equals("p joined")) {
                    e.getValue().setHasPlayer(true);
                } else if (turn.getAction().equals("p left")) {
                    if (!e.getValue().hasPlayer()) {
                        w.writeLine(turn.getRawLine());
                    }
                    e.getValue().setHasPlayer(false);
                }
                if (!isValidTurn(turn, e.getValue().hasPlayer())) {
                    w.writeLine(turn.getRawLine());
                    break;
                }
            }
        }
        w.close();
    }

    /**
     * Determines if the given turn is valid based on the rules of the game.
     *
     * @param turn      the turn to validate
     * @param hasPlayer indicates whether the session has a player
     * @return true if the turn is valid, false otherwise
     * @throws NullPointerException if turn is null
     */
    private static boolean isValidTurn(Turn turn, boolean hasPlayer) {
        if (turn.hasDuplicateCards()) {
            return false;
        }
        if (turn.getDealerHandValue() > 21 && turn.getPlayerHandValue() > 21) {
            return false;
        } else if (turn.getDealerHandValue() > 21) {
            if (!turn.getAction().equals("p win")) {
                return false;
            }
        } else if (turn.getPlayerHandValue() > 21) {
            if (!turn.getAction().equals("p lose")) {
                return false;
            }
        }
        if (turn.getDealerHandValue() == -1 || turn.getPlayerHandValue() == -1) {
            return false;
        }
        if (hasPlayer) {
            if (turn.getDealerHandValue() == 0 || turn.getPlayerHandValue() == 0) {
                return false;
            }
        }
        switch (turn.getAction()) {
            case "p joined":
            case "d redeal":
                if (Turn.findCardAmount(turn.getDealerHand()) != 2 || Turn.findCardAmount(turn.getPlayerHand()) != 2) {
                    return false;
                }
                if (!Turn.hasUnknownCard(turn.getDealerHand())) {
                    return false;
                }
                break;

            case "d hit":
                if (turn.getDealerHandValue() >= 17) {
                    return false;
                }
                if (Turn.hasUnknownCard(turn.getDealerHand())) {
                    return false;
                }
                break;

            case "d show":
                if (!Turn.hasUnknownCard(turn.getDealerHand())) {
                    return false;
                }
                break;

            case "p win":
                if (turn.getDealerHandValue() < 17) {
                    return false;
                }
                if (turn.getPlayerHandValue() < turn.getDealerHandValue()) {
                    return false;
                }
                break;

            case "p lose":
                if (turn.getPlayerHandValue() >= turn.getDealerHandValue()) {
                    return false;
                }
                break;

            case "p hit":
                if (turn.getPlayerHandValue() >= 20) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * Adds a Turn object to a Session object in the provided sessionMap.
     *
     * @param sessionMap The Map containing all Session objects.
     * @param line The String representing a Turn object.
     * @throws NullPointerException if sessionMap or line is null.
     */
    private static void addToSession(Map<Integer, Session> sessionMap, String line) {
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

    /**
     * Creates a new Turn object from a String array containing the necessary data.
     * The data is expected to be in the format: [timestamp, sessionId, playerId, action, playerHand, dealerHand]
     *
     * @param lineArr the String array containing the data needed to create a Turn object
     * @return a new Turn object containing the data from the input String array
     * @throws NumberFormatException if the timestamp or playerId cannot be parsed as integers
     */
    private static Turn createTurn(String[] lineArr) {
        Turn turn;
        int timestamp = 0, playerId = 0;
        try {
            timestamp = Integer.parseInt(lineArr[0]);
            playerId = Integer.parseInt(lineArr[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String line = String.join(",", lineArr);
        turn = new Turn(timestamp, playerId, lineArr[3].toLowerCase(), lineArr[4], lineArr[5], line);
        return turn;
    }

    /**
     * Sorts the turns in the given session map based on their timestamp.
     *
     * @param sessionMap the map of session IDs to sessions
     * @throws NullPointerException if the sessionMap is null
     */
    private static void sortTurns(Map<Integer, Session> sessionMap) {
        for (Map.Entry<Integer, Session> e : sessionMap.entrySet()) {
            ArrayList<Turn> turns = e.getValue().getTurns();
            turns.sort(Comparator.comparingInt(Turn::getTimestamp));
        }
    }
}