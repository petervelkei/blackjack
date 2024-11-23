package blackjack;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 
 * Ez az osztály a kártyapaklit reprezentálja, amely keverhető és amelyből kártyákat lehet húzni.
 */
public class Deck {

    private List<Card> cards = new LinkedList<>();
    private List<Card> shuffle = new LinkedList<>();
    
    /**
     * 
     * Létrehoz egy új Deck objektumot, amely tárolja az 52 különböző kártyát sorrendben.
     */
    public Deck() {
        Rank[] ranks = Rank.values();
        Suit[] suits = Suit.values();
        List<String> cardNames = new LinkedList<>();
    
        // Összegyűjtjük az összes kártya nevét
        for (Rank rank : ranks) {
            for (Suit suit : suits) {
                cardNames.add(rank.name().toLowerCase() + "_of_" + suit.name().toLowerCase() + ".png");
            }
        }
    
        // Betöltjük a képeket és létrehozzuk a kártya objektumokat
        for (String cardName : cardNames) {
            try {
                String fileName = "src/resources/cards/" + cardName;
                File file = new File(fileName);
                if (!file.exists()) {
                    throw new IOException("File not found: " + fileName);
                }
                Image t = ImageIO.read(file);
                String[] parts = cardName.split("_of_");
                Rank rank = Rank.valueOf(parts[0].toUpperCase());
                Suit suit = Suit.valueOf(parts[1].replace(".png", "").toUpperCase());
                cards.add(new Card(rank, suit, t));
            } catch (IOException e) {
                System.err.println("Missed files: " + e.getMessage());
                System.exit(154);
            }
        }
    }

    /**
     * 
     * Visszaadja a pakli első kártyáját.
     * Ha a pakli üres (minden kártyát kihúztak vagy a Deck objektumot éppen most hozták létre),
     * a újra feltöltjük.
     * 
     */
    public Card draw() {
        if (!shuffle.isEmpty()) {
            return shuffle.remove(0);
        }
        shuffle = new ArrayList<>(cards);
        Collections.shuffle(shuffle);
        return shuffle.remove(0);
    }
}