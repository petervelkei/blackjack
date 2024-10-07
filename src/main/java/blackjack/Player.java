package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void reset() {
        hand.clear();
    }

    // addhand
    public void addHand(List<Card> cards) {
        hand.addAll(cards);
    }

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            switch (card.getRank()) {
                case "2" -> value += 2;
                case "3" -> value += 3;
                case "4" -> value += 4;
                case "5" -> value += 5;
                case "6" -> value += 6;
                case "7" -> value += 7;
                case "8" -> value += 8;
                case "9" -> value += 9;
                case "10", "jack", "queen", "king" -> value += 10;
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

    public boolean isBusted() {
        return getHandValue() > 21;
    }

    public boolean hasBlackjack() {
        return getHandValue() == 21 && hand.size() == 2;
    }

    public boolean isStanding() {
        return getHandValue() >= 17;
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean isPush(Player other) {
        return getHandValue() == other.getHandValue();
    }

    public boolean hasBetterHandThan(Player other) {
        return getHandValue() > other.getHandValue() && !isBusted();
    }

    public boolean hasLesserHandThan(Player other) {
        return getHandValue() < other.getHandValue();
    }
}