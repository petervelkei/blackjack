package blackjack;

public class Dealer extends Player {
    // Osztó specifikus logika

    private static final String DEALERS_HAND = "Dealer's hand:";

    public boolean mustDrawCard() {
        return getHandValue() < 17;
    }

    @Override
    public boolean isBusted() {
        return getHandValue() > 21;
    }

    @Override
    public boolean isStanding() {
        return getHandValue() >= 17;
    }

    @Override
    public boolean hasBlackjack() {
        return getHandValue() == 21 && getHand().size() == 2;
    }

    @Override
    public boolean isFiveCardTrick() {
        return getHand().size() == 5 && getHandValue() <= 21;
    }

    @Override
    public boolean isPush(Player other) {
        return getHandValue() == other.getHandValue();
    }

    @Override
    public boolean hasBetterHandThan(Player other) {
        return getHandValue() > other.getHandValue();
    }

    @Override
    public boolean hasLesserHandThan(Player other) {
        return getHandValue() < other.getHandValue();
    }

    @Override
    public void clearHand() {
        getHand().clear();
    }

    @Override
    public void addCard(Card card) {
        getHand().add(card);
    }

    public void printHand() {
        System.out.println(DEALERS_HAND);
        for (Card card : getHand()) {
            System.out.println(card);
        }
        System.out.println("Dealer's hand value: " + getHandValue());
    }

    public void printFirstCard() {
        System.out.println(DEALERS_HAND);
        System.out.println(getHand().get(0));
    }

    public void printHandValue() {
        System.out.println("Dealer's hand value: " + getHandValue());
    }

    public void printBusted() {
        System.out.println("Dealer is busted!");
    }

    public void printBlackjack() {
        System.out.println("Dealer has blackjack!");
    }

    public void printFiveCardTrick() {
        System.out.println("Dealer has a five card trick!");
    }

    public void printPush() {
        System.out.println("Dealer has a push!");
    }

    public void printWin() {
        System.out.println("Dealer wins!");
    }

    public void printLose() {
        System.out.println("Dealer loses!");
    }

    public void printStanding() {
        System.out.println("Dealer is standing.");
    }

    public void printDrawingCard() {
        System.out.println("Dealer is drawing a card.");
    }

    public void printClearHand() {
        System.out.println("Dealer's hand is cleared.");
    }

    public void printFirstCardHidden() {
        System.out.println(DEALERS_HAND);
        System.out.println("Hidden card");
        System.out.println(getHand().get(1));
    }


    @Override
    public void reset() {
        super.reset();
    }
}
