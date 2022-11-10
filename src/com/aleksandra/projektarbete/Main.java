package com.aleksandra.projektarbete;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Deck deckTemplate = new Deck();
        Player player1 = new Player();
        Dealer dealer = new Dealer();
        dealer.setName("Dealer");


        List<Card> deckOfCards = deckTemplate.generateDeck();  // Sorted by default
        Menu menu = new Menu(player1, dealer, deckOfCards);

        menu.mainMenu();  // Starts Blackjack





        // TODO - Being able to draw a card
        // #1 - deckOfCards - 1 Card LESS
        // #2 - Calculate Card Value
        // #3 - Do While - Scanner + Switch cases

    }
}
