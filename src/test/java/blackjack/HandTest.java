package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    /**
     * Beállítja a teszt környezetet minden teszt előtt.
     * Inicializál egy új Hand objektumot a "player" tulajdonossal.
     */
    @BeforeEach
    void setUp() {
        hand = new Hand("player");
    }

    /**
     * Teszteli a kártya hozzáadását a kézhez.
     * Ellenőrzi, hogy a kártya hozzáadása után a kéz mérete 1.
     */
    @Test
    void testAddCard() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        hand.addCard(card);
        assertEquals(1, hand.getSize());
    }

    /**
     * Teszteli a kéz értékének lekérdezését.
     * Ellenőrzi, hogy két kártya hozzáadása után a kéz értéke 11.
     */
    @Test
    void testGetValue() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        assertEquals(11, hand.getValue());
    }

    /**
     * Teszteli a kéz ász értékének lekérdezését.
     * Ellenőrzi, hogy két kártya hozzáadása után a kéz ász értéke 21.
     */
    @Test
    void testGetAcedValue() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        assertEquals(21, hand.getAcedValue());
    }

    /**
     * Teszteli, hogy a kéz blackjack-e.
     * Ellenőrzi, hogy két kártya hozzáadása után a kéz blackjack.
     */
    @Test
    void testBlackJack() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        assertTrue(hand.blackJack());
    }

    /**
     * Teszteli, hogy a kéz osztható-e.
     * Ellenőrzi, hogy két azonos rangú kártya hozzáadása után a kéz osztható.
     */
    @Test
    void testIsSplittable() {
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        assertTrue(hand.isSplittable());
    }

    /**
     * Teszteli a kéz osztását.
     * Ellenőrzi, hogy a kéz osztása után mindkét kéz mérete 1.
     */
    @Test
    void testSplit() {
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        Hand splitHand = hand.split();
        assertNotNull(splitHand);
        assertEquals(1, hand.getSize());
        assertEquals(1, splitHand.getSize());
    }

    /**
     * Teszteli a tét hozzáadását a kézhez.
     * Ellenőrzi, hogy a tét hozzáadása után a kéz tétje 100.
     */
    @Test
    void testAddBet() {
        hand.addBet(100);
        assertEquals(100, hand.getBet());
    }

    /**
     * Teszteli a kéz tulajdonosának lekérdezését.
     * Ellenőrzi, hogy a kéz tulajdonosa "player".
     */
    @Test
    void testGetOwner() {
        assertEquals("player", hand.getOwner());
    }

    /**
     * Teszteli, hogy a kéz tartalmaz-e ászt.
     * Ellenőrzi, hogy egy ász hozzáadása után a kéz tartalmaz ászt.
     */
    @Test
    void testHasAce() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        assertTrue(hand.hasAce());
    }

    /**
     * Teszteli a kártya lekérdezését a kézből.
     * Ellenőrzi, hogy a hozzáadott kártya lekérdezése helyes.
     */
    @Test
    void testGetCard() {
        Card card = new Card(Rank.ACE, Suit.CLUBS, null);
        hand.addCard(card);
        assertEquals(card, hand.getCard(0));
    }

    /**
     * Teszteli a kéz méretének lekérdezését.
     * Ellenőrzi, hogy két kártya hozzáadása után a kéz mérete 2.
     */
    @Test
    void testGetSize() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        assertEquals(2, hand.getSize());
    }

    /**
     * Teszteli a kéz tétjének lekérdezését.
     * Ellenőrzi, hogy a tét hozzáadása után a kéz tétje 50.
     */
    @Test
    void testGetBet() {
        hand.addBet(50);
        assertEquals(50, hand.getBet());
    }
}