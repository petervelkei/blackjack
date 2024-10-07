package blackjack;

public class Dealer extends Player {
    private boolean hasHiddenCard = false;

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
        return getHandValue() > other.getHandValue() && !isBusted();
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

    public void addCard(Card card, boolean hidden) {
        if (hidden) {
            hasHiddenCard = true;
        }
        addCard(card);
    }

    public void revealHiddenCard() {
        hasHiddenCard = false;
    }

    public boolean hasHiddenCard() {
        return hasHiddenCard;
    }
}