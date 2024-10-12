package blackjack;

/**
 * Felsoroló, amely a BlackJack-ben használt alapvető játékkártyák rangjait reprezentálja.
 * Tartalmazza a rangok nevét, szimbólumát és értékét, amelyeket a BlackJack-ben használnak.
 */
public enum Rank {

    ACE("A", 1), KING("K", 10), QUEEN("Q", 10), JACK("J", 10), TEN("10", 10), NINE("9", 9), EIGHT("8", 8), SEVEN("7", 7), SIX("6", 6), FIVE("5", 5), FOUR("4", 4), THREE("3", 3), TWO("2", 2);

    public final String symbol; // A rang szimbóluma (pl. A, K, Q, J, 10, stb.)
    public final int value; // A rang értéke (pl. 1, 10, 9, stb.)

    /**
     * Létrehoz egy rangot a megadott szimbólummal és értékkel.
     *
     * @param sym   A rang szimbóluma
     * @param value A rang értéke
     */
    Rank(String sym, int value) {
        symbol = sym;
        this.value = value;
    }
}