package com.aleksandra.projektarbete;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Menu menu = new Menu(player, dealer);
        System.out.println("Welcome to BlackJack!");

        menu.runInteractionWithUser();  // Starts Blackjack

    }
}
