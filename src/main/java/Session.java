package main.java;

import java.util.ArrayList;

public class Session {
    private int sessionID;
    private ArrayList<Turn> turns;

    public Session(int sessionID) {
        this.sessionID = sessionID;
    }

    public void addMove(Turn turn) {
        this.turns.add(turn);
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public void setTurns(ArrayList<Turn> turns) {
        this.turns = turns;
    }
}
