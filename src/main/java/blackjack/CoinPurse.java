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
     * Ellenőrzi, hogy a CoinPurse objektumnak van-e elég pénze a játékos által kiválasztott összeg fogadásához.
     *
     * @return true, ha van elég pénz, különben false
     */
    public boolean betAcceptable() {
        return balance - currentBet >= 0;
    }

    /**
     * Fogadást tesz a kiválasztott összegre, és visszaadja a fogadott összeget.
     * Ha nincs elég pénz a CoinPurse objektumban, nullát ad vissza.
     *
     * @return A fogadott összeg
     */
    public int bet() {
        if (!betAcceptable()) {
            return 0;
        }
        balance -= currentBet;
        return currentBet;
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
     * Visszaadja a CoinPurse aktuális tétjét.
     *
     * @return Az aktuális tét
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Hozzáadja a megadott pénzösszeget a CoinPurse objektumhoz.
     * Ha negatív összeget adnak meg, nem ad hozzá semmit.
     *
     * @param m A hozzáadandó pénzösszeg
     */
    public void addMoney(int m) {
        balance += m > 0 ? m : 0;
    }

    /**
     * Beállítja a CoinPurse jelenlegi vagyonát a megadott összegre.
     * Ha kevesebb, mint 10, megtartja a jelenlegi összeget.
     *
     * @param m A beállítandó vagyon összege
     */
    public void setWealth(int m) {
        balance = m >= 10 ? m : balance;
    }

    /**
     * Beállítja a CoinPurse jelenlegi tétméretét a megadott összegre.
     * Ha kevesebb, mint 10, megtartja a jelenlegi összeget.
     *
     * @param m A beállítandó tétméret
     */
    private void setBetSize(int m) {
        currentBet = m >= 10 ? m : 0;
    }

    /**
     * Kezeli a JSlider állapotváltozását, és beállítja a tétméretet a csúszka értékére.
     *
     * @param e Az állapotváltozás eseménye
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            setBetSize(source.getValue());
        }
    }
}