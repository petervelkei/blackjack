package blackjack;

import java.util.LinkedList;
import java.util.List;

/**
 * Ez az osztály egyetlen kezet reprezentál a blackjack játékban.
 * 
 * @autor Wampie
 */
public class Hand {
    private final List<Card> kartyak = new LinkedList<>();
    private boolean asz = false;
    private boolean splittelt = false;
    private int fogadas = 0;
    private int ertek = 0;
    private int aszErtek = 0;
    private final String felhasznalo;

    /**
     * Létrehoz egy új Hand objektumot és megadja a tulajdonost.
     * 
     * @param felhasznalo A kéz tulajdonosa
     */
    public Hand(String f) {
        felhasznalo = f;
    }

    /**
     * Hozzáad egy adott kártyát a kézhez. Ha a kártya null, visszatér anélkül, hogy bármit is tenne.
     * 
     * @param c A kézhez osztott kártya
     */
    public void addCard(Card c) {
        if (c != null) {
            kartyak.add(c);
            ertek += c.getRank();
            aszErtek += c.getRank();
            if (c.getRank() == 1 && !asz) {
                asz = true;
                aszErtek = ertek + 10;
            }
        }
    }

    /**
     * Ellenőrzi, hogy a kéz tartalmaz-e BlackJack-et (21 érték az első két kártyával, ÁSZ és 10 vagy J vagy Q vagy K).
     * 
     * @return true, ha a kéz BlackJack-et tartalmaz
     */
    public boolean blackJack() {
        return asz && kartyak.size() == 2 && aszErtek == 21;
    }

    /**
     * A BlackJack-ben oszthat egy kezet, amely párokat tartalmaz, két kézre, amelyek mindegyike egy új kártyát kap.
     * A metódus ellenőrzi, hogy a kéz tulajdonosa a játékos, és hogy a kezet még nem osztották fel korábban (újraosztás nem engedélyezett), és hogy mindkét kártya értéke megegyezik.
     * 
     * @return true, ha a kéz párokat tartalmaz az első két kártyával.
     */
    public boolean splittelheto() {
        return felhasznalo.equalsIgnoreCase("player") && kartyak.size() == 2 && kartyak.get(0).getRank() == kartyak.get(1).getRank() && !splittelt;
    }

    /**
     * Létrehoz egy új kezet az eredeti kéz egyik kártyájának felhasználásával.
     * Ha a kéz nem osztható, null értéket ad vissza.
     * 
     * @return Az osztott kéz
     */
    public Hand split() {
        if (splittelheto()) {
            Hand s = new Hand("split");
            s.addCard(kartyak.remove(1));
            ertek = kartyak.get(0).getRank();
            splittelt = true;
            return s;
        }
        return null;
    }

    /**
     * Visszaadja a kéz értékét, amikor az Ász 11-nek számít.
     * Ha az érték meghaladja a 21-et, 0-t ad vissza.
     * 
     * @return A kéz értéke, amikor az Ász 11-nek számít
     */
    public int getaszErtek() {
        return aszErtek > 21 ? 0 : aszErtek;
    }

    /**
     * Hozzáad egy adott összeget a kézre tett tét összegéhez. Ha kevesebb, mint 10, 10-et ad hozzá.
     * 
     * @param b A megadott tét
     */
    public void placeBet(int b) {
        fogadas += Math.max(b, 10);
    }

    /**
     * Visszaadja a megadott indexhez osztott kártyát.
     * 
     * @param i A kívánt kártya indexe
     * @return A megadott indexhez tartozó kártya
     */
    public Card getKartya(int i) {
        return kartyak.get(i);
    }

    /**
     * Visszaadja a kéz értékét, az ászokat 1-nek tekintik.
     * 
     * @return A kéz értéke
     */
    public int getErtek() {
        return ertek;
    }

    /**
     * A kéz tulajdonosa.
     * 
     * @return A tulajdonos
     */
    public String getFelhasznalo() {
        return felhasznalo;
    }

    /**
     * Visszaadja, hogy a kéz tartalmaz-e Ászt.
     * 
     * @return true, ha a kéz Ászt tartalmaz
     */
    public boolean VanAsza() {
        return asz;
    }

    /**
     * Visszaadja a kézre tett tét összegét.
     * 
     * @return A kézre tett tét összege
     */
    public int getFogadas() {
        return fogadas;
    }

    /**
     * Visszaadja a kéz méretét tesztelés céljából.
     * 
     * @return A kéz mérete
     */
    public int getKartyakMeret() {
        return kartyak.size();
    }
}