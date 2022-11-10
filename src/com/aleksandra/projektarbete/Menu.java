package com.aleksandra.projektarbete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final int WINNING_TOTAL = 21;
    private final Scanner scanner = new Scanner(System.in);
    private final Player player;    // <-- NULL
    private final Dealer dealer;    // <-- NULL

    // Constructor for the Menu class, creates our variables and starts the game
    public Menu(Player player, Dealer dealer) {
        this.player = player;                 // <-- player is no longer NULL
        this.dealer = dealer;
    }

    private void setPlayersName() {
        System.out.println("""
                What is your name? \s
                """);
        String name = scanner.next();
        player.setName(name);
    }

    public void runInteractionWithUser() {
        setPlayersName();
        showMainMenu();
    }

    private void showMainMenu() {
        boolean isPlaying = true;
        do {
            System.out.println("""
                    1 - Start new game\s
                    0 - Exit Game\s
                    """);
            switch (scanner.next()) {
                case "1" -> runNewGame();
                case "0" -> isPlaying = false;
                default -> System.out.println("Enter a valid number option, please");
            }
        } while (isPlaying);
        System.exit(0);
    }

    void runNewGame() {
        System.out.println( "Player's Wins: "+ player.getWins() + " vs. Losses: "+ player.getLosses());
        System.out.println("--NEW GAME--");
        if (player.getAccountBalance() > 0) {
            player.setHand(new ArrayList<>());
            dealer.setHand(new ArrayList<>());
            if (player.makeBet()) {
                List<Card> deckOfCards = new Deck().generateDeck();
                Collections.shuffle(deckOfCards);   // Shuffle the deck
                System.out.println("The deck has just gotten reshuffled.");
                player.draw(deckOfCards);   // initial pair cards for player
                player.draw(deckOfCards);
                System.out.println("Initial hand of " + player.getName() + ": " + player.getHand());
                dealer.draw(deckOfCards);    // initial pair of cards for dealer
                dealer.draw(deckOfCards);

                if (isGameFinished(player, dealer)) {
                    showMainMenu();
                } else {
                    makeRound(deckOfCards);
                }
            }
            else {
                runNewGame();
            }
        }
        else {
            System.out.println(player.getName() + " has not enough money on the account.");
        }
    }

    public boolean isGameFinished(Player player, Dealer dealer) {     // checking total sum of cards in players hand
        int totalSumOfPlayer = Card.getCardsValue(player.getHand());
        int totalSumOfDealer = Card.getCardsValue(dealer.getHand());
        System.out.println(player.getName() + "'s total hand is " + Card.getCardsValue(player.getHand()) + "; " + dealer.getName() + "'s total is " + Card.getCardsValue(dealer.getHand()));
        if ((dealer.getHand().size() > 2 && totalSumOfDealer > totalSumOfPlayer && totalSumOfDealer <= WINNING_TOTAL) ||
                (totalSumOfDealer == WINNING_TOTAL && totalSumOfPlayer < WINNING_TOTAL)
        ) {
            System.out.println(player.getName() + " lost.");
            player.setLosses(player.getLosses() + 1);
            System.out.println(player.getAccountBalance() + " is a new balance of " + player.getName());
            return true;
        }
        else

        if ((totalSumOfDealer == WINNING_TOTAL && totalSumOfPlayer == WINNING_TOTAL) || (totalSumOfDealer == totalSumOfPlayer && (dealer.getHand().size() + player.getHand().size() > 4))) {
            System.out.println("The dealer and the player got the same. It's a draw!");
            player.setAccountBalance(player.getAccountBalance() + player.getBet());
            dealer.setAccountBalance(dealer.getAccountBalance() + dealer.getBet());
            return true;
        }
        else
        if (totalSumOfPlayer == WINNING_TOTAL || (totalSumOfPlayer < WINNING_TOTAL && totalSumOfDealer > WINNING_TOTAL)) {
            System.out.println(player.getName() + " won the game.");
            double dealerBet = player.getBet(); // initially it's assumed as the same as player's
            if (player.getHand().size() == 2) {
                // blackjack case happens when a player has initial two cards only
                if (dealer.getHand().size() == 2 && Card.getCardsValue(dealer.getHand()) == WINNING_TOTAL) {// push happened
                    player.setAccountBalance(player.getAccountBalance() + player.getBet());
                } else {
                    dealerBet = 1.5 * (player.getBet());     // if blackjack * 1.5
                    player.setWins(player.getWins() + 1);
                }
            }
            player.setWins(player.getWins() + 1);
            player.setAccountBalance(player.getAccountBalance() + player.getBet() + dealerBet);
            System.out.println(player.getAccountBalance() + " is current balance of " + player.getName());
            return true;
        } else if (totalSumOfPlayer > WINNING_TOTAL) {
            System.out.println(player.getName() + " lost. " + player.getName() + " has gone over " + WINNING_TOTAL + " in the hand.");
            player.setLosses(player.getLosses() + 1);
            System.out.println(player.getAccountBalance() + " is a new balance of " + player.getName());
            return true;
        }
        return false;
    }

    public void makeRound(List<Card> deckOfCards) {
        System.out.println("Would you like to hit (enter h) or stand (enter other symbol)?");
        String userResponse = scanner.next();
        if (userResponse.equals("h")) {
            System.out.println(player.getName() + " hit.");
            player.draw(deckOfCards);
        } else {
            System.out.println(player.getName() + " stands, " + dealer.getName() + " draws.");
            int dealerCurrentTotal = Card.getCardsValue(dealer.getHand());
            int playerCurrentTotal = Card.getCardsValue(player.getHand());
            while (dealerCurrentTotal < playerCurrentTotal) {
                dealer.draw(deckOfCards);
                dealerCurrentTotal = Card.getCardsValue(dealer.getHand());
            }
            System.out.println("dealer hand (" + dealerCurrentTotal + ") is " + dealer.getHand());
        }
        System.out.println(player.getName() + " has " + Card.getCardsValue(player.getHand()) + " points, the hand now is " + player.getHand());
        if (isGameFinished(player, dealer)) {
            showMainMenu();
        } else {
            makeRound(deckOfCards);
        }
    }
}
