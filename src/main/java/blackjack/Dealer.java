package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Dealer osztály, amely a játék logikáját és a kártyák osztását kezeli.
 */
public class Dealer implements ActionListener {

    private final Deck deck; // A pakli kártyák
    private final GameUI ui; // A játék felhasználói felülete
    private Hand playerHand; // A játékos keze
    private Hand dealerHand; // Az osztó keze
    private Hand splitHand; // A megosztott kéz
    private final CoinPurse playerPurse; // A játékos pénztárcája
    private boolean playingSplitHand; // Jelzi, hogy a játékos a megosztott kézzel játszik-e

    /**
     * Konstruktor, amely inicializálja a paklit, a felhasználói felületet és a játékos pénztárcáját.
     */
    public Dealer() {
        deck = new Deck();
        ui = new GameUI(this);
        playerPurse = new CoinPurse(2000);
        GameUI.betslider.addChangeListener(playerPurse);
    }

    /**
     * Frissíti a kéz értékét a felhasználói felületen.
     *
     * @param hand A frissítendő kéz
     */
    private void updateHandValue(Hand hand) {
        String update = hand.getValue() + "";
        if (hand.hasAce() && hand.getAcedValue() > 0) {
            update += "/" + hand.getAcedValue();
        }
        if (hand.blackJack()) {
            update = "BLACKJACK!";
        }
        ui.updateHandValue(hand.getOwner(), update);
    }

    /**
     * Kioszt egy kártyát a megadott kézhez.
     *
     * @param target A cél kéz, amelyhez a kártyát ki kell osztani
     */
    private void dealCard(Hand target) {
        Card temp = deck.drawCard();
        ui.drawCard(temp, target.getOwner());
        target.addCard(temp);
    }

    /**
     * Ellenőrzi, hogy a kéz értéke meghaladja-e a 21-et (bust).
     *
     * @param hand Az ellenőrizendő kéz
     * @return true, ha a kéz értéke meghaladja a 21-et, különben false
     */
    private boolean checkBust(Hand hand) {
        if (hand.getValue() <= 21) {
            return false;
        }
        if (gameoverBust(hand)) {
            ui.setEnableButton("bust");
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
        switch (hand.getOwner()) {
            case GameUI.SPLIT -> {
                setEnableSplitHand(false);
                gameOver = false;
            }
            case "dealer" -> declareWinner();
            case GameUI.PLAYER -> {
                if (splitHand != null && splitHand.getValue() <= 21) {
                    GameUI.stayButton.doClick();
                    gameOver = false;
                    break;
                }
                ui.gameOver("PLAYER BUSTS");
            }
            default -> throw new IllegalStateException("Unexpected value: " + hand.getOwner());
        }
        return gameOver;
    }

    /**
     * Engedélyezi vagy letiltja a megosztott kéz használatát.
     *
     * @param enable true, ha engedélyezni kell a megosztott kéz használatát, különben false
     */
    private void setEnableSplitHand(boolean enable) {
        playingSplitHand = enable;
        if (enable) {
            ui.enableHand(GameUI.SPLIT);
            ui.disableHand(GameUI.PLAYER);
        } else {
            ui.enableHand(GameUI.PLAYER);
            ui.disableHand(GameUI.SPLIT);
        }
    }

    /**
     * Megduplázza a tétet és kioszt egy kártyát a megadott kézhez.
     *
     * @param hand A megduplázandó kéz
     */
    public void doubleHand(Hand hand) {
        hand.addBet(playerPurse.bet());
        dealCard(hand);
        checkBust(hand);
        updateHandValue(hand);
        GameUI.stayButton.doClick();
    }

    /**
     * Kifizeti a nyereményt a játékosnak.
     *
     * @param amount A kifizetendő összeg
     */
    private void payWinnings(int amount) {
        playerPurse.addMoney(amount);
        ui.updateBets(playerPurse.getBalance(), playerHand.getBet());
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
        if (playerHand.blackJack()) {
            winnings += playerHand.getBet() + (playerHand.getBet() * 3) / 2;
        } else if (player > dealer) {
            winnings += playerHand.getBet() * 2;
        } else if (player == dealer) {
            winnings += playerHand.getBet();
        }
        if (splitHand.blackJack()) {
            winnings += splitHand.getBet() + (splitHand.getBet() * 3) / 2;
        } else if (split > dealer) {
            winnings += splitHand.getBet() * 2;
        } else if (split == dealer) {
            winnings += splitHand.getBet();
        }
        if (winnings > 0) {
            payWinnings(winnings);
            ui.gameOver("YOU WON " + winnings + "€");
        } else {
            ui.gameOver("DEALER WINS!");
        }
    }

    /**
     * Meghatározza a győztest a játék végén.
     */
    public void declareWinner() {
        int dealer = Math.max(dealerHand.getAcedValue(), dealerHand.getValue());
        int player = Math.max(playerHand.getValue(), playerHand.getAcedValue());
        int split;
        dealer = dealer > 21 ? 0 : dealer;
        player = player > 21 ? 0 : player;

        if (splitHand != null) {
            split = Math.max(splitHand.getValue(), splitHand.getAcedValue());
            split = split > 21 ? 0 : split;
            declareSplitWinnings(dealer, player, split);
            return;
        }
        if (dealer > player) {
            ui.gameOver("DEALER WINS");
        } else if (dealer == player) {
            ui.gameOver("GAME IS SPLIT");
            payWinnings((playerHand.getBet()));
        } else {
            ui.gameOver("YOU WIN " + (playerHand.getBet() * 2) + "€");
            payWinnings((playerHand.getBet() * 2));
        }
    }

    /**
     * Ellenőrzi, hogy a játékos vagy az osztó kapott-e BlackJack-et.
     *
     * @return true, ha valamelyik kapott BlackJack-et, különben false
     */
    public boolean checkBlackJack() {
        if (playerHand.blackJack() && dealerHand.blackJack()) {
            ui.gameOver("GAME IS SPLIT");
            payWinnings((playerHand.getBet()));
        } else if (dealerHand.blackJack()) {
            ui.gameOver("DEALER WINS");
        } else if (playerHand.blackJack()) {
            ui.gameOver("YOU WIN " + (playerHand.getBet() + (playerHand.getBet() * 3) / 2) + "€");
            payWinnings(playerHand.getBet() + (playerHand.getBet() * 3) / 2);
        } else {
            return false;
        }
        ui.revealDealerCard();
        updateHandValue(dealerHand);
        return true;
    }

    /**
     * Kezeli a "deal" gomb megnyomását.
     */
    public void dealPressed() {
        ui.clear();
        playerHand = new Hand("player");
        dealerHand = new Hand("dealer");
        splitHand = null;
        dealCard(dealerHand);
        dealCard(dealerHand);
        dealCard(playerHand);
        dealCard(playerHand);
        playerHand.addBet(playerPurse.bet());
        updateHandValue(playerHand);
        ui.updateBets(playerPurse.getBalance(), playerHand.getBet());
    }

    /**
     * Kezeli a "hit" gomb megnyomását.
     */
    public void hitPressed() {
        Hand temp;
        if (playingSplitHand) {
            temp = splitHand;
        } else {
            temp = playerHand;
        }
        dealCard(temp);
        checkBust(temp);
        updateHandValue(temp);
    }

    /**
     * Kezeli a "stay" gomb megnyomását.
     */
    public void stayPressed() {
        ui.enableHand(GameUI.SPLIT);
        ui.revealDealerCard();
        while (dealerHand.getValue() < 17) {
            dealCard(dealerHand);
        }
        updateHandValue(dealerHand);
        if (!checkBust(dealerHand)) {
            declareWinner();
        }
    }

    /**
     * Kezeli a "split" gomb megnyomását.
     */
    public void splitPressed() {
        ui.clearPlayer();
        splitHand = playerHand.split();
        splitHand.addBet(playerPurse.bet());
        ui.drawCard(playerHand.getCard(0), GameUI.PLAYER);
        ui.drawCard(splitHand.getCard(0), GameUI.SPLIT);
        dealCard(playerHand);
        dealCard(splitHand);
        setEnableSplitHand(true);
        updateHandValue(playerHand);
        updateHandValue(splitHand);
        if (splitHand.blackJack()) {
            GameUI.stayButton.doClick();
            if (playerHand.blackJack()) {
                GameUI.stayButton.doClick();
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
                if (!playerPurse.betAcceptable()) {
                    ui.gameOver("Choose smaller bet!");
                    return;
                }
                dealPressed();
                if (checkBlackJack()) {
                    command = "bust";
                }
                GameUI.splitButton.setEnabled(playerHand.isSplittable());
            }
            case "hit" -> hitPressed();
            case "stay" -> {
                if (playingSplitHand) {
                    setEnableSplitHand(false);
                    if (playerHand.blackJack()) {
                        GameUI.stayButton.doClick();
                    }
                    return;
                }
                stayPressed();
            }
            case "split" -> splitPressed();
            case "double" -> doubleHand(playerHand);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
        ui.setEnableButton(command);
    }

    

    /**
     * Visszaadja a játékos kezét.
     * 
     * @return a játékos keze
     */
    public Hand getHand() {
        return playerHand;
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
        playerPurse.addMoney(playerHand.getBet() / 2);
        ui.gameOver("PLAYER SURRENDERS");
    }
    
    /**
     * Visszaadja a játékos egyenlegét.
     * 
     * @return a játékos egyenlege
     */
    public int getBalance() {
        return playerPurse.getBalance();
    }
    
    /**
     * Végrehajt egy deal műveletet.
     */
    public void deal() {
        dealPressed();
    }

}