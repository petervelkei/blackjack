package blackjack;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UI {
    private static final String BALANCE_LABEL = "Balance: ";
    private static final String CURRENT_BET_LABEL = "Current Bet: ";
    private static final String FONT_STYLE = "Courier New";
    private final Game game;
    private final Balance balance;

    private JFrame frame;
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JPanel betPanel;

    private JButton drawButton;
    private JButton stayButton;
    private JButton startGameButton;
    private JButton backButton;
    private JButton resetButton;
    private JLabel playerScoreLabel;
    private JLabel dealerScoreLabel;
    private JLabel balanceLabel;
    private JLabel currentBetLabel;

    public UI(Game game) {
        this.game = game;
        this.balance = new Balance(2000);
    }

    public void createAndShowGUI() {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(0, 128, 0));
        showBettingView();
    }

    public void startGame() {
        frame.getContentPane().removeAll(); // üres panel
    
        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        drawButton = new JButton("Hit");
        stayButton = new JButton("Stand");
        resetButton = new JButton("Reset Game");
        backButton = new JButton("Back to Menu");
        playerScoreLabel = new JLabel("Player Score: 0");
        dealerScoreLabel = new JLabel("Dealer Score: 0");
        balanceLabel = new JLabel(BALANCE_LABEL + balance.getBalance());
        currentBetLabel = new JLabel(CURRENT_BET_LABEL + balance.getCurrentBet());
    
        stayButton.addActionListener(e -> {
            game.endGame();
            updateUI();
        });
    
        resetButton.addActionListener(e -> {
            game.restart();
            balance.resetBet();
            updateUI();
        });
    
        drawButton.addActionListener(e -> {
            game.playerDrawCard();
            if (game.isGameOver()) {
                game.endGame();
            }
            updateUI();
        });
    
        backButton.addActionListener(e -> {
            showBettingView();
            game.restart();
            balance.resetBet();
            updateUI();
        });
    
        playerPanel.setOpaque(false);
        dealerPanel.setOpaque(false);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0, 128, 0)); // Zöld szín
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    
        drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentBetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(drawButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(stayButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(resetButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(balanceLabel);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(currentBetLabel);
        buttonPanel.add(Box.createVerticalGlue());
    
        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.add(dealerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(playerScoreLabel, BorderLayout.WEST);
        frame.add(dealerScoreLabel, BorderLayout.EAST);
    
        frame.setVisible(true);
        updateUI();
    }

    private void showBettingView() {
        frame.getContentPane().removeAll();

        betPanel = new JPanel();
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));
        betPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("BLACKJACK");
        titleLabel.setFont(new Font(FONT_STYLE, Font.BOLD, 44));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        balanceLabel = new JLabel(BALANCE_LABEL + balance.getBalance());
        balanceLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 18));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        currentBetLabel = new JLabel(CURRENT_BET_LABEL + balance.getCurrentBet());
        currentBetLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 18));
        currentBetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton bet5Button = new JButton("Bet 5");
        JButton bet10Button = new JButton("Bet 10");
        JButton bet25Button = new JButton("Bet 25");
        JButton bet50Button = new JButton("Bet 50");
        JButton bet100Button = new JButton("Bet 100");
        startGameButton = new JButton("Start Game");

        bet5Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        bet10Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        bet25Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        bet50Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        bet100Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        bet5Button.addActionListener(e -> addBet(5));
        bet10Button.addActionListener(e -> addBet(10));
        bet25Button.addActionListener(e -> addBet(25));
        bet50Button.addActionListener(e -> addBet(50));
        bet100Button.addActionListener(e -> addBet(100));
        startGameButton.addActionListener(e -> startGame());

        betPanel.add(Box.createVerticalGlue());
        betPanel.add(titleLabel);
        betPanel.add(Box.createVerticalStrut(20));
        betPanel.add(balanceLabel);
        betPanel.add(currentBetLabel);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(bet5Button);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(bet10Button);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(bet25Button);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(bet50Button);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(bet100Button);
        betPanel.add(Box.createVerticalStrut(10));
        betPanel.add(startGameButton);
        betPanel.add(Box.createVerticalGlue());

        frame.add(betPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addBet(int amount) {
        try {
            balance.addBet(amount);
            balanceLabel.setText(BALANCE_LABEL + balance.getBalance());
            currentBetLabel.setText(CURRENT_BET_LABEL + balance.getCurrentBet());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    public void updateUI() {
        updatePanel(playerPanel, game.getPlayer().getHand());
        updatePanel(dealerPanel, game.getDealer().getHand(), game.getDealer().hasHiddenCard());
    
        playerScoreLabel.setText("Player Score: " + game.calculatePoints(game.getPlayer().getHand()));
        playerScoreLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 16));
    
        if (game.getDealer().hasHiddenCard()) {
            // Csak az első lap pontszámát jelenítjük meg
            List<Card> dealerVisibleHand = List.of(game.getDealer().getHand().get(0));
            dealerScoreLabel.setText("Dealer Score: " + game.calculatePoints(dealerVisibleHand));
            dealerScoreLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 16));
        } else {
            // Az összes lap pontszámát jelenítjük meg
            dealerScoreLabel.setText("Dealer Score: " + game.calculatePoints(game.getDealer().getHand()));
            dealerScoreLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 16));
        }

        balanceLabel.setText(BALANCE_LABEL + balance.getBalance());
        balanceLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 16));
        currentBetLabel.setText(CURRENT_BET_LABEL + balance.getCurrentBet());
        currentBetLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 16));
    }

    private void updatePanel(JPanel panel, List<Card> hand) {
        updatePanel(panel, hand, false);
    }

    private void updatePanel(JPanel panel, List<Card> hand, boolean hasHiddenCard) {
        panel.removeAll();
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            URL imageUrl;
            if (hasHiddenCard && i == 1) {
                imageUrl = getClass().getClassLoader().getResource("cards/back.jpeg");
            } else {
                imageUrl = getClass().getClassLoader().getResource(card.getImagePath());
            }
            if (imageUrl != null) {
                try {
                    BufferedImage originalImage = ImageIO.read(imageUrl);
                    Image scaledImage = originalImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                    JLabel label = new JLabel(new ImageIcon(scaledImage));
                    panel.add(label);
                } catch (IOException e) {
                    System.err.println("Hiba a kép betöltésekor: " + card.getImagePath());
                }
            } else {
                System.err.println("Kép nem található: " + card.getImagePath());
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    public void showEndGameMessage(String message) {
        drawButton.setEnabled(false);
        stayButton.setEnabled(false);
        JOptionPane.showMessageDialog(frame, message);

        if (message.contains("Player wins!")) {
            balance.winBet();
        } else if (message.contains("Dealer wins!")) {
            balance.loseBet();
        }

        balanceLabel.setText(BALANCE_LABEL + balance.getBalance());
        currentBetLabel.setText(CURRENT_BET_LABEL + balance.getCurrentBet());
    }
}