package blackjack;

import java.awt.Image;

/**
 * Egy osztály, amely egy játékkártyát reprezentál ranggal, színnel és képpel.
 */
public class Card {
    private final Rank rank; // A kártya rangja (pl. Ász, 2, 3, ..., Király)
    private final Suit suit; // A kártya színe (pl. Treff, Káró, Kőr, Pikk)
    private final Image image; // A kártyát ábrázoló kép

    /**
     * Létrehoz egy kártyát a megadott ranggal, színnel és képpel.
     *
     * @param r   A kártya rangja
     * @param s   A kártya színe
     * @param img A kártya képe
     */
    public Card(Rank r, Suit s, Image img) {
        this.rank = r;
        this.suit = s;
        this.image = img;
    }

    /**
     * Visszaadja a kártya színét sztringként.
     *
     * @return A kártya színe
     */
    public String getSuit() {
        return suit.name();
    }

    /**
     * Visszaadja a kártya rangját egész számként.
     *
     * @return A kártya rangja
     */
    public int getRank() {
        return rank.value;
    }

    /**
     * Visszaadja a kártya képét.
     *
     * @return A kártya képe
     */
    public Image getImage() {
        return image;
    }

    /**
     * Visszaadja a kártya sztring reprezentációját a "rank_of_suit.png" formátumban.
     *
     * @return A kártya sztring reprezentációja
     */
    @Override
    public String toString() {
        return rank + "_of_" + suit + ".png";
    }
}