package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Dealer osztály, amely a játék logikáját és a kártyák osztását kezeli.
 */
public class Dealer implements ActionListener {

    private Deck asztal; // A pakli kártyák
    private GameUI screen; // A játék felhasználói felülete
    private Hand jatekosKez; // A játékos keze
    private Hand osztoKez; // Az osztó keze
    private Hand jatekosOsztottKez; // A megosztott kéz
    private CoinPurse jatekosEgyenleg; // A játékos pénztárcája
    private boolean osztottKez; // Jelzi, hogy a játékos a megosztott kézzel játszik-e

    public void play() {
        asztal = new Deck();
        screen = new GameUI(this);
        jatekosEgyenleg = new CoinPurse(2000);
        GameUI.csuszka.addChangeListener(jatekosEgyenleg);
    }

    /**
     * Frissíti a kéz értékét a felhasználói felületen.
     *
     * @param hand A frissítendő kéz
     */
    private void updateHandValue(Hand hand) {
        String update = hand.getErtek() + "";
        if (hand.VanAsza() && hand.getaszErtek() > 0) {
            update += "/" + hand.getaszErtek();
        }
        if (hand.blackJack()) {
            update = "BLACKJACK!";
        }
        screen.updateKezErtek(hand.getFelhasznalo(), update);
    }

    /**
     * Kioszt egy kártyát a megadott kézhez.
     *
     * @param target A cél kéz, amelyhez a kártyát ki kell osztani
     */
    private void dealCard(Hand target) {
        Card temp = asztal.draw();
        screen.kartyaHuzasUI(temp, target.getFelhasznalo());
        target.addCard(temp);
    }

    /**
     * Ellenőrzi, hogy a kéz értéke meghaladja-e a 21-et (bust).
     *
     * @param hand Az ellenőrizendő kéz
     * @return true, ha a kéz értéke meghaladja a 21-et, különben false
     */
    private boolean checkBust(Hand hand) {
        if (hand.getErtek() <= 21) {
            return false;
        }
        if (gameoverBust(hand)) {
            screen.gombokatEngedelyez("bust");
        }
        return true;
    }

    /**
     * Kezeli a játék végét, ha a kéz értéke meghaladja a 21-et.
     *
     * @param hand Az ellenőrizendő kéz
     * @return true, ha a játék véget ért, különben false
     */
    public boolean gameoverBust(Hand hand) {
        boolean gameOver = true;
        switch (hand.getFelhasznalo()) {
            case GameUI.SPLIT -> {
                setEnablejatekosOsztottKez(false);
                gameOver = false;
            }
            case GameUI.DEALER -> declareWinner();
            case GameUI.PLAYER -> {
                if (jatekosOsztottKez != null && jatekosOsztottKez.getErtek() <= 21) {
                    GameUI.stay.doClick();
                    gameOver = false;
                    break;
                }
                screen.vege("PLAYER BUSTS");
            }
            default -> throw new IllegalStateException("Unexpected value: " + hand.getFelhasznalo());
        }
        return gameOver;
    }

    /**
     * Engedélyezi vagy letiltja a megosztott kéz használatát.
     *
     * @param enable true, ha engedélyezni kell a megosztott kéz használatát, különben false
     */
    private void setEnablejatekosOsztottKez(boolean enable) {
        osztottKez = enable;
        if (enable) {
            screen.kezEngedelyez(GameUI.SPLIT);
            screen.kezTiltas(GameUI.PLAYER);
        } else {
            screen.kezEngedelyez(GameUI.PLAYER);
            screen.kezTiltas(GameUI.SPLIT);
        }
    }

    /**
     * Megduplázza a tétet és kioszt egy kártyát a megadott kézhez.
     *
     * @param hand A megduplázandó kéz
     */
    public void doubleHand(Hand hand) {
        hand.placeBet(jatekosEgyenleg.bet());
        dealCard(hand);
        checkBust(hand);
        updateHandValue(hand);
        GameUI.stay.doClick();
    }

    /**
     * Kifizeti a nyereményt a játékosnak.
     *
     * @param amount A kifizetendő összeg
     */
    private void payWinnings(int amount) {
        jatekosEgyenleg.deposit(amount);
        screen.tetFrissites(jatekosEgyenleg.getBalance(), jatekosKez.getFogadas());
    }

    /**
     * Kifizeti a nyereményt a megosztott kéz esetén.
     *
     * @param dealer Az osztó értéke
     * @param player A játékos értéke
     * @param split A megosztott kéz értéke
     */
    public void declareSplitWinnings(int dealer, int player, int split) {
        int winnings = 0;
        if (jatekosKez.blackJack()) {
            winnings += jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2;
        } else if (player > dealer) {
            winnings += jatekosKez.getFogadas() * 2;
        } else if (player == dealer) {
            winnings += jatekosKez.getFogadas();
        }
        if (jatekosOsztottKez.blackJack()) {
            winnings += jatekosOsztottKez.getFogadas() + (jatekosOsztottKez.getFogadas() * 3) / 2;
        } else if (split > dealer) {
            winnings += jatekosOsztottKez.getFogadas() * 2;
        } else if (split == dealer) {
            winnings += jatekosOsztottKez.getFogadas();
        }
        if (winnings > 0) {
            payWinnings(winnings);
            screen.vege("YOU WON " + winnings + "€");
        } else {
            screen.vege("DEALER WINS!");
        }
    }

    /**
     * Meghatározza a győztest a játék végén.
     */
    public void declareWinner() {
        int dealer = Math.max(osztoKez.getaszErtek(), osztoKez.getErtek());
        int player = Math.max(jatekosKez.getErtek(), jatekosKez.getaszErtek());
        int split;
        dealer = dealer > 21 ? 0 : dealer;
        player = player > 21 ? 0 : player;

        if (jatekosOsztottKez != null) {
            split = Math.max(jatekosOsztottKez.getErtek(), jatekosOsztottKez.getaszErtek());
            split = split > 21 ? 0 : split;
            declareSplitWinnings(dealer, player, split);
            return;
        }
        if (dealer > player) {
            screen.vege("DEALER WINS");
        } else if (dealer == player) {
            screen.vege("GAME IS SPLIT");
            payWinnings((jatekosKez.getFogadas()));
        } else {
            screen.vege("YOU WIN " + (jatekosKez.getFogadas() * 2) + "€");
            payWinnings((jatekosKez.getFogadas() * 2));
        }
    }

    /**
     * Ellenőrzi, hogy a játékos vagy az osztó kapott-e BlackJack-et.
     *
     * @return true, ha valamelyik kapott BlackJack-et, különben false
     */
    public boolean checkBlackJack() {
        if (jatekosKez.blackJack() && osztoKez.blackJack()) {
            screen.vege("GAME IS SPLIT");
            payWinnings((jatekosKez.getFogadas()));
        } else if (osztoKez.blackJack()) {
            screen.vege("DEALER WINS");
        } else if (jatekosKez.blackJack()) {
            screen.vege("YOU WIN " + (jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2) + "€");
            payWinnings(jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2);
        } else {
            return false;
        }
        screen.felfedRejtettKartya();
        updateHandValue(osztoKez);
        return true;
    }

    /**
     * Kezeli a "deal" gomb megnyomását.
     */
    public void dealPressed() {
        screen.torol();
        jatekosKez = new Hand(GameUI.PLAYER);
        osztoKez = new Hand(GameUI.DEALER);
        jatekosOsztottKez = null;
        dealCard(osztoKez);
        dealCard(osztoKez);
        dealCard(jatekosKez);
        dealCard(jatekosKez);
        jatekosKez.placeBet(jatekosEgyenleg.bet());
        updateHandValue(jatekosKez);
        screen.tetFrissites(jatekosEgyenleg.getBalance(), jatekosKez.getFogadas());
    }

    /**
     * Kezeli a "hit" gomb megnyomását.
     */
    public void hitPressed() {
        Hand temp;
        if (osztottKez) {
            temp = jatekosOsztottKez;
        } else {
            temp = jatekosKez;
        }
        dealCard(temp);
        checkBust(temp);
        updateHandValue(temp);
    }

    /**
     * Kezeli a "stay" gomb megnyomását.
     */
    public void stayPressed() {
        screen.kezEngedelyez(GameUI.SPLIT);
        screen.felfedRejtettKartya();
        while (osztoKez.getErtek() < 17) {
            dealCard(osztoKez);
        }
        updateHandValue(osztoKez);
        if (!checkBust(osztoKez)) {
            declareWinner();
        }
    }

    /**
     * Kezeli a "split" gomb megnyomását.
     */
    public void splitPressed() {
        screen.torolJatekos();
        jatekosOsztottKez = jatekosKez.split();
        jatekosOsztottKez.placeBet(jatekosEgyenleg.bet());
        screen.kartyaHuzasUI(jatekosKez.getKartya(0), GameUI.PLAYER);
        screen.kartyaHuzasUI(jatekosOsztottKez.getKartya(0), GameUI.SPLIT);
        dealCard(jatekosKez);
        dealCard(jatekosOsztottKez);
        setEnablejatekosOsztottKez(true);
        updateHandValue(jatekosKez);
        updateHandValue(jatekosOsztottKez);
        if (jatekosOsztottKez.blackJack()) {
            GameUI.stay.doClick();
            if (jatekosKez.blackJack()) {
                GameUI.stay.doClick();
            }
        }
    }

    /**
     * Kezeli a felhasználói felület eseményeit.
     *
     * @param e Az esemény, amelyet kezelni kell
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "deal" -> {
                if (jatekosEgyenleg.bet() == 0) {
                    screen.vege("Choose smaller bet!");
                    return;
                }
                dealPressed();
                if (checkBlackJack()) {
                    command = "bust";
                }
                GameUI.splitB.setEnabled(jatekosKez.splittelheto());
            }
            case "hit" -> hitPressed();
            case "stay" -> {
                if (osztottKez) {
                    setEnablejatekosOsztottKez(false);
                    if (jatekosKez.blackJack()) {
                        GameUI.stay.doClick();
                    }
                    return;
                }
                stayPressed();
            }
            case "split" -> splitPressed();
            case "double" -> doubleHand(jatekosKez);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
        screen.gombokatEngedelyez(command);
    }


    /**
     * Visszaadja a játékos kezét.
     * 
     * @return a játékos keze
     */
    public Hand getHand() {
        return jatekosKez;
    }
    
    /**
     * Végrehajt egy hit műveletet.
     */
    public void hit() {
        hitPressed();
    }
    
    /**
     * Végrehajt egy surrender műveletet.
     * A játékos feladja a játékot, és visszakapja a tét felét.
     */
    public void surrender() {
        // Implementálni kell a surrender logikát
        jatekosEgyenleg.deposit(jatekosKez.getFogadas() / 2);
        screen.vege("PLAYER SURRENDERS");
    }
    
    /**
     * Visszaadja a játékos egyenlegét.
     * 
     * @return a játékos egyenlege
     */
    public int getBalance() {
        return jatekosEgyenleg.getBalance();
    }
    
    /**
     * Végrehajt egy deal műveletet.
     */
    public void deal() {
        dealPressed();
    }

}