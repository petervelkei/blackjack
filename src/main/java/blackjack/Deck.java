package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Deck osztály, amely a kártyapaklit reprezentálja.
 * 
 * Ez az osztály tartalmazza a kártyapaklit, és biztosítja a kártyák inicializálását,
 * keverését, húzását és visszaállítását.
 */
public class Deck {
    private final List<Card> cards;

    /**
     * Létrehoz egy új Deck objektumot, és inicializálja a kártyapaklit.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Inicializálja a kártyapaklit a szokásos 52 kártyával.
     * 
     * Ez a metódus létrehozza a kártyákat a négy szín (hearts, diamonds, clubs, spades)
     * és a tizenhárom rang (2-10, jack, queen, king, ace) kombinációjával, majd
     * hozzáadja őket a paklihoz. A kártyák képeinek elérési útját is beállítja.
     * Végül megkeveri a kártyákat.
     */
    private void initializeDeck() {
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

    /**
     * Megkeveri a kártyapaklit.
     * 
     * Ez a metódus véletlenszerű sorrendbe rendezi a kártyákat a pakliban.
     */
    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    /**
     * Megkeveri a kártyapaklit.
     * 
     * Ez a metódus meghívja a shuffleCards metódust, hogy véletlenszerű sorrendbe
     * rendezze a kártyákat a pakliban.
     */
    public void shuffle() {
        shuffleCards();
    }

    /**
     * Húz egy kártyát a kártyapakli tetejéről.
     * 
     * Ez a metódus eltávolítja és visszaadja a kártyapakli utolsó kártyáját.
     * 
     * @return A kártyapakli utolsó kártyája.
     */
    public Card draw() {
        return cards.remove(cards.size() - 1);
    }

    /**
     * Visszaállítja a kártyapaklit az eredeti állapotába.
     * 
     * Ez a metódus törli a jelenlegi kártyákat a pakliból, majd újra inicializálja
     * a kártyapaklit a szokásos 52 kártyával és megkeveri őket.
     */
    public void reset() {
        cards.clear();
        initializeDeck();
    }
}