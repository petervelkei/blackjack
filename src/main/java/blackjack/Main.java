package blackjack;
/**
 * A Main osztály, amely a Blackjack játék indításáért felelős.
 * 
 * Ez az osztály tartalmazza a main metódust, amely a program belépési pontja.
 * A main metódus létrehoz egy új Dealer objektumot, és elindítja a játékot.
 */
public class Main {
    /**
     * A program belépési pontja.
     * 
     * Ez a metódus létrehoz egy új Dealer objektumot, hogy elindítsa a Blackjack játékot.
     * 
     * @param args A parancssori argumentumok tömbje.
     */
    public static void main(String[] args) {
        Dealer game = new Dealer();
    }
}