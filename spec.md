# Blackjack Játék Specifikáció

## Feladat rövid szöveges ismertetése

A feladat egy egyszerű Blackjack játék megvalósítása Java nyelven, amely grafikus felhasználói felülettel (GUI) rendelkezik. A játék célja, hogy a játékos kártyáinak összértéke közelebb legyen a 21-hez, mint az osztóé, anélkül, hogy túllépné a 21-et. A játékos különböző műveleteket hajthat végre, mint például kártya húzása (Hit), megállás (Stand), duplázás (Double) és osztás (Split). A játékosnak lehetősége van fogadni, és a játék végén nyerhet vagy veszíthet a fogadása alapján. A játék grafikus felülete lehetővé teszi a játékos számára, hogy könnyen kezelje a játékot és kövesse a játék állapotát.

## Use-case-ek

1. **Játék indítása**
   - A felhasználó elindítja az alkalmazást, és megjelenik a főmenü.
   - A felhasználó a "Start Game" gombra kattintva elindítja a játékot.

2. **Fogadás elhelyezése**
   - A felhasználó a főmenüben kiválasztja a fogadni kívánt összeget a chip gombok segítségével.
   - A felhasználó a "Start Game" gombra kattintva elindítja a játékot a kiválasztott fogadással.

3. **Kártya húzása (Hit)**
   - A játék során a felhasználó a "Hit" gombra kattintva húz egy új kártyát.
   - A játék frissíti a játékos kártyáit és pontszámát.

4. **Megállás (Stand)**
   - A játék során a felhasználó a "Stand" gombra kattintva befejezi a körét.
   - Az osztó folytatja a játékot a szabályok szerint, majd a játék véget ér.

5. **Duplázás (Double)**
   - A játék során a felhasználó a "Double" gombra kattintva megduplázza a fogadását és húz egy új kártyát.
   - A játék frissíti a játékos kártyáit és pontszámát, majd az osztó folytatja a játékot.

6. **Osztás (Split)**
   - Ha a játékos két azonos értékű kártyát kap, a "Split" gombra kattintva két külön kézre oszthatja a kártyáit.
   - A játék frissíti a játékos kártyáit és pontszámát mindkét kézre.

7. **Játék újraindítása**
   - A játék végén a felhasználó a "Reset Game" gombra kattintva újraindíthatja a játékot.
   - A játék visszaállítja az eredeti állapotot és új játékot kezd.

8. **Visszatérés a főmenübe**
   - A játék során a felhasználó a "Back to Menu" gombra kattintva visszatérhet a főmenübe.
   - A játék visszaállítja az eredeti állapotot és visszatér a főmenübe.

## Megoldási ötlet vázlatos ismertetése

A játék megvalósítása Java nyelven történik, a grafikus felhasználói felületet a Swing könyvtár segítségével hozzuk létre. Az alkalmazás MVC (Model-View-Controller) architektúrát követ, ahol a `Game` osztály a játék logikáját, a `UI` osztály a felhasználói felületet, és a `Balance` osztály a játékos egyenlegét kezeli.

- **Technológiai megoldások:**
  - Java nyelv
  - Swing könyvtár a GUI megvalósításához
  - JUnit a teszteléshez

- **Fájlformátumok:**
  - A kártyák és zsetonok képei PNG formátumban kerülnek tárolásra a `resources` mappában.
  - A játék állapotát és a játékos egyenlegét a memóriában tároljuk, nem használunk külső fájlokat vagy adatbázisokat.

- **Főbb osztályok:**
  - `Game`: A játék logikáját kezeli, beleértve a kártyák húzását, a játék végének ellenőrzését és a játék újraindítását.
  - `UI`: A grafikus felhasználói felületet kezeli, beleértve a gombok és panelek létrehozását és frissítését.
  - `Balance`: A játékos egyenlegét és fogadásait kezeli.

- **Főbb funkciók:**
  - Kártyák húzása és megjelenítése
  - Fogadások kezelése és frissítése
  - Játék állapotának frissítése és megjelenítése
  - Játék végének kezelése és üzenetek megjelenítése

A specifikáció alapján a játék könnyen bővíthető és karbantartható, mivel az MVC architektúra lehetővé teszi a különböző komponensek elkülönítését és független fejlesztését.