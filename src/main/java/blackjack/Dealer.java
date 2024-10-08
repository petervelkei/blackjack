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
     * Ellenőrzi, hogy az osztó keze megegyezik-e egy másik játékos kezével.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk az osztó kezét.
     * @return true, ha az osztó keze megegyezik a másik játékos kezével, különben false.
     */
    @Override
    public boolean isPush(Player other) {
        return getHandValue() == other.getHandValue();
    }

    /**
     * Ellenőrzi, hogy az osztó keze jobb-e, mint egy másik játékos keze.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk az osztó kezét.
     * @return true, ha az osztó keze jobb, mint a másik játékos keze és az osztó nem lépte túl a 21-et, különben false.
     */
    @Override
    public boolean hasBetterHandThan(Player other) {
        return getHandValue() > other.getHandValue() && !isBusted();
    }

    /**
     * Ellenőrzi, hogy az osztó keze rosszabb-e, mint egy másik játékos keze.
     * 
     * @param other A másik játékos, akivel összehasonlítjuk az osztó kezét.
     * @return true, ha az osztó keze rosszabb, mint a másik játékos keze, különben false.
     */
    @Override
    public boolean hasLesserHandThan(Player other) {
        return getHandValue() < other.getHandValue();
    }

    /**
     * Törli az osztó kezében lévő kártyákat.
     */
    @Override
    public void clearHand() {
        getHand().clear();
    }

    /**
     * Hozzáad egy kártyát az osztó kezéhez.
     * 
     * @param card A hozzáadni kívánt kártya.
     */
    @Override
    public void addCard(Card card) {
        getHand().add(card);
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