package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A PlayerTest osztály, amely a Player osztály teszteléséért felelős.
 * 
 * Ez az osztály tartalmazza a Player osztály különböző metódusainak tesztjeit,
 * beleértve a kezdeti kéz ellenőrzését, kártyák hozzáadását, a játékos
 * túllépésének és blackjack-jének ellenőrzését, valamint a kéz visszaállítását.
 */
public class PlayerTest {
    private Player player;

    /**
     * Inicializálja a tesztkörnyezetet minden teszt előtt.
     * 
     * Ez a metódus létrehoz egy új Player objektumot.
     */
    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    /**
     * Teszteli a játékos kezdeti kezét.
     * 
     * Ez a metódus ellenőrzi, hogy a játékos kezdeti keze üres.
     */
    @Test
    public void testInitialHand() {
        assertTrue(player.getHand().isEmpty());
    }

    /**
     * Teszteli a kártya hozzáadását a játékos kezéhez.
     * 
     * Ez a metódus hozzáad egy kártyát a játékos kezéhez, majd ellenőrzi,
     * hogy a kéz mérete 1-re nőtt, és a hozzáadott kártya megegyezik a várt kártyával.
     */
    @Test
    public void testAddCard() {
        Card card = new Card("hearts", "10", "10");
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals(card, player.getHand().get(0));
    }

    /**
     * Teszteli, hogy a játékos keze túllépte-e a 21-et.
     * 
     * Ez a metódus hozzáad három kártyát a játékos kezéhez, majd ellenőrzi,
     * hogy a játékos keze túllépte-e a 21-et.
     */
    @Test
    public void testIsBusted() {
        player.addCard(new Card("hearts", "10", "10"));
        player.addCard(new Card("spades", "10", "10"));
        player.addCard(new Card("clubs", "2", "2"));
        assertTrue(player.isBusted());
    }

    /**
     * Teszteli, hogy a játékosnak blackjack-je van-e.
     * 
     * Ez a metódus hozzáad két kártyát a játékos kezéhez, majd ellenőrzi,
     * hogy a játékosnak blackjack-je van-e.
     */
    @Test
    public void testHasBlackjack() {
        player.addCard(new Card("hearts", "ace", "ace"));
        player.addCard(new Card("spades", "10", "10"));
        assertTrue(player.hasBlackjack());
    }

    /**
     * Teszteli a játékos kezének visszaállítását.
     * 
     * Ez a metódus hozzáad egy kártyát a játékos kezéhez, majd visszaállítja a kezet,
     * és ellenőrzi, hogy a játékos keze üres.
     */
    @Test
    public void testReset() {
        player.addCard(new Card("hearts", "10", "10"));
        player.reset();
        assertTrue(player.getHand().isEmpty());
    }
}