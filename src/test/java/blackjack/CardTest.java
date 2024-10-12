package blackjack;

import java.awt.Image;

import javax.swing.ImageIcon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CardTest {

    /**
     * Teszteli a getSuit metódust.
     * Ellenőrzi, hogy a kártya színe helyesen van-e beállítva.
     */
    @Test
    void testGetSuit() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        assertEquals("CLUBS", card.getSuit());
    }

    /**
     * Teszteli a getRank metódust.
     * Ellenőrzi, hogy a kártya rangja helyesen van-e beállítva.
     */
    @Test
    void testGetRank() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        assertEquals(1, card.getRank());
    }

    /**
     * Teszteli a getImage metódust.
     * Ellenőrzi, hogy a kártya képe helyesen van-e beállítva.
     */
    @Test
    void testGetImage() {
        Image image = new ImageIcon("src/cards/ace_of_clubs.png").getImage();
        Card card = new Card(Rank.ACE, Suit.CLUBS, image);
        assertEquals(image, card.getImage());
    }

    /**
     * Teszteli a toString metódust.
     * Ellenőrzi, hogy a kártya szöveges reprezentációja helyes-e.
     */
    @Test
    void testToString() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        assertEquals("ACE_of_CLUBS.png", card.toString());
    }

    /**
     * Teszteli a kártyák egyenlőségét.
     * Ellenőrzi, hogy két azonos kártya egyenlő-e.
     */
    @Test
    void testCardEquality() {
        Card card1 = new Card(Rank.ACE, Suit.CLUBS, null);
        Card card2 = new Card(Rank.ACE, Suit.CLUBS, null);
        assertEquals(card1.toString(), card2.toString());
    }

    /**
     * Teszteli a kártyák különbözőségét.
     * Ellenőrzi, hogy két különböző kártya nem egyenlő-e.
     */
    @Test
    void testCardInequality() {
        Card card1 = new Card(Rank.ACE, Suit.CLUBS, null);
        Card card2 = new Card(Rank.KING, Suit.CLUBS, null);
        assertNotEquals(card1.toString(), card2.toString());
    }

    /**
     * Teszteli a kártyát képpel.
     * Ellenőrzi, hogy a kártya képe nem null-e.
     */
    @Test
    void testCardWithImage() {
        Image image = new ImageIcon("src/cards/ace_of_clubs.png").getImage();
        Card card = new Card(Rank.ACE, Suit.CLUBS, image);
        assertNotNull(card.getImage());
    }

    /**
     * Teszteli a kártyát kép nélkül.
     * Ellenőrzi, hogy a kártya képe null-e.
     */
    @Test
    void testCardWithoutImage() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        assertNull(card.getImage());
    }

    /**
     * Teszteli a kártya rangértékét.
     * Ellenőrzi, hogy a kártya rangértéke helyes-e.
     */
    @Test
    void testCardRankValue() {
        Card card = new Card(Rank.KING, Suit.CLUBS, null);
        assertEquals(10, card.getRank());
    }

    /**
     * Teszteli a kártya színértékét.
     * Ellenőrzi, hogy a kártya színértéke helyes-e.
     */
    @Test
    void testCardSuitValue() {
        Card card = new Card(Rank.ACE, Suit.HEARTS, null);
        assertEquals("HEARTS", card.getSuit());
    }
}