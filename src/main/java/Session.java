package main.java;

import java.util.ArrayList;

public class Session {
    private int sessionId;
    private ArrayList<Turn> turns;

    public Session(int sessionId, Turn turn) {
        this.sessionId = sessionId;
        this.turns = new ArrayList<>();
        this.turns.add(turn);
    }

    public void addTurn(Turn turn) {
        this.turns.add(turn);
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public void setTurns(ArrayList<Turn> turns) {
        this.turns = turns;
    }
}
