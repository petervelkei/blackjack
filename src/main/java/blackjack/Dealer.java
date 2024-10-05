package blackjack;

public class Dealer extends Player {
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
}