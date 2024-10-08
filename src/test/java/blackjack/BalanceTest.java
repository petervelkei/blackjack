package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A BalanceTest osztály, amely a Balance osztály teszteléséért felelős.
 * 
 * Ez az osztály tartalmazza a Balance osztály különböző metódusainak tesztjeit,
 * beleértve az egyenleg inicializálását, fogadások hozzáadását, nyeremények és
 * veszteségek kezelését, valamint a fogadások visszaállítását.
 */
public class BalanceTest {
    private Balance balance;

    /**
     * Inicializálja a tesztkörnyezetet minden teszt előtt.
     * 
     * Ez a metódus létrehoz egy új Balance objektumot 1000 kezdeti egyenleggel.
     */
    @BeforeEach
    public void setUp() {
        balance = new Balance(1000); // Kezdeti egyenleg 1000
    }

    /**
     * Teszteli a Balance objektum kezdeti egyenlegét és jelenlegi fogadását.
     * 
     * Ez a metódus ellenőrzi, hogy a kezdeti egyenleg 1000, és a jelenlegi fogadás 0.
     */
    @Test
    public void testInitialBalance() {
        assertEquals(1000, balance.getBalance());
        assertEquals(0, balance.getCurrentBet());
    }

    /**
     * Teszteli a fogadás hozzáadását a Balance objektumhoz.
     * 
     * Ez a metódus hozzáad egy 100-as fogadást, majd ellenőrzi, hogy az egyenleg 900-ra,
     * a jelenlegi fogadás pedig 100-ra változott.
     */
    @Test
    public void testAddBet() {
        balance.addBet(100);
        assertEquals(900, balance.getBalance());
        assertEquals(100, balance.getCurrentBet());
    }

    /**
     * Teszteli a fogadás hozzáadását, ha az egyenleg nem elegendő.
     * 
     * Ez a metódus megpróbál hozzáadni egy 1100-as fogadást, ami meghaladja az egyenleget,
     * és ellenőrzi, hogy IllegalArgumentException kivétel keletkezik a megfelelő üzenettel.
     */
    @Test
    public void testAddBetNotEnoughBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            balance.addBet(1100);
        });
        assertEquals("Not enough balance!", exception.getMessage());
    }

    /**
     * Teszteli a fogadás megduplázását nyeremény esetén.
     * 
     * Ez a metódus hozzáad egy 100-as fogadást, majd megduplázza a fogadást nyeremény esetén,
     * és ellenőrzi, hogy a jelenlegi fogadás 200-ra változott.
     */
    @Test
    public void testWinBet() {
        balance.addBet(100);
        balance.winBet();
        assertEquals(200, balance.getCurrentBet());
    }

    /**
     * Teszteli a fogadás levonását veszteség esetén.
     * 
     * Ez a metódus hozzáad egy 100-as fogadást, majd levonja a fogadást veszteség esetén,
     * és ellenőrzi, hogy az egyenleg 800-ra, a jelenlegi fogadás pedig 100-ra változott.
     */
    @Test
    public void testLoseBet() {
        balance.addBet(100);
        balance.loseBet();
        assertEquals(800, balance.getBalance());
        assertEquals(100, balance.getCurrentBet());
    }

    /**
     * Teszteli a fogadás visszaállítását.
     * 
     * Ez a metódus hozzáad egy 100-as fogadást, majd visszaállítja a fogadást,
     * és ellenőrzi, hogy az egyenleg 1000-re, a jelenlegi fogadás pedig 0-ra változott.
     */
    @Test
    public void testResetBet() {
        balance.addBet(100);
        balance.resetBet();
        assertEquals(1000, balance.getBalance());
        assertEquals(0, balance.getCurrentBet());
    }
}