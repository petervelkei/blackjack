package blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * A Game osztály, amely a Blackjack játék logikáját kezeli.
 * 
 * Ez az osztály tartalmazza a játékhoz szükséges összes logikát, beleértve a kártyák
 * osztását, a játékos és az osztó kártyáinak kezelését, a játék állapotának frissítését,
 * valamint a játék végének kezelését.
 */
public class Game {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final UI ui;

    /**
     * Létrehoz egy új Game objektumot, és inicializálja a játékhoz szükséges komponenseket.
     */
    public Game() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        ui = new UI(this);
    }

    /**
     * Elindítja a játékot és inicializálja a grafikus felhasználói felületet.
     * 
     * Ez a metódus létrehozza és megjeleníti a grafikus felhasználói felületet,
     * majd kiosztja a kezdeti kártyákat a játékosnak és az osztónak.
     */
    public void start() {
        ui.createAndShowGUI();
        dealInitialCards();
    }

    /**
     * Visszaadja a játékos objektumot.
     * 
     * @return A játékos objektum.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Visszaadja az osztó objektumot.
     * 
     * @return Az osztó objektum.
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Visszaadja a kártyapakli objektumot.
     * 
     * @return A kártyapakli objektum.
     */
    public Deck getDeck() {
        return deck;
    }


    /**
     * Kiosztja a kezdeti kártyákat a játékosnak és az osztónak.
     * 
     * Ez a metódus törli a játékos és az osztó kezében lévő kártyákat, majd
     * két-két kártyát oszt mindkettőjüknek. Az osztó második kártyája rejtett lesz.
     * Végül frissíti a grafikus felhasználói felületet.
     */
    public void dealInitialCards() {
        player.reset();
        dealer.reset();
        player.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw(), true);
        ui.updateUI();
    }

    /*
     * A játékos húz egy kártyát.
     * 
     * Ez a metódus egy új kártyát ad a játékos kezéhez, majd frissíti a grafikus
     * felhasználói felületet. Ha a játékos keze túllépi a 21-et vagy blackjack-je van,
     * a játék véget ér.
     */
    public void playerDrawCard() {
        player.addCard(deck.draw());
        ui.updateUI();
    }


    /**
     * Az osztó húz egy kártyát.
     * 
     * Ez a metódus egy új kártyát ad az osztó kezéhez, majd frissíti a grafikus
     * felhasználói felületet.
     */
    public void dealerDrawCard() {
        dealer.addCard(deck.draw());
        ui.updateUI();
    }


    /**
     * Ellenőrzi, hogy a játék véget ért-e.
     * 
     * @return true, ha a játék véget ért, különben false.
     */
    public boolean isGameOver() {
        return player.isBusted() || dealer.isBusted() || player.hasBlackjack() || dealer.hasBlackjack(); 
    }


    /**
     * Kezeli a játék végét és megjeleníti a megfelelő üzenetet.
     * 
     * Ez a metódus felfedi az osztó rejtett kártyáját, majd az osztó húz kártyákat,
     * amíg a keze értéke el nem éri a 17-et. Ezután összehasonlítja a játékos és az
     * osztó kezét, és megjeleníti a megfelelő üzenetet a játék végének eredményéről.
     */
    public void endGame() {
        dealer.revealHiddenCard(); // Felfedjük a lefordított lapot
        while (dealer.mustDrawCard()) {
            dealerDrawCard();
        }
    
        if (player.isPush(dealer)) {
            ui.showEndGameMessage("Push!");
        } else if (player.isBusted() || (!dealer.isBusted() && dealer.hasBetterHandThan(player)) || (dealer.hasBlackjack() && !player.hasBlackjack())) {
            ui.showEndGameMessage("Dealer wins!");
        } else {
            ui.showEndGameMessage("Player wins!");
        }
    }
    

    /**
     * Kiszámítja a megadott kéz összértékét.
     * 
     * Az ászok értéke 11 vagy 1 lehet, attól függően, hogy melyik érték
     * tartja a kéz összértékét 21 alatt vagy annak megfelelően.
     * 
     * @param hand A kártyák listája, amelynek összértékét ki kell számítani.
     * @return A kéz összértéke.
     */
    public int calculatePoints(List<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            switch (card.getRank()) {
                case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> value += Integer.parseInt(card.getRank());
                case "jack", "queen", "king" -> value += 10;
                case "ace" -> aceCount++;
                default -> throw new IllegalArgumentException("Unexpected card rank: " + card.getRank());
            }
        }

        for (int i = 0; i < aceCount; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }

        return value;
    }

    /**
     * Újraindítja a játékot.
     * 
     * Ez a metódus visszaállítja a kártyapaklit, a játékos és az osztó kezét,
     * majd kiosztja a kezdeti kártyákat.
     */
    public void restart() {
        deck.reset();
        player.reset();
        dealer.reset();
        dealInitialCards();
    }

    /**
     * Osztja a játékos kezét, ha a két kártya azonos értékű.
     * 
     * Ez a metódus ellenőrzi, hogy a játékos kezében lévő két kártya azonos értékű-e,
     * és ha igen, akkor két külön kézre osztja őket. Mindkét kézhez egy-egy új kártyát
     * húz a pakliból. Ha a kártyák nem oszthatók, IllegalArgumentException kivételt dob.
     * 
     * @throws IllegalArgumentException ha a játékos keze nem osztható.
     */
    public void split() {
        if (player.getHand().size() == 2 && player.getHand().get(0).getRank().equals(player.getHand().get(1).getRank())) {
            List<Card> newHand = new ArrayList<>();
            newHand.add(player.getHand().remove(1));
            player.addHand(newHand);
            player.addCard(deck.draw());
            player.addCard(deck.draw());
            ui.updateUI();
        } else {
            throw new IllegalArgumentException("Cannot split hand!");
        }
    }
}