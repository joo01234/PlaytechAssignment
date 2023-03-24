package main.java;

public class Turn {
    private int timestamp, playerId, dealerHandValue, playerHandValue;
    private String action, dealerHand, playerHand;

    public Turn(int timestamp, int playerId, String action, String dealerHand, String playerHand) {
        this.timestamp = timestamp;
        this.playerId = playerId;
        this.action = action;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
        this.dealerHandValue = findValue(dealerHand);
        this.playerHandValue = findValue(playerHand);
    }

    public int getDealerHandValue() {
        return dealerHandValue;
    }

    public void setDealerHandValue(int dealerHandValue) {
        this.dealerHandValue = dealerHandValue;
    }

    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public void setPlayerHandValue(int playerHandValue) {
        this.playerHandValue = playerHandValue;
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
        this.dealerHandValue = findValue(dealerHand);
    }

    public String getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(String playerHand) {
        this.playerHand = playerHand;
        this.playerHandValue = findValue(playerHand);
    }

    public static boolean isValidTurn(Turn turn) {
        if (turn.getDealerHandValue() > 21 || turn.getPlayerHandValue() > 21) {
            if (!turn.getAction().split(" ")[1].equals("Win")) {
                return false;
            }
        }
        switch (turn.getAction()) {
            case "D Hit":
                if (turn.getDealerHandValue() >= 17) {
                    return false;
                }
                if (hasMysteryCard(turn.getDealerHand())) {
                    return false;
                }
                break;

            case "D Show":
                if (!hasMysteryCard(turn.getDealerHand())) {
                    return false;
                }
                break;

            case "P Win":
                if (turn.getPlayerHandValue() < turn.getDealerHandValue()) {
                    return false;
                }
                break;

            case "P Lose":
                if (turn.getPlayerHandValue() >= turn.getDealerHandValue()) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static boolean hasMysteryCard(String hand) {
        String[] handArr = hand.split("-");
        for (String card : handArr) {
            if (card.equals("?")) {
                return true;
            }
        }
        return false;
    }

    public static int findValue(String hand) {
        String[] handArr = hand.split("-");
        int total = 0;
        for (String card : handArr) {
            if (!InputChecker.isCorrectCardFormat(card)) {
                return -1;
            }
            char firstChar = card.charAt(0);
            if (card.length() == 3) {
                total += 10;
            }
            else if (card.equals("?")) {
                total += 0;
            }
            else if (Character.isDigit(firstChar)) {
                total += Character.getNumericValue(firstChar);
            }
            else {
                total += 10;
            }
        }
        return total;
    }
}
