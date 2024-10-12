package blackjack;

import java.util.ArrayList;

/**
 * Ez az osztály egyetlen kezet reprezentál a blackjack játékban.
 * 
 * @autor Wampie
 */
public class Hand {

    /**
     * A kártyák listája, amelyeket a kézhez osztottak.
     */
    private final ArrayList<Card> cards;
    
    /**
     * A kéz értéke a BlackJack szabályai szerint.
     */
    private int value = 0;
    
    /**
     * Ha a kéz ászt tartalmaz, a BlackJack-ben két értéke lehet, ezt a változó kezeli.
     */
    private int acedValue = 0;
    
    /**
     * A pénzösszeg, amit a játékos a kézre tett.
     */
    private int bet = 0;
    
    /**
     * Mivel az ász lehet 1 vagy 11 a BlackJack-ben, tudnunk kell, hogy a kéz tartalmaz-e egyet.
     */
    private boolean hasAce = false;
    
    /**
     * A legtöbb BlackJack szabály szerint egy osztás során csak egyszer lehet osztani.
     * Ha a kezet egyszer már osztották, nem lehet újra osztani.
     */
    private boolean isSplitted = false;
    
    /**
     * A BlackJack-ben a kéz tartozhat a játékoshoz vagy az osztóhoz, ebben a programban különbséget teszünk a játékos és az osztott kéz között is.
     */
    private final String owner;

    /**
     * Létrehoz egy új Hand objektumot és megadja a tulajdonost.
     * 
     * @param owner A kéz tulajdonosa
     */
    public Hand(String owner) {
        cards = new ArrayList<>();
        this.owner = owner;
    }

    /**
     * Hozzáad egy adott kártyát a kézhez. Ha a kártya null, visszatér anélkül, hogy bármit is tenne.
     * 
     * @param c A kézhez osztott kártya
     */
    public void addCard(Card c) {
        if (c == null) {
            return;
        }
        cards.add(c);
        value += c.getRank();
        acedValue += c.getRank();
        if (c.getRank() == 1 && !hasAce) {
            hasAce = true;
            acedValue = value + 10;
        }
    }

    /**
     * Ellenőrzi, hogy a kéz tartalmaz-e BlackJack-et (21 érték az első két kártyával, ÁSZ és 10 vagy J vagy Q vagy K).
     * 
     * @return true, ha a kéz BlackJack-et tartalmaz
     */
    public boolean blackJack() {
        if (!hasAce || cards.size() != 2) {
            return false;
        }
        return acedValue == 21;
    }

    /**
     * A BlackJack-ben oszthat egy kezet, amely párokat tartalmaz, két kézre, amelyek mindegyike egy új kártyát kap.
     * A metódus ellenőrzi, hogy a kéz tulajdonosa a játékos, és hogy a kezet még nem osztották fel korábban (újraosztás nem engedélyezett), és hogy mindkét kártya értéke megegyezik.
     * 
     * @return true, ha a kéz párokat tartalmaz az első két kártyával.
     */
    public boolean isSplittable() {
        return owner.equalsIgnoreCase("player") && cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank() && !isSplitted;
    }

    /**
     * Létrehoz egy új kezet az eredeti kéz egyik kártyájának felhasználásával.
     * Ha a kéz nem osztható, null értéket ad vissza.
     * 
     * @return Az osztott kéz
     */
    public Hand split() {
        if (!isSplittable()) {
            return null;
        } else {
            Hand split = new Hand("split");
            split.addCard(cards.remove(1));
            value = cards.get(0).getRank();
            isSplitted = true;
            return split;
        }
    }

    /**
     * Visszaadja a kéz értékét, amikor az Ász 11-nek számít.
     * Ha az érték meghaladja a 21-et, 0-t ad vissza.
     * 
     * @return A kéz értéke, amikor az Ász 11-nek számít
     */
    public int getAcedValue() {
        if (acedValue > 21) {
            return 0;
        }
        return acedValue;
    }

    /**
     * Hozzáad egy adott összeget a kézre tett tét összegéhez. Ha kevesebb, mint 10, 10-et ad hozzá.
     * 
     * @param b A megadott tét
     */
    public void addBet(int b) {
        this.bet += b < 10 ? 10 : b;
    }

    /**
     * Visszaadja a megadott indexhez osztott kártyát.
     * 
     * @param index A kívánt kártya indexe
     * @return A megadott indexhez tartozó kártya
     */
    public Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Visszaadja a kéz értékét, az ászokat 1-nek tekintik.
     * 
     * @return A kéz értéke
     */
    public int getValue() {
        return value;
    }

    /**
     * A kéz tulajdonosa.
     * 
     * @return A tulajdonos
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Visszaadja, hogy a kéz tartalmaz-e Ászt.
     * 
     * @return true, ha a kéz Ászt tartalmaz
     */
    public boolean hasAce() {
        return hasAce;
    }

    /**
     * Visszaadja a kézre tett tét összegét.
     * 
     * @return A kézre tett tét összege
     */
    public int getBet() {
        return bet;
    }

    /**
     * Visszaadja a kéz méretét tesztelés céljából.
     * 
     * @return A kéz mérete
     */
    public int getSize() {
        return cards.size();
    }
}