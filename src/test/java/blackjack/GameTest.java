package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A GameTest osztály, amely a Game osztály teszteléséért felelős.
 * 
 * Ez az osztály tartalmazza a Game osztály különböző metódusainak tesztjeit,
 * beleértve a kezdeti kártyák kiosztását, az osztó kártyahúzását és a játék újraindítását.
 */
public class GameTest {
    private Game game;

    /**
     * Inicializálja a tesztkörnyezetet minden teszt előtt.
     * 
     * Ez a metódus létrehoz egy új Game objektumot.
     */
    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /**
     * Teszteli a kezdeti kártyák kiosztását a játékosnak és az osztónak.
     * 
     * Ez a metódus meghívja a dealInitialCards metódust, majd ellenőrzi, hogy
     * mind a játékos, mind az osztó két-két kártyát kapott.
     */
    @Test
    public void testInitialDeal() {
        game.dealInitialCards();
        assertEquals(2, game.getPlayer().getHand().size());
        assertEquals(2, game.getDealer().getHand().size());
    }

    /**
     * Teszteli az osztó kártyahúzását.
     * 
     * Ez a metódus meghívja a dealInitialCards metódust, majd az osztó húz egy kártyát,
     * és ellenőrzi, hogy az osztó kezében lévő kártyák száma háromra nőtt.
     */
    @Test
    public void testDealerDrawCard() {
        game.dealInitialCards();
        game.dealerDrawCard();
        assertEquals(3, game.getDealer().getHand().size());
    }

    /**
     * Teszteli a játék újraindítását.
     * 
     * Ez a metódus meghívja a dealInitialCards metódust, majd az újraindítja a játékot,
     * és ellenőrzi, hogy mind a játékos, mind az osztó két-két kártyát kapott az újraindítás után.
     */
    @Test
    public void testRestart() {
        game.dealInitialCards();
        game.restart();
        assertEquals(2, game.getPlayer().getHand().size());
        assertEquals(2, game.getDealer().getHand().size());
    }
}