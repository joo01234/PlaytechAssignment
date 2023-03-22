package main.java;

public class Turn {
    private int timestamp, playerId;
    private String action, dealerHand, playerHand;

    public Turn(int timestamp, int playerId, String action, String dealerHand, String playerHand) {
        this.timestamp = timestamp;
        this.playerId = playerId;
        this.action = action;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(String dealerHand) {
        this.dealerHand = dealerHand;
    }

    public String getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(String playerHand) {
        this.playerHand = playerHand;
    }
}
