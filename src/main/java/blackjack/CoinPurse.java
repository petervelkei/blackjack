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
        this.currentBet = 50;
    }


    /**
     * Fogadást tesz a kiválasztott összegre, és visszaadja a fogadott összeget.
     * Ha nincs elég pénz a CoinPurse objektumban, nullát ad vissza.
     *
     * @return A fogadott összeg
     */
    public int bet() {
        if (balance - currentBet >= 0) {
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
     * Beállítja a CoinPurse jelenlegi vagyonát a megadott összegre.
     * Ha kevesebb, mint 10, megtartja a jelenlegi összeget.
     *
     * @param amount A beállítandó vagyon összege
     */
    public void updateBalance(int amount) {
        if (amount >= 10) {
            balance = amount;
        }
    }
    
    /**
     * Beállítja a CoinPurse jelenlegi tétméretét a megadott összegre.
     * Ha kevesebb, mint 10, megtartja a jelenlegi összeget.
     *
     * @param amount A beállítandó tétméret
     */
    private void updateBet(int amount) {
        if (amount >= 10) {
            currentBet = amount;
        } else {
            currentBet = 0;
        }
    }

    /**
     * Kezeli a JSlider állapotváltozását, és beállítja a tétméretet a csúszka értékére.
     *
     * @param event Az állapotváltozás eseménye
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        JSlider slider = (JSlider) event.getSource();
        if (!slider.getValueIsAdjusting()) {
            updateBet(slider.getValue());
        }
    }
}