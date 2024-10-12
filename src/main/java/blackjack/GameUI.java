package blackjack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

/**
 * A GameUI osztály a játék grafikus felhasználói felületét (GUI) kezeli.
 */
public class GameUI extends JFrame {

    private transient Card hidden = null; // A rejtett kártya
    public static final JButton hitButton = new JButton("Hit Me");
    public static final JButton stayButton = new JButton("Stay");
    public static final JButton dealButton = new JButton("Deal");
    public static final JButton doubleButton = new JButton("Double");
    public static final JButton splitButton = new JButton("Split");

    public static final String SPLIT = "split";
    public static final String BET = "Bet:    ";
    public static final String WEALTH = "Wealth: ";
    public static final String PLAYER = "player";
    public static final String DEALER = "dealer";
    public static final String FONT = "Courier New";

    private final JLayeredPane layeredPane;
    private final JPanel buttonPanel;
    private final JPanel panel = new JPanel();
    private final JPanel controls = new JPanel();
    private final JPanel bets = new JPanel();
    public static final JSlider betslider = new JSlider();
    private ArrayList<JLabel> dealerHand = new ArrayList<>();
    private ArrayList<JLabel> playerHand = new ArrayList<>();
    private ArrayList<JLabel> splitHand = new ArrayList<>();
    private final JLabel playerAmount = new JLabel();
    private final JLabel dealerAmount = new JLabel();
    private final JLabel splitAmount = new JLabel();
    private final JLabel gameOver = new JLabel();
    private final JLabel moneyLabel = new JLabel();
    private final JLabel betLabel = new JLabel();

    /**
     * Konstruktor, amely inicializálja a játék felhasználói felületét.
     *
     * @param list Az ActionListener, amely kezeli a gombnyomásokat
     */
    public GameUI(ActionListener list) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(0, 128, 0));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 800));
        
        layeredPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Gametable",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        layeredPane.setBackground(new Color(0, 128, 0));

        hitButton.addActionListener(list);
        hitButton.setActionCommand("hit");
        hitButton.setFont(new Font(FONT, Font.BOLD, 14));
        hitButton.setEnabled(false);
        hitButton.setBackground(Color.RED);
        hitButton.setForeground(Color.BLACK);

        stayButton.addActionListener(list);
        stayButton.setActionCommand("stay");
        stayButton.setFont(new Font(FONT, Font.BOLD, 14));
        stayButton.setEnabled(false);
        stayButton.setBackground(Color.RED);
        stayButton.setForeground(Color.BLACK);

        dealButton.addActionListener(list);
        dealButton.setActionCommand("deal");
        dealButton.setFont(new Font(FONT, Font.BOLD, 14));
        dealButton.setBackground(Color.RED);
        dealButton.setForeground(Color.BLACK);

        doubleButton.addActionListener(list);
        doubleButton.setActionCommand("double");
        doubleButton.setFont(new Font(FONT, Font.BOLD, 14));
        doubleButton.setEnabled(false);
        doubleButton.setBackground(Color.RED);
        doubleButton.setForeground(Color.BLACK);

        splitButton.addActionListener(list);
        splitButton.setActionCommand(SPLIT);
        splitButton.setEnabled(false);
        splitButton.setBackground(Color.RED);
        splitButton.setForeground(Color.BLACK);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 128, 0));
        controls.setPreferredSize(new Dimension(100, 100));
        controls.setBackground(new Color(0, 128, 0));
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);
        
        buttonPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Actions",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        betslider.setMajorTickSpacing(50);
        betslider.setMinorTickSpacing(25);
        betslider.setPaintTicks(true);
        betslider.setPaintLabels(true);
        betslider.setSnapToTicks(true);
        betslider.setMinimum(50);
        betslider.setMaximum(500);
        betslider.setValue(50);
        bets.setLayout(new GridLayout(2, 0));
        
        bets.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Bets",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        bets.setBackground(new Color(0, 128, 0));
        bets.add(betslider);
        bets.add(dealButton);

        dealerAmount.setBounds(45, 175, 150, 50);
        dealerAmount.setForeground(Color.BLACK);
        dealerAmount.setFont(new Font(FONT, Font.BOLD, 18));
        layeredPane.add(dealerAmount);
        playerAmount.setBounds(45, 595, 150, 25);
        playerAmount.setForeground(Color.BLACK);
        playerAmount.setFont(new Font(FONT, Font.BOLD, 18));
        layeredPane.add(playerAmount);
        splitAmount.setBounds(480, 580, 150, 50);
        splitAmount.setFont(new Font(FONT, Font.BOLD, 18));
        splitAmount.setForeground(Color.BLACK);
        layeredPane.add(splitAmount);

        moneyLabel.setBounds(660, 30, 200, 40);
        moneyLabel.setForeground(Color.BLACK);
        layeredPane.add(moneyLabel);

        betLabel.setBounds(660, 60, 250, 40);
        betLabel.setForeground(Color.BLACK);
        layeredPane.add(betLabel);

        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));

        controls.setLayout(new GridLayout(0, 2));
        controls.add(buttonPanel);
        controls.add(bets);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(layeredPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(controls);

        gameOver.setBounds(200, 300, 600, 150);
        gameOver.setBorder(BorderFactory.createTitledBorder("Game Over:"));
        gameOver.setFont(new Font(gameOver.getFont().getFontName(), gameOver.getFont().getStyle(), 50));
        gameOver.setForeground(Color.BLACK);
        gameOver.setVisible(false);
        layeredPane.add(gameOver);

        this.add(panel);
        this.setTitle("BlackJack");
        this.setSize(1000, 800);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Kártya kirajzolása a megadott célhelyre.
     *
     * @param c A kirajzolandó kártya
     * @param dest A célhely (dealer, player, split)
     */
    public void drawCard(Card c, String dest) {
        ImageIcon card;
        JLabel cardLabel;
        switch (dest) {
            case DEALER -> {
                if (dealerHand.size() != 1) {
                    card = new ImageIcon(c.getImage());
                    card = new ImageIcon(card.getImage().getScaledInstance(
                        (int) (card.getIconWidth() * 0.2), (int) (card.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                } else {
                    hidden = c;
                    card = new ImageIcon("build/cards/b1fv.png");
                    card = new ImageIcon(card.getImage().getScaledInstance(
                        (int) (card.getIconWidth() * 1.52), (int) (card.getIconHeight() * 1.52), java.awt.Image.SCALE_SMOOTH));
                }
                cardLabel = new JLabel(card);
                cardLabel.setBounds(20 + 37 * dealerHand.size(), 40, card.getIconWidth(), card.getIconHeight());
                dealerHand.add(cardLabel);
                layeredPane.add(cardLabel, Integer.valueOf(dealerHand.size()));
            }
            case PLAYER -> {
                card = new ImageIcon(c.getImage());
                card = new ImageIcon(card.getImage().getScaledInstance(
                    (int) (card.getIconWidth() * 0.2), (int) (card.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                cardLabel = new JLabel(card);
                cardLabel.setBounds(20 + 37 * playerHand.size(), 450, card.getIconWidth(), card.getIconHeight());
                layeredPane.add(cardLabel, Integer.valueOf(playerHand.size()));
                playerHand.add(cardLabel);
            }
            case SPLIT -> {
                card = new ImageIcon(c.getImage());
                card = new ImageIcon(card.getImage().getScaledInstance(
                    (int) (card.getIconWidth() * 0.2), (int) (card.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                cardLabel = new JLabel(card);
                cardLabel.setBounds(460 + 37 * splitHand.size(), 450, card.getIconWidth(), card.getIconHeight());
                layeredPane.add(cardLabel, Integer.valueOf(splitHand.size()));
                splitHand.add(cardLabel);
            }
            default -> {
                //
            }
        }
    }

    /**
     * Gombok engedélyezése vagy letiltása a megadott parancs alapján.
     *
     * @param command A parancs, amely alapján a gombokat engedélyezni vagy letiltani kell
     */
    public void setEnableButton(String command) {
        switch (command) {
            case "deal" -> {
                GameUI.betslider.setEnabled(false);
                GameUI.dealButton.setEnabled(false);
                GameUI.stayButton.setEnabled(true);
                GameUI.hitButton.setEnabled(true);
                GameUI.doubleButton.setEnabled(true);
            }
            case "stay" -> {
                GameUI.splitButton.setEnabled(false);
                GameUI.hitButton.setEnabled(false);
                GameUI.stayButton.setEnabled(false);
                GameUI.dealButton.setEnabled(true);
                GameUI.betslider.setEnabled(true);
                GameUI.doubleButton.setEnabled(false);
            }
            case SPLIT -> {
                GameUI.splitButton.setEnabled(false);
                GameUI.doubleButton.setEnabled(false);
            }
            case "bust" -> {
                GameUI.hitButton.setEnabled(false);
                GameUI.stayButton.setEnabled(false);
                GameUI.dealButton.setEnabled(true);
                GameUI.betslider.setEnabled(true);
            }
            case "hit" -> {
                GameUI.doubleButton.setEnabled(false);
                GameUI.splitButton.setEnabled(false);
            }
            case "double" -> {
                GameUI.hitButton.setEnabled(false);
                GameUI.stayButton.setEnabled(false);
                GameUI.doubleButton.setEnabled(false);
            }
            default -> throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    /**
     * Frissíti a kéz értékét a felhasználói felületen.
     *
     * @param owner A kéz tulajdonosa
     * @param value A kéz értéke
     */
    public void updateHandValue(String owner, String value) {
        switch (owner) {
            case DEALER -> dealerAmount.setText(value);
            case PLAYER -> playerAmount.setText(value);
            case SPLIT -> splitAmount.setText(value);
            default -> throw new IllegalArgumentException("Invalid owner: " + owner);
        }
    }

    /**
     * Törli a kártyákat a felhasználói felületről.
     */
    public void clear() {
        for (JLabel jLabel : dealerHand) {
            layeredPane.remove(jLabel);
        }
        for (JLabel jLabel : playerHand) {
            layeredPane.remove(jLabel);
        }
        for (JLabel jLabel : splitHand) {
            layeredPane.remove(jLabel);
        }
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        splitHand = new ArrayList<>();
        dealerAmount.setText("");
        splitAmount.setText("");
        gameOver.setVisible(false);
        this.repaint();
    }

    /**
     * Törli a játékos kártyáit a felhasználói felületről.
     */
    public void clearPlayer() {
        for (JLabel jLabel : playerHand) {
            layeredPane.remove(jLabel);
        }
        playerHand = new ArrayList<>();
        this.repaint();
    }

    /**
     * Letiltja a kéz kártyáit a felhasználói felületen.
     *
     * @param owner A kéz tulajdonosa
     */
    public void disableHand(String owner) {
        switch (owner) {
            case PLAYER -> {
                for (JLabel jLabel : playerHand) {
                    jLabel.setEnabled(false);
                }
                this.repaint();
            }
            case SPLIT -> {
                for (JLabel jLabel : splitHand) {
                    jLabel.setEnabled(false);
                }
                this.repaint();
            }
            default -> {
                //
            }
        }
    }

    /**
     * Engedélyezi a kéz kártyáit a felhasználói felületen.
     *
     * @param owner A kéz tulajdonosa
     */
    public void enableHand(String owner) {
        switch (owner) {
            case PLAYER -> {
                for (JLabel jLabel : playerHand) {
                    jLabel.setEnabled(true);
                }
                this.repaint();
            }
            case SPLIT -> {
                for (JLabel jLabel : splitHand) {
                    jLabel.setEnabled(true);
                }
                this.repaint();
            }
            default -> {
                //
            }
        }
    }

    /**
     * Felfedi az osztó rejtett kártyáját.
     */
    public void revealDealerCard() {
        if (dealerHand.isEmpty()) {
            return;
        }
        layeredPane.remove(dealerHand.get(1));
        dealerHand.remove(1);
        ImageIcon card = new ImageIcon(hidden.getImage());
        card = new ImageIcon(card.getImage().getScaledInstance(
            (int) (card.getIconWidth() * 0.2), (int) (card.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel = new JLabel(card);
        cardLabel.setBounds(20 + 37, 40, card.getIconWidth(), card.getIconHeight());
        dealerHand.add(1, cardLabel);
        layeredPane.add(cardLabel, Integer.valueOf(2));
    }

    /**
     * Megjeleníti a játék végét jelző üzenetet.
     *
     * @param text A megjelenítendő üzenet
     */
    public void gameOver(String text) {
        gameOver.setText(text);
        gameOver.setVisible(true);
    }

    /**
     * Frissíti a fogadások és a játékos vagyonának értékét a felhasználói felületen.
     *
     * @param wealthAmount A játékos aktuális vagyona
     * @param betAmount A játékos aktuális tétje
     */
    public void updateBets(int wealthAmount, int betAmount) {
        moneyLabel.setText(GameUI.WEALTH + wealthAmount + "€");
        moneyLabel.setFont(new Font(FONT, Font.BOLD, 20));
        betLabel.setText(BET + betAmount + "€");
        betLabel.setFont(new Font(FONT, Font.BOLD, 20));
    }
}