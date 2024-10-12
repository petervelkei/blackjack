package blackjack;

/**
 * Minden kártyaszínhez tartozik egy szimbólum és egy betűjel.
 * A szimbólum a kártya grafikus megjelenítéséhez, a betűjel pedig a kódoláshoz használható.
 * 
 * Színek:
 * - CLUBS (Treff)
 * - SPADES (Pikk)
 * - HEARTS (Kőr)
 * - DIAMONDS (Káró)
 * 
 * @author Wampie
 */
public enum Suit {
    CLUBS('\u2663', 'c'),    // Treff: ♣, c
    SPADES('\u2660', 's'),   // Pikk: ♠, s
    HEARTS('\u2665', 'h'),   // Kőr: ♥, h
    DIAMONDS('\u2666', 'd'); // Káró: ♦, d

    public final char symbol; // A kártyaszín szimbóluma
    public final char letter; // A kártyaszín betűjele

    /**
     * Konstruktor, amely beállítja a kártyaszín szimbólumát és betűjelét.
     * 
     * @param c A kártyaszín szimbóluma
     * @param l A kártyaszín betűjele
     */
    Suit(char c, char l) {
        symbol = c;
        letter = l;
    }
}