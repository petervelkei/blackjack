package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                String imagePath = "cards/" + rank + "_of_" + suit + ".png";
                cards.add(new Card(suit, rank, imagePath));
            }
        }
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    public void shuffle() {
        shuffleCards();
    }

    public Card draw() {
        return cards.remove(cards.size() - 1);
    }

    public void reset() {
        cards.clear();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                String imagePath = "cards/" + rank + "_of_" + suit + ".png";
                cards.add(new Card(suit, rank, imagePath));
            }
        }
        shuffleCards();
    }
}