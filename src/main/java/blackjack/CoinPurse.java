package blackjack;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A CoinPurse osztály kezeli a játékos pénzét és fogadásait a blackjack játékban.
 */
public class CoinPurse implements ChangeListener {
    private int balance; // A játékos aktuális egyenlege
    private int currentBet; // A játékos aktuális tétje

    /**
     * Konstruktor, amely inicializálja a CoinPurse objektumot a megadott kezdő egyenleggel.
     * Ha a kezdő egyenleg kisebb vagy egyenlő nullával, akkor az alapértelmezett egyenleg 2000 lesz.
     *
     * @param initialBalance A kezdő egyenleg
     */
    public CoinPurse(int initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 2000;
    }

    public boolean helyesTet() {
        return balance - currentBet >= 0;
    }


    /**
     * Fogadást tesz a kiválasztott összegre, és visszaadja a fogadott összeget.
     * Ha nincs elég pénz a CoinPurse objektumban, nullát ad vissza.
     *
     * @return A fogadott összeg
     */
    public int bet() {
        if (helyesTet()) {
            balance -= currentBet;
            return currentBet;
        }
        return 0;
    }

    /**
     * Visszaadja a CoinPurse aktuális egyenlegét.
     *
     * @return Az aktuális egyenleg
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Hozzáadja a megadott pénzösszeget a CoinPurse objektumhoz.
     * Ha negatív összeget adnak meg, nem ad hozzá semmit.
     *
     * @param amount A hozzáadandó pénzösszeg
     */
    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    /**
     * Kivonja a megadott pénzösszeget a CoinPurse objektumból.
     * Ha a megadott összeg nagyobb, mint a játékos egyenlege, nem von le semmit.
     *
     * @param amount A kivonandó pénzösszeg
     */
    private void updateBet(int m) {
        currentBet = m >= 100 ? m : 0;
    }

    /**
     * Visszaadja a játékos aktuális tétjét.
     *
     * @return A játékos aktuális tétje
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        JSlider slider = (JSlider) event.getSource();
        if (!slider.getValueIsAdjusting()) {
            updateBet(slider.getValue());
        }
    }
}