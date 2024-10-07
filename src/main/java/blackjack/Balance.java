package blackjack;

public class Balance {
    private int totalBalance;
    private int currentBet;

    public Balance(int initialBalance) {
        this.totalBalance = initialBalance;
        this.currentBet = 0;
    }

    public int getBalance() {
        return totalBalance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void addBet(int amount) {
        if (totalBalance >= amount) {
            totalBalance -= amount;
            currentBet += amount;
        } else {
            throw new IllegalArgumentException("Not enough balance!");
        }
    }

    public void winBet() {
        currentBet *= 2;
    }

    public void loseBet() {
        totalBalance -= currentBet;
    }

    public void resetBet() {
        totalBalance += currentBet;
        currentBet = 0;
    }
}