package main.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Turn {
    private int timestamp, playerId, dealerHandValue, playerHandValue;
    private String action, dealerHand, playerHand, rawLine;

    /**
     * Constructor for the Turn class.
     *
     * @param timestamp The timestamp of the turn.
     * @param playerId The ID of the player taking the turn.
     * @param action The action taken by the player or dealer.
     * @param dealerHand The dealer's hand before the action takes place.
     * @param playerHand The player's hand before the action takes place.
     * @param rawLine The raw input line from the log file.
     */
    public Turn(int timestamp, int playerId, String action, String dealerHand, String playerHand, String rawLine) {
        this.timestamp = timestamp;
        this.playerId = playerId;
        this.action = action;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
        this.dealerHandValue = findValue(dealerHand);
        this.playerHandValue = findValue(playerHand);
        this.rawLine = rawLine;
    }

    public String getRawLine() {
        return rawLine;
    }

    public void setRawLine(String rawLine) {
        this.rawLine = rawLine;
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

    /**
     * Determines whether there are any duplicate cards in the dealer's and player's hands.
     * Returns true if there are duplicates, and false otherwise.
     *
     * @return boolean indicating whether there are duplicate cards in the hands
     */
    public boolean hasDuplicateCards() {
        String[] dealerHandArr = this.dealerHand.split("-");
        String[] playerHandArr = this.playerHand.split("-");
        for (int i = 0; i < dealerHandArr.length; i++) {
            dealerHandArr[i] = dealerHandArr[i].toLowerCase();
        }
        for (int i = 0; i < playerHandArr.length; i++) {
            playerHandArr[i] = playerHandArr[i].toLowerCase();
        }
        Set<String> dealerHandSet = new HashSet<>();
        Set<String> playerHandSet = new HashSet<>();
        Collections.addAll(dealerHandSet, dealerHandArr);
        Collections.addAll(playerHandSet, playerHandArr);
        if (dealerHandSet.size() < dealerHandArr.length) {
            return true;
        }
        if (playerHandSet.size() < playerHandArr.length) {
            return true;
        }
        for (String dealerCard : dealerHandArr) {
            if (Arrays.asList(playerHandArr).contains(dealerCard)) {
                return true;
            }
        }
        for (String playerCard : playerHandArr) {
            if (Arrays.asList(dealerHandArr).contains(playerCard)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether the given hand has an unknown card (represented by a "?" symbol).
     *
     * @param hand the hand to check for an unknown card
     * @return true if the hand has an unknown card, false otherwise
     */
    public static boolean hasUnknownCard(String hand) {
        String[] handArr = hand.split("-");
        for (String card : handArr) {
            if (card.equals("?")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the total value of a hand. Unknown cards (?) are treated as having a value of 0.
     *
     * @param hand a string representing the hand to be evaluated
     * @return the total value of the hand, or -1 if the input is not in the correct format
     */
    public static int findValue(String hand) {
        String[] handArr = hand.split("-");
        int total = 0;
        for (String card : handArr) {
            if (!FormatChecker.isCorrectCardFormat(card)) {
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
            else if (firstChar == 'A') {
                total += 11;
            }
            else {
                total += 10;
            }
        }
        return total;
    }

    /**
     * Finds the amount of cards in the hand.
     *
     * @param hand a string representing the hand to be evaluated
     * @return the number of cards in the hand
     */
    public static int findCardAmount(String hand) {
        String[] handArr = hand.split("-");
        return handArr.length;
    }
}
