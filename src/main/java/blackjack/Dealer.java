package blackjack;

/**
 * A Dealer osztály, amely a játék osztóját reprezentálja.
 * 
 * A Dealer osztály a Player osztályból származik, és tartalmazza az osztó specifikus
 * logikáját, mint például a kártyák húzásának szabályait és a rejtett kártya kezelését.
 */
public class Dealer extends Player {
    private boolean hasHiddenCard = false;

    /**
     * Meghatározza, hogy az osztónak kell-e még kártyát húznia.
     * 
     * @return true, ha az osztó kezének értéke kisebb, mint 17, különben false.
     */
    public boolean mustDrawCard() {
        return getHandValue() < 17;
    }


    /**
     * Ellenőrzi, hogy az osztó megállt-e (keze értéke legalább 17).
     * 
     * @return true, ha az osztó keze legalább 17, különben false.
     */
    public boolean isStanding() {
        return getHandValue() >= 17;
    }
    

    /**
     * Hozzáad egy kártyát az osztó kezéhez, és beállítja, hogy a kártya rejtett-e.
     * 
     * @param card A hozzáadni kívánt kártya.
     * @param hidden Ha true, a kártya rejtett lesz.
     */
    public void addCard(Card card, boolean hidden) {
        if (hidden) {
            hasHiddenCard = true;
        }
        addCard(card);
    }

    /**
     * Felfedi az osztó rejtett kártyáját.
     */
    public void revealHiddenCard() {
        hasHiddenCard = false;
    }

    /**
     * Ellenőrzi, hogy az osztónak van-e rejtett kártyája.
     * 
     * @return true, ha az osztónak van rejtett kártyája, különben false.
     */
    public boolean hasHiddenCard() {
        return hasHiddenCard;
    }
}