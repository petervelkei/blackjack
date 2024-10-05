package blackjack;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UI {
    private Game game;
    private JFrame frame;
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JButton drawButton;
    private JButton stayButton;
    private JButton restartButton;
    private JLabel playerScoreLabel;
    private JLabel dealerScoreLabel;

    public UI(Game game) {
        this.game = game;
    }

    public void createAndShowGUI() {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Háttér szín beállítása
        frame.getContentPane().setBackground(new Color(0, 128, 0)); // Zöld szín

        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        drawButton = new JButton("Draw Card");
        stayButton = new JButton("Stay");
        restartButton = new JButton("Restart");
        playerScoreLabel = new JLabel("Player Score: 0");
        dealerScoreLabel = new JLabel("Dealer Score: 0");

        drawButton.addActionListener(e -> {
            game.playerDrawCard();
            game.dealerDrawCard();
            updateUI();
            if (game.isGameOver()) {
                endGame();
            }
        });

        stayButton.addActionListener(e -> {
            while (game.calculatePoints(game.getDealer().getHand()) < 17) {
                game.dealerDrawCard();
            }
            updateUI();
            determineWinner();
        });

        restartButton.addActionListener(e -> {
            game.restart();
            drawButton.setEnabled(true);
            stayButton.setEnabled(true);
            updateUI();
        });

        playerPanel.setOpaque(false);
        dealerPanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(drawButton);
        buttonPanel.add(stayButton);
        buttonPanel.add(restartButton);

        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.add(dealerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(playerScoreLabel, BorderLayout.WEST);
        frame.add(dealerScoreLabel, BorderLayout.EAST);

        frame.setVisible(true);
        updateUI();
    }

    public void updateUI() {
        updatePanel(playerPanel, game.getPlayer().getHand());
        updatePanel(dealerPanel, game.getDealer().getHand());
        playerScoreLabel.setText("Player Score: " + game.calculatePoints(game.getPlayer().getHand()));
        dealerScoreLabel.setText("Dealer Score: " + game.calculatePoints(game.getDealer().getHand()));
    }

    private void updatePanel(JPanel panel, List<Card> hand) {
        panel.removeAll();
        for (Card card : hand) {
            URL imageUrl = getClass().getClassLoader().getResource(card.getImagePath());
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

    private void endGame() {
        drawButton.setEnabled(false);
        stayButton.setEnabled(false);
        int playerPoints = game.calculatePoints(game.getPlayer().getHand());
        int dealerPoints = game.calculatePoints(game.getDealer().getHand());
        String message;
        if (playerPoints > 21) {
            message = "Player busts! Dealer wins!";
        } else if (dealerPoints > 21) {
            message = "Dealer busts! Player wins!";
        } else {
            message = "Game over!";
        }
        JOptionPane.showMessageDialog(frame, message);
    }

    private void determineWinner() {
        drawButton.setEnabled(false);
        stayButton.setEnabled(false);
        int playerPoints = game.calculatePoints(game.getPlayer().getHand());
        int dealerPoints = game.calculatePoints(game.getDealer().getHand());
        String message;
        if (playerPoints > dealerPoints && playerPoints <= 21) {
            message = "Player wins!";
        } else if (dealerPoints > playerPoints && dealerPoints <= 21) {
            message = "Dealer wins!";
        } else if (playerPoints == dealerPoints) {
            message = "It's a tie!";
        } else if (playerPoints > 21) {
            message = "Player busts! Dealer wins!";
        } else {
            message = "Dealer busts! Player wins!";
        }
        JOptionPane.showMessageDialog(frame, message);
    }
}