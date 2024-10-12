package blackjack;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

/**
 * 
 * Ez az osztály a kártyapaklit reprezentálja, amely keverhető és amelyből kártyákat lehet húzni.
 */
public class Deck {

    private ArrayList<Card> cardStorage = new ArrayList<>();
    private ArrayList<Card> shuffledDeck = new ArrayList<>();
    
    /**
     * 
     * Létrehoz egy új Deck objektumot, amely tárolja az 52 különböző kártyát sorrendben.
     */
    public Deck() {
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                try {
                    String fileName = "src/resources/cards/" + r.name().toLowerCase() + "_of_" + s.name().toLowerCase() + ".png";
                    File file = new File(fileName);
                    if (!file.exists()) {
                        throw new IOException("File not found: " + fileName);
                    }
                    Image temp = ImageIO.read(file);
                    cardStorage.add(new Card(r, s, temp));
                } catch (IOException ex) {
                    System.out.println("You seem to miss the card files: " + ex.getMessage());
                    System.exit(154);
                }
            }
        }
    }

    /**
     * 
     * A kártyatárolót használja egy új, 52 kártyából álló pakli megkeveréséhez.
     */
    public void shuffle() {
        shuffledDeck = new ArrayList<>(cardStorage);
        Collections.shuffle(shuffledDeck);
    }

    /**
     * 
     * Visszaadja a pakli első kártyáját.
     * Ha a pakli üres (minden kártyát kihúztak vagy a Deck objektumot éppen most hozták létre),
     * a shuffle() metódust használja a feltöltéshez.
     * 
     * @return first Card of the deck
     */
    public Card drawCard() {
        if (shuffledDeck.isEmpty()) {
            shuffle();
        }
        return shuffledDeck.remove(0);
    }
}