package blackjack;

import java.awt.Image;

/**
 * Egy osztály, amely egy játékkártyát reprezentál ranggal, színnel és képpel.
 */
public class Card {
    private final Rank r; // A kártya rangja (pl. Ász, 2, 3, ..., Király)
    private final Suit s; // A kártya színe (pl. Treff, Káró, Kőr, Pikk)
    private final Image i; // A kártyát ábrázoló kép

    /**
     * Létrehoz egy kártyát a megadott ranggal, színnel és képpel.
     *
     * @param r   A kártya rangja
     * @param s   A kártya színe
     * @param img A kártya képe
     */
    public Card(Rank r, Suit s, Image i) {
        this.r = r;
        this.s = s;
        this.i = i;
    }

    /**
     * Visszaadja a kártya színét sztringként.
     *
     * @return A kártya színe
     */
    public String getSuit() {
        return s.name();
    }

    /**
     * Visszaadja a kártya rangját egész számként.
     *
     * @return A kártya rangja
     */
    public int getRank() {
        return r.v;
    }

    /**
     * Visszaadja a kártya képét.
     *
     * @return A kártya képe
     */
    public Image getKep() {
        return i;
    }

    /**
     * Visszaadja a kártya sztring reprezentációját a "rank_of_suit.png" formátumban.
     *
     * @return A kártya sztring reprezentációja
     */
    @Override
    public String toString() {
        return r + "_of_" + s + ".png";
    }
}