package com.aleksandra.projektarbete;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Menu menu = new Menu(player, dealer);
        System.out.println("Welcome to BlackJack!");
        menu.runInteractionWithUser();  // Starts Blackjack

        // TODO - Being able to draw a card
        // #1 - deckOfCards - 1 Card LESS
        // #2 - Calculate Card Value
        // #3 - Do While - Scanner + Switch cases

    }
}
