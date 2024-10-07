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
        dealer.addCard(deck.draw(), true);
        ui.updateUI();
    }

    public void playerDrawCard() {
        player.addCard(deck.draw());
        ui.updateUI();
        if (player.isBusted() || player.hasBlackjack()) {
            endGame();
        }
    }

    public void dealerDrawCard() {
        dealer.addCard(deck.draw());
        ui.updateUI();
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isGameOver() {
        return player.isBusted() || dealer.isBusted() || player.isStanding() || player.hasBlackjack() || dealer.hasBlackjack(); 
    }

    public void endGame() {
        dealer.revealHiddenCard(); // Felfedjük a lefordított lapot
        while (dealer.mustDrawCard()) {
            dealerDrawCard();
        }

        if (dealer.isBusted() || player.hasBetterHandThan(dealer) || player.hasBlackjack()) {
            ui.showEndGameMessage("Player wins!");
        } else if (player.isBusted() || dealer.hasBetterHandThan(player) || dealer.hasBlackjack()) {
            ui.showEndGameMessage("Dealer wins!");
        } else {
            ui.showEndGameMessage("Push!");
        }
    }

    public int calculatePoints(List<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            switch (card.getRank()) {
                case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> value += Integer.parseInt(card.getRank());
                case "jack", "queen", "king" -> value += 10;
                case "ace" -> aceCount++;
                default -> throw new IllegalArgumentException("Unexpected card rank: " + card.getRank());
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