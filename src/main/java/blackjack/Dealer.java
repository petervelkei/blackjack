package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Dealer osztály, amely a játék logikáját és a kártyák osztását kezeli.
 */
public class Dealer implements ActionListener {

    private Deck asztal;
    private GameUI screen;
    private Hand jatekosKez;
    private Hand osztoKez;
    private Hand jatekosOsztottKez;
    private CoinPurse jatekosEgyenleg;
    private boolean osztottKez;

    public void jatekIndit() {
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
    private void kezErtekFrissit(Hand h) {
        String update = h.getErtek() + "";
        if (h.vanAsz() && h.getaszErtek() > 0) {
            update += "/" + h.getaszErtek();
        }
        if (h.blackJack()) {
            update = "BLACKJACK!";
        }
        screen.updateKezErtek(h.getFelhasznalo(), update);
    }

    /**
     * Kioszt egy kártyát a megadott kézhez.
     *
     * @param cel A cél kéz, amelyhez a kártyát ki kell osztani
     */
    private void kartyaOszt(Hand cel) {
        Card t = asztal.draw();
        screen.kartyaHuzasUI(t, cel.getFelhasznalo());
        cel.addCard(t);
    }

    /**
     * Ellenőrzi, hogy a kéz értéke meghaladja-e a 21-et (bust).
     *
     * @param h Az ellenőrizendő kéz
     * @return true, ha a kéz értéke meghaladja a 21-et, különben false
     */
    private boolean ellenorizBust(Hand h) {
        if (h.getErtek() <= 21) {
            return false;
        }
        if (jatekVegeBust(h)) {
            screen.gombokatEngedelyez("bust");
        }
        return true;
    }

    /**
     * Kezeli a játék végét, ha a kéz értéke meghaladja a 21-et.
     *
     * @param h Az ellenőrizendő kéz
     * @return true, ha a játék véget ért, különben false
     */
    public boolean jatekVegeBust(Hand h) {
        boolean gameOver = true;
        switch (h.getFelhasznalo()) {
            case GameUI.SPLIT -> {
                setEnablejatekosOsztottKez(false);
                gameOver = false;
            }
            case GameUI.DEALER -> nyertestHirdet();
            case GameUI.PLAYER -> {
                if (jatekosOsztottKez != null && jatekosOsztottKez.getErtek() <= 21) {
                    GameUI.stay.doClick();
                    gameOver = false;
                    break;
                }
                screen.vege("PLAYER BUSTS");
            }
            default -> throw new IllegalStateException("Unexpected value: " + h.getFelhasznalo());
        }
        return gameOver;
    }

    /**
     * Engedélyezi vagy letiltja a megosztott kéz használatát.
     *
     * @param beallit true, ha engedélyezni kell a megosztott kéz használatát, különben false
     */
    private void setEnablejatekosOsztottKez(boolean beallit) {
        osztottKez = beallit;
        if (beallit) {
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
     * @param h A megduplázandó kéz
     */
    public void duplazKez(Hand h) {
        h.placeBet(jatekosEgyenleg.bet());
        kartyaOszt(h);
        ellenorizBust(h);
        kezErtekFrissit(h);
        GameUI.stay.doClick();
    }

    /**
     * Kifizeti a nyereményt a játékosnak.
     *
     * @param penz A kifizetendő összeg
     */
    private void nyeremenyKifizet(int penz) {
        jatekosEgyenleg.deposit(penz);
        screen.tetFrissites(jatekosEgyenleg.getBalance(), jatekosKez.getFogadas());
    }

    /**
     * Kifizeti a nyereményt a megosztott kéz esetén.
     *
     * @param oszto Az osztó értéke
     * @param jatekos A játékos értéke
     * @param megosztottKez A megosztott kéz értéke
     */
    public void nyeremenyKifizetSplit(int oszto, int jatekos, int megosztottKez) {
        int nyeremeny = 0;
        if (jatekosKez.blackJack()) {
            nyeremeny += jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2;
        } else if (jatekos > oszto) {
            nyeremeny += jatekosKez.getFogadas() * 2;
        } else if (jatekos == oszto) {
            nyeremeny += jatekosKez.getFogadas();
        }
        if (jatekosOsztottKez.blackJack()) {
            nyeremeny += jatekosOsztottKez.getFogadas() + (jatekosOsztottKez.getFogadas() * 3) / 2;
        } else if (megosztottKez > oszto) {
            nyeremeny += jatekosOsztottKez.getFogadas() * 2;
        } else if (megosztottKez == oszto) {
            nyeremeny += jatekosOsztottKez.getFogadas();
        }
        if (nyeremeny > 0) {
            nyeremenyKifizet(nyeremeny);
            screen.vege("YOU WON " + nyeremeny + "€");
        } else {
            screen.vege("DEALER WINS!");
        }
    }

    /**
     * Meghatározza a győztest a játék végén.
     */
    public void nyertestHirdet() {
        int oszto = Math.max(osztoKez.getaszErtek(), osztoKez.getErtek());
        int jatekos = Math.max(jatekosKez.getErtek(), jatekosKez.getaszErtek());
        int osztottKezErtek;
        oszto = oszto > 21 ? 0 : oszto;
        jatekos = jatekos > 21 ? 0 : jatekos;

        if (jatekosOsztottKez != null) {
            osztottKezErtek = Math.max(jatekosOsztottKez.getErtek(), jatekosOsztottKez.getaszErtek());
            osztottKezErtek = osztottKezErtek > 21 ? 0 : osztottKezErtek;
            nyeremenyKifizetSplit(oszto, jatekos, osztottKezErtek);
            return;
        }
        if (oszto > jatekos) {
            screen.vege("DEALER WINS");
        } else if (oszto == jatekos) {
            screen.vege("GAME IS EVEN");
            nyeremenyKifizet((jatekosKez.getFogadas()));
        } else {
            screen.vege("YOU WIN " + (jatekosKez.getFogadas() * 2) + "€");
            nyeremenyKifizet((jatekosKez.getFogadas() * 2));
        }
    }

    /**
     * Ellenőrzi, hogy a játékos vagy az osztó kapott-e BlackJack-et.
     *
     * @return true, ha valamelyik kapott BlackJack-et, különben false
     */
    public boolean ellenorizBlackJack() {
        if (jatekosKez.blackJack() && osztoKez.blackJack()) {
            screen.vege("GAME IS EVEN");
            nyeremenyKifizet((jatekosKez.getFogadas()));
        } else if (osztoKez.blackJack()) {
            screen.vege("DEALER WINS");
        } else if (jatekosKez.blackJack()) {
            screen.vege("YOU WIN " + (jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2) + "€");
            nyeremenyKifizet(jatekosKez.getFogadas() + (jatekosKez.getFogadas() * 3) / 2);
        } else {
            return false;
        }
        screen.felfedRejtettKartya();
        kezErtekFrissit(osztoKez);
        return true;
    }

    /**
     * Kezeli a "deal" gomb megnyomását.
     */
    public void osztasGomb() {
        screen.torol();
        jatekosKez = new Hand(GameUI.PLAYER);
        osztoKez = new Hand(GameUI.DEALER);
        jatekosOsztottKez = null;
        kartyaOszt(osztoKez);
        kartyaOszt(osztoKez);
        kartyaOszt(jatekosKez);
        kartyaOszt(jatekosKez);
        jatekosKez.placeBet(jatekosEgyenleg.bet());
        kezErtekFrissit(jatekosKez);
        screen.tetFrissites(jatekosEgyenleg.getBalance(), jatekosKez.getFogadas());
    }

    /**
     * Kezeli a "hit" gomb megnyomását.
     */
    public void huzasGomb() {
        Hand t;
        if (osztottKez) {
            t = jatekosOsztottKez;
        } else {
            t = jatekosKez;
        }
        kartyaOszt(t);
        ellenorizBust(t);
        kezErtekFrissit(t);
    }

    /**
     * Kezeli a "stay" gomb megnyomását.
     */
    public void megallasGomb() {
        screen.kezEngedelyez(GameUI.SPLIT);
        screen.felfedRejtettKartya();
        while (osztoKez.getErtek() < 17) {
            kartyaOszt(osztoKez);
        }
        kezErtekFrissit(osztoKez);
        if (!ellenorizBust(osztoKez)) {
            nyertestHirdet();
        }
    }

    /**
     * Kezeli a "split" gomb megnyomását.
     */
    public void megosztasGomb() {
        screen.torolJatekos();
        jatekosOsztottKez = jatekosKez.split();
        jatekosOsztottKez.placeBet(jatekosEgyenleg.bet());
        screen.kartyaHuzasUI(jatekosKez.getKartya(0), GameUI.PLAYER);
        screen.kartyaHuzasUI(jatekosOsztottKez.getKartya(0), GameUI.SPLIT);
        kartyaOszt(jatekosKez);
        kartyaOszt(jatekosOsztottKez);
        setEnablejatekosOsztottKez(true);
        kezErtekFrissit(jatekosKez);
        kezErtekFrissit(jatekosOsztottKez);
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
                if (!jatekosEgyenleg.helyesTet()) {
                    screen.vege("Choose smaller bet!");
                    return;
                }
                osztasGomb();
                if (ellenorizBlackJack()) {
                    command = "bust";
                }
                GameUI.splitB.setEnabled(jatekosKez.splittelheto());
            }
            case "hit" -> huzasGomb();
            case "stay" -> {
                if (osztottKez) {
                    setEnablejatekosOsztottKez(false);
                    if (jatekosKez.blackJack()) {
                        GameUI.stay.doClick();
                    }
                    return;
                }
                megallasGomb();
            }
            case "split" -> megosztasGomb();
            case "double" -> duplazKez(jatekosKez);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
        screen.gombokatEngedelyez(command);
    }


    /**
     * Visszaadja a játékos kezét.
     * 
     * @return a játékos keze
     */
    public Hand getJatekosKez() {
        return jatekosKez;
    }
    
    /**
     * Végrehajt egy hit műveletet.
     */
    public void huzas() {
        huzasGomb();
    }
    

    
    /**
     * Visszaadja a játékos egyenlegét.
     * 
     * @return a játékos egyenlege
     */
    public int getEgyenleg() {
        return jatekosEgyenleg.getBalance();
    }
    
    /**
     * Végrehajt egy deal műveletet.
     */
    public void osztas() {
        osztasGomb();
    }
}