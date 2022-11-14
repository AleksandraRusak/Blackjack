package com.aleksandra.projektarbete;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player {

    private static final int MAX_BET = 500;    // max bet 500$
    private static final int MIN_BET = 2;      // min bet 2$
    private double bet;                        // Amount player bets on a game
    private List<Card> hand = new ArrayList<>();
    private double accountBalance = MAX_BET;    // players balance
    private int wins = 0, losses = 0;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean makeBet() {
        System.out.println(getName() + " has " + getAccountBalance() + "$");  // player has 500$ from the beginning
        System.out.println("Make your bet: ");
        Scanner scanner = new Scanner(System.in);
        try {
            bet = scanner.nextDouble();
            if (bet < MIN_BET) {
                System.out.println("Please make your bet between " + MIN_BET + "$ and " + MAX_BET + "$");
            } else if (MAX_BET >= bet) {
                setAccountBalance(getAccountBalance() - bet);
                System.out.println("Account balance " + getAccountBalance() + "$");
                return true;
            } else if (bet > getAccountBalance()) {
                System.out.println("You don't have enough money to bet");
            } else {
                System.out.println("Try again");
            }
        } catch (InputMismatchException e) {
            System.out.println("You entered wrong value. Try again.");
        }

        return false;
    }

    public void draw(List<Card> deckOfCards) {
        if (!deckOfCards.isEmpty()) {
            System.out.println(getName() + " gets a card.");
            hand.add(deckOfCards.get(0));
            deckOfCards.remove(0);
        }
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}

