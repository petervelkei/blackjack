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
 */
public enum Suit {
    CLUBS( 'c'),
    SPADES( 's'),
    HEARTS( 'h'),
    DIAMONDS( 'd');

    public final char l; // A kártyaszín szimbóluma

    /**
     * Konstruktor, amely beállítja a kártyaszín szimbólumát és betűjelét.
     * 
     * @param l A kártyaszín betűjele
     */
    Suit(char l) {
        this.l = l;
    }
}