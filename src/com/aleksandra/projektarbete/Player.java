package com.aleksandra.projektarbete;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    // TODO - privet modifyer

    public static int MAX_BET = 500;    // max bet 500$
    public static int MIN_BET = 2;      // min bet 2$

    private long accountBalance = MAX_BET;    // players balance

    int bet;        // Amount player bets on a game
    List<Card> hand = new ArrayList<>();
    private int wins, losses, pushes;

    private String name = "Player";         // if player doesn't set name, so we call player for "Player"

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //TODO - initial 'bet'
    public void makeBet() {

        System.out.println("You have " + MAX_BET + "$");
        System.out.println("Make your bet: ");
        Scanner scanner = new Scanner(System.in);
        bet = scanner.nextInt();
        if (bet < MIN_BET) System.out.println("Please make your bet between " + MIN_BET + "$ and " + MAX_BET + "$");
        else if (MAX_BET > bet) {
            accountBalance -= bet;
            System.out.println("Account balance " + accountBalance + "$");
        } else if (bet > accountBalance) System.out.println("You don't have enough money to bet");
        else System.out.println("Try again");
    }


    public void draw(List<Card> deckOfCards) {
        // pHand.add(deckOfCards.get(deckOfCards.size() -1));
        if (!deckOfCards.isEmpty()) {
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

    public int getPushes() {
        return pushes;
    }

    public void setPushes(int pushes) {
        this.pushes = pushes;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}

