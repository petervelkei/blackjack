package blackjack;
/**
 * A Main osztály, amely a Blackjack játék indításáért felelős.
 */
public class Main {
    /**
     * Létrehoz egy új Dealer objektumot, hogy elindítsa a Blackjack játékot.
     */
    public static void main(String[] args) {
        Dealer g = new Dealer();
        g.jatekIndit();
    }
}