package blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * A Player osztály, amely a játékos kártyáit és pontszámát kezeli.
 * 
 * Ez az osztály tartalmazza a játékos kezében lévő kártyák listáját, valamint
 * a kártyák hozzáadására, eltávolítására és értékük kiszámítására szolgáló metódusokat.
 */
public class Player {
    private final List<Card> hand;

    /**
     * Létrehoz egy új Player objektumot üres kézzel.
     */
    public Player() {
        hand = new ArrayList<>();
    }

    /**
     * Hozzáad egy kártyát a játékos kezéhez.
     * 
     * @param card A hozzáadni kívánt kártya.
     */
    public void addCard(Card card) {
        hand.add(card);
    }


    /**
     * Hozzáad egy kártyát a játékos kezéhez.
     * 
     * @param card A hozzáadni kívánt kártya.
     * @param hand A játékos keze.
     */
    public void addCard(Card card, List<Card> hand) {
        hand.add(card);
    }

    /**
     * Visszaadja a játékos kezében lévő kártyák listáját.
     * 
     * @return A játékos kezében lévő kártyák listája.
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Törli a játékos kezében lévő kártyákat.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Hozzáad egy listányi kártyát a játékos kezéhez.
     * 
     * @param cards A hozzáadni kívánt kártyák listája.
     */
    public void addHand(List<Card> cards) {
        hand.addAll(cards);
    }


    /**
     * Visszaadja a játékos kezének összértékét.
     * 
     * Az ászok értéke 11 vagy 1 lehet, attól függően, hogy melyik érték
     * tartja a kéz összértékét 21 alatt vagy annak megfelelően.
     * 
     * @return A játékos kezének összértéke.
     */
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

    /**
     * Ellenőrzi, hogy a játékos keze túllépte-e a 21-et.
     * 
     * @return true, ha a játékos keze túllépte a 21-et, különben false.
     */
    public boolean isBusted() {
        return getHandValue() > 21;
    }

    /**
     * Ellenőrzi, hogy a játékosnak blackjack-je van-e.
     * 
     * @return true, ha a játékos keze pontosan 21 és két kártyából áll, különben false.
     */
    public boolean hasBlackjack() {
        return getHandValue() == 21 && hand.size() == 2;
    }


    /**
     * Ellenőrzi, hogy a játékos keze megegyezik-e egy másik játékos kezével.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk a játékos kezét.
     * @return true, ha a játékos keze megegyezik a másik játékos kezével, különben false.
     */
    public boolean isPush(Player other) {
        return getHandValue() == other.getHandValue();
    }

    /**
     * Ellenőrzi, hogy a játékos keze jobb-e, mint egy másik játékos keze.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk a játékos kezét.
     * @return true, ha a játékos keze jobb, mint a másik játékos keze és a játékos nem lépte túl a 21-et, különben false.
     */
    public boolean hasBetterHandThan(Player other) {
        return getHandValue() > other.getHandValue() && !isBusted();
    }

    /**
     * Ellenőrzi, hogy a játékos keze rosszabb-e, mint egy másik játékos keze.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk a játékos kezét.
     * @return true, ha a játékos keze rosszabb, mint a másik játékos keze, különben false.
     */
    public boolean hasLesserHandThan(Player other) {
        return getHandValue() < other.getHandValue();
    }
}