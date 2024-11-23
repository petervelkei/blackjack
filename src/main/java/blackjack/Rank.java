package blackjack;

/**
 * játékban használt alapvető játékkártyák rangjait reprezentálja.
 */
public enum Rank {
    ACE( 1),
    KING( 10),
    QUEEN( 10),
    JACK( 10),
    TEN(10),
    NINE( 9),
    EIGHT( 8),
    SEVEN( 7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    public final int v; // A rang értéke (pl. 1, 10, 9, stb.)

    /**
     * Létrehoz egy rangot a megadott értékkel.
     *
     * @param value A rang értéke
     */
    Rank(int value) {
        v = value;
    }
}