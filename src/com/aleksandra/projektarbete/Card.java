package com.aleksandra.projektarbete;

import java.util.List;

public class Card {

    private String cardSymbol;
    private String cardValue;

    public static int getCardsValue(List<Card> hand) {    // sum of cards in a hand
        int sum = 0;
        int aceCount = 0;
        for (Card card : hand) {                //For each card in this hand
            sum += card.getCardValue();         //Add the card value to the hand
            if (card.getCardValue() == 11) {    //Count how many aces have been added
                aceCount++;
            }
        }
        int initialAceCount = aceCount;
        /*if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed
        by two or more aces, (10+11+1 > 21) go back and set each ace to 1 until get back under 21, if possible*/
        if (sum > 21 && aceCount > 0) {
            while (aceCount > 0 && sum > 21) {
                aceCount--;
                sum -= 10;
            }
        }
        return sum;
    }

    public void setCardSymbol(String cardSymbol) {
        this.cardSymbol = cardSymbol;
    }

    public int getCardValue() {

        return switch (cardValue) { // returneras
            case "Ace" -> 11;                   // TODO - Fix 1 or 11
            case "Jack", "King", "Queen" -> 10;
            default -> Integer.parseInt(cardValue); // converteras
        };
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return "{" + cardValue + cardSymbol + '}' + "\n";
    }
}

