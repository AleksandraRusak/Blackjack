package com.aleksandra.projektarbete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Player player;    // <-- NULL
    Dealer dealer;    // <-- NULL
    List<Card> deckOfCards; // <-- NULL
    List<Player> listOfPlayers = new ArrayList<>();




    // Constructor for the Menu class, creates our variables and starts the game
    public Menu(Player player, Dealer dealer, List<Card> deckOfCards) {
        this.player = player;                 // <-- player is no longer NULL
        this.dealer = dealer;                 // <-- dealer is no longer NULL
        this.deckOfCards = deckOfCards;       // <-- deckOfCards is no longer NULL


        listOfPlayers.add(dealer);
        listOfPlayers.add(player);

    }


    public void mainMenu() {

        boolean isPlaying = true;

        do {
            System.out.println("""
                    Welcome to BlackJack\s
                     
                    1 - Start game\s
                    2 - What is your name? \s
                    0 - Exit Game\s
                    """);

            switch (scanner.next()) {
                case "1" -> menuStartGame();
                case "2" -> setPlayersName();

                case "0" -> isPlaying = false;

                default -> System.out.println("Error");
            }

        } while (isPlaying);

    }

    private void setPlayersName() {
        System.out.println("Please enter your name");
        String name = scanner.next();
        player.setName(name);
    }

    public void menuStartGame() {

        player.makeBet(); // make a bet

        //TODO - shuffle
        Collections.shuffle(deckOfCards);    // Shuffle the deck

        //If this isn't the first round, display the users score and put their cards back in the deck
        /*if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);

        }
        */


        //TODO - receive 2 cards initially


        player.draw(deckOfCards);   // cards for player
        player.draw(deckOfCards);

        dealer.draw(deckOfCards);    // cards for dealer
        dealer.draw(deckOfCards);



        System.out.println("Would you like to hit (enter h) or stay (enter other symbol)?");
        String userResponse = scanner.next();
        if (userResponse.equals("h")) {
            System.out.println("Player hit.");
            makeRound();

        } else {
            System.out.println("Player stands, dealer draws.");
            dealer.draw(deckOfCards);
        }


        // TODO - do while loop



        switch (scanner.next()) {
            case "1" -> {

                makeRound(); // draw card


                //TODO - check AFTER receiving card if sum > 21
                //TODO - IF 21  (blackjack)
                //TODO - bonus (VG) check ACE value

            }

            case "2" -> System.out.println("Stay");
            // TODO - dealer draws card
            // TODO - check if win or lose

        }

    }

    public void checkGameState(Player player) {     // checking total sum of cards in players hand
        int totalSum = Card.getCardsValue(player.hand);

        System.out.println(player.getName() + " state after draw: " + totalSum + " " + player.hand);

        if (totalSum == 21) {
            System.out.println(player.getName() + " won the game.");
            player.setWins(player.getWins() + 1);
            //makeRound();
            //TODO - update account balance
            double coefficient = 2.0;
            if (player.hand.size() == 2){
                coefficient = 1.5;     // if blackjack * 1.5
            }
            player.setAccountBalance(player.getAccountBalance() + Math.round(coefficient * player.getBet()));
            System.out.println(player.getAccountBalance() + " is a new balance");
        }
        else if (totalSum > 21){
            System.out.println(player.getName() + " lost. You have gone over 21.");
            player.setLosses(player.getLosses() + 1);
            //start the round over
            //makeRound();
        }
    }

    public void makeRound() {

        //TODO - draw card
        player.draw(deckOfCards);   // player draw cards
        checkGameState(player);     // sum of cards player
        System.out.println("Here is a new draw");
        System.out.println(player.hand);

        System.out.println("Would you like to hit (enter h) or stay (enter other symbol)?");
        String userResponse = scanner.next();
        if (userResponse.equals("h")) {     // player choose to hit
            makeRound();                    // player gets a card
        } else {
            dealer.draw(deckOfCards);   // player choose to stay so dealer plays now
            checkGameState(dealer);      // sum of cards dealer
            makeRound();
        }

    }

}
