package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;

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

    // További játékos logika

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            switch (card.getRank()) {
                case "2":
                    value += 2;
                    break;
                case "3":
                    value += 3;
                    break;
                case "4":
                    value += 4;
                    break;
                case "5":
                    value += 5;
                    break;
                case "6":
                    value += 6;
                    break;
                case "7":
                    value += 7;
                    break;
                case "8":
                    value += 8;
                    break;
                case "9":
                    value += 9;
                    break;
                case "10":
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


    public boolean isFiveCardTrick() {
        return hand.size() == 5 && getHandValue() <= 21;
    }

    public boolean isPush(Player other) {
        return getHandValue() == other.getHandValue();
    }

    public boolean hasBetterHandThan(Player other) {
        return getHandValue() > other.getHandValue();
    }

    public boolean hasLesserHandThan(Player other) {
        return getHandValue() < other.getHandValue();
    }




}
