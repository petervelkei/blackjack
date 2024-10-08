package blackjack;

/**
 * A játékos egyenlegét és fogadásait kezelő osztály.
 * 
 * Ez az osztály kezeli a játékos teljes egyenlegét és a jelenlegi fogadását.
 * Lehetővé teszi a fogadások hozzáadását, a nyeremények és veszteségek kezelését,
 * valamint a fogadások visszaállítását.
 */
public class Balance {
    private int totalBalance;
    private int currentBet;

    /**
     * Létrehoz egy új Balance objektumot a megadott kezdeti egyenleggel.
     * 
     * @param initialBalance A játékos kezdeti egyenlege.
     */
    public Balance(int initialBalance) {
        this.totalBalance = initialBalance;
        this.currentBet = 0;
    }

    /**
     * Visszaadja a játékos teljes egyenlegét.
     * 
     * @return A játékos teljes egyenlege.
     */
    public int getBalance() {
        return totalBalance;
    }

    /**
     * Visszaadja a játékos jelenlegi fogadásának összegét.
     * 
     * @return A játékos jelenlegi fogadásának összege.
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Hozzáad egy fogadást a játékos jelenlegi fogadásához.
     * 
     * Ha a játékos teljes egyenlege nagyobb vagy egyenlő a megadott összeggel,
     * akkor a megadott összeget levonja a teljes egyenlegből és hozzáadja a jelenlegi fogadáshoz.
     * Ellenkező esetben IllegalArgumentException kivételt dob.
     * 
     * @param amount A hozzáadni kívánt fogadás összege.
     * @throws IllegalArgumentException ha a játékos teljes egyenlege kisebb, mint a megadott összeg.
     */
    public void addBet(int amount) {
        if (totalBalance >= amount) {
            totalBalance -= amount;
            currentBet += amount;
        } else {
            throw new IllegalArgumentException("Not enough balance!");
        }
    }

    /**
     * Megduplázza a játékos jelenlegi fogadását nyeremény esetén.
     * 
     * A jelenlegi fogadás összegét megduplázza, jelezve, hogy a játékos nyert.
     */
    public void winBet() {
        currentBet *= 2;
    }

    /**
     * Levonja a játékos jelenlegi fogadását a teljes egyenlegből veszteség esetén.
     * 
     * A jelenlegi fogadás összegét levonja a teljes egyenlegből, jelezve, hogy a játékos veszített.
     */
    public void loseBet() {
        totalBalance -= currentBet;
    }

    /**
     * Visszaállítja a játékos fogadását és hozzáadja a jelenlegi fogadás összegét a teljes egyenleghez.
     * 
     * A jelenlegi fogadás összegét hozzáadja a teljes egyenleghez, majd a jelenlegi fogadást nullára állítja.
     */
    public void resetBet() {
        totalBalance += currentBet;
        currentBet = 0;
    }
}