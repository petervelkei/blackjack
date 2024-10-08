package blackjack;

/**
 * A Card osztály, amely egy kártyát reprezentál a Blackjack játékban.
 * 
 * Ez az osztály tartalmazza a kártya színét, rangját és a kép elérési útját.
 * Biztosítja a kártya attribútumainak lekérdezésére szolgáló metódusokat.
 */
public class Card {
    private final String suit;
    private final String rank;
    private final String imagePath;

    /**
     * Létrehoz egy új Card objektumot a megadott színnel, ranggal és kép elérési úttal.
     * 
     * @param suit A kártya színe (pl. hearts, diamonds, clubs, spades).
     * @param rank A kártya rangja (pl. 2, 3, 4, ..., 10, jack, queen, king, ace).
     * @param imagePath A kártya képének elérési útja.
     */
    public Card(String suit, String rank, String imagePath) {
        this.suit = suit;
        this.rank = rank;
        this.imagePath = imagePath;
    }

    /**
     * Visszaadja a kártya színét.
     * 
     * @return A kártya színe.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Visszaadja a kártya rangját.
     * 
     * @return A kártya rangja.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Visszaadja a kártya képének elérési útját.
     * 
     * @return A kártya képének elérési útja.
     */
    public String getImagePath() {
        return imagePath;
    }
}