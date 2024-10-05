package blackjack;

public class Card {
    private final String suit;
    private final String rank;
    private final String imagePath;

    public Card(String suit, String rank, String imagePath) {
        this.suit = suit;
        this.rank = rank;
        this.imagePath = imagePath;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String getImagePath() {
        return imagePath;
    }
}