package blackjack;

/**
 * Minden kártyaszínhez tartozik egy betűjel.
 */
public enum Suit {
    CLUBS( 'c'),
    SPADES( 's'),
    HEARTS( 'h'),
    DIAMONDS( 'd');

    public final char l;

    /**
     * Konstruktor, amely beállítja a kártyaszín betűjelét.
     * 
     * @param l A kártyaszín betűjele
     */
    Suit(char l) {
        this.l = l;
    }
}