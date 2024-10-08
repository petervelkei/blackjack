# Blackjack dokumentációja

## Card osztály

### Áttekintés
A `Card` osztály egy kártyát reprezentál a Blackjack játékban. Ez az osztály tartalmazza a kártya színét, rangját és a kép elérési útját. Biztosítja az attribútumok lekérdezésére szolgáló metódusokat.

### Osztály
#### `Card`
Egy kártya, amely három attribútummal rendelkezik: szín (`suit`), rang (`rank`), és a kártya képének elérési útja (`imagePath`).

### Konstruktor

#### `Card(String suit, String rank, String imagePath)`
Létrehoz egy új `Card` objektumot a megadott paraméterekkel.

- **Paraméterek:**
  - `suit` (`String`): A kártya színe (pl. `hearts`, `diamonds`, `clubs`, `spades`).
  - `rank` (`String`): A kártya rangja (pl. `2`, `3`, `4`, ..., `10`, `jack`, `queen`, `king`, `ace`).
  - `imagePath` (`String`): A kártya képének fájl elérési útja.

- **Példa:**
  ```java
  Card card = new Card("hearts", "ace", "/images/ace_of_hearts.png");

### Metódusok

#### `String getSuit()`
Visszaadja a kártya színét.

#### `String getRank()`
Visszaadja a kártya rangját.

#### `String getImagePath()`
Visszaadja a kártya képének elérési útját.

- **Példa:**
  ```java
  String suit = card.getSuit();
  String rank = card.getRank();
  String imagePath = card.getImagePath();


---

# Deck Osztály

## Áttekintés

A `Deck` osztály a kártyapaklit reprezentálja a Blackjack játékban. Az osztály tartalmazza a kártyapaklit, és biztosítja a kártyák inicializálását, keverését, húzását és visszaállítását.

## Osztály

### `Deck`
A kártyapakli, amely egy listát tartalmaz a kártyákból.

### Konstruktor
### `Deck()`
Létrehoz egy új `Deck` objektumot, és inicializálja a kártyapaklit.

### Metódusok

