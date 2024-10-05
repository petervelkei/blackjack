package blackjack;

import java.util.List;

public class Game {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final UI ui;

    public Game() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        ui = new UI(this);
    }

    public void start() {
        ui.createAndShowGUI();
        dealInitialCards();
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void dealInitialCards() {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
        ui.updateUI();
    }

    public void playerDrawCard() {
        player.addCard(deck.draw());
        ui.updateUI();
    }

    public void dealerDrawCard() {
        dealer.addCard(deck.draw());
        ui.updateUI();
    }

    public boolean isGameOver() {
        return player.isBusted() || dealer.isBusted() || player.isStanding();
    }

    public void endGame() {
        while (dealer.mustDrawCard()) {
            dealerDrawCard();
        }

        if (dealer.isBusted() || player.hasBetterHandThan(dealer)) {
            System.out.println("Player wins!");
        } else if (player.isBusted() || dealer.hasBetterHandThan(player)) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("Push!");
        }
    }

    public int calculatePoints(List<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            switch (card.getRank()) {
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "10":
                    value += Integer.parseInt(card.getRank());
                    break;
                case "jack":
                case "queen":
                case "king":
                    value += 10;
                    break;
                case "ace":
                    aceCount++;
                    break;
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }

        return value;
    }

    public void restart() {
        deck.reset();
        player.reset();
        dealer.reset();
        dealInitialCards();
        ui.updateUI();
    }
}