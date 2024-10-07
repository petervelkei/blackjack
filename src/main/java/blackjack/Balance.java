package blackjack;

public class Balance {
    private int balance;
    private int currentBet;

    public Balance(int initialBalance) {
        this.balance = initialBalance;
        this.currentBet = 0;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void addBet(int amount) {
        if (balance >= amount) {
            balance -= amount;
            currentBet += amount;
        } else {
            throw new IllegalArgumentException("Not enough balance!");
        }
    }

    public void winBet() {
        balance += currentBet * 2;
        currentBet = 0;
    }

    public void loseBet() {
        currentBet = 0;
    }

    public void resetBet() {
        balance += currentBet;
        currentBet = 0;
    }
}