package blackjack;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * A UI osztály a grafikus felhasználói felületet kezeli a Blackjack játékhoz.
 * Ez az osztály felelős a játékos és az osztó kártyáinak megjelenítéséért,
 * a játék állapotának frissítéséért és a felhasználói interakciók kezeléséért.
 */
public class UI {
    private static final String BALANCE_LABEL = "Balance: ";
    private static final String CURRENT_BET_LABEL = "Current Bet: ";
    private static final String FONT_STYLE = "Courier New";
    private final Game game;
    private final Balance balance;

    private JFrame frame;
    private JPanel playerPanel;
    private JPanel dealerPanel;
    
    private JButton drawButton;
    private JButton stayButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JLabel playerScoreLabel;
    private JLabel dealerScoreLabel;
    private JLabel balanceLabel;
    private JLabel currentBetLabel;


    /**
     * Létrehoz egy új UI objektumot a megadott játékhoz.
     *
     * @param game A játék, amelyhez a felhasználói felület tartozik.
     */
    public UI(Game game) {
        this.game = game;
        this.playerPanel = new JPanel();
        this.dealerPanel = new JPanel();
        this.balance = new Balance(2000);
        this.playerScoreLabel = new JLabel();
        this.dealerScoreLabel = new JLabel();
        this.balanceLabel = new JLabel();
        this.currentBetLabel = new JLabel();

    }


    /**
     * Létrehozza és megjeleníti a grafikus felhasználói felületet.
     */
    public void createAndShowGUI() {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.getContentPane().setBackground(new Color(0, 128, 0));
        showBettingView();
    }


    /**
     * Elindítja a játékot és inicializálja a játékhoz szükséges komponenseket.
     * 
     * Ez a metódus eltávolítja a jelenlegi tartalmat a fő keretből, majd létrehozza és inicializálja
     * a játékhoz szükséges paneleket, gombokat és címkéket. Beállítja a gombokhoz tartozó eseménykezelőket,
     * amelyek a játék különböző műveleteit hajtják végre, mint például kártya húzása, megállás, duplázás,
     * osztás, játék újraindítása és visszatérés a főmenübe. A metódus végül hozzáadja a komponenseket
     * a fő kerethez és frissíti a felhasználói felületet.
     */
    public void startGame() {
        // Eltávolítja a jelenlegi tartalmat a fő keretből
        frame.getContentPane().removeAll(); // üres panel
    
        // Létrehozza és inicializálja a paneleket, gombokat és címkéket
        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        drawButton = new JButton("Hit");
        stayButton = new JButton("Stand");
        doubleButton = new JButton("Double");
        splitButton = new JButton("Split");
        JButton resetButton = new JButton("Reset Game");
        JButton backButton = new JButton("Back to Menu");
        playerScoreLabel = new JLabel("Player Score: 0");
        dealerScoreLabel = new JLabel("Dealer Score: 0");
        balanceLabel = new JLabel(BALANCE_LABEL + balance.getBalance());
        currentBetLabel = new JLabel(CURRENT_BET_LABEL + balance.getCurrentBet());
    
        // Beállítja a "Stand" gomb eseménykezelőjét
        stayButton.addActionListener(e -> {
            game.endGame();
            updateUI();
        });
    
        // Beállítja a "Reset Game" gomb eseménykezelőjét
        resetButton.addActionListener(e -> {
            game.restart();
            startGame();
        });
    
        // Beállítja a "Hit" gomb eseménykezelőjét
        drawButton.addActionListener(e -> {
            game.playerDrawCard();
            if (game.isGameOver()) {
                game.endGame();
            }
            updateUI();
        });
        
        // Beállítja a "Double" gomb eseménykezelőjét
        doubleButton.addActionListener(e -> {
            try {
                balance.addBet(balance.getCurrentBet());
                game.playerDrawCard();
                if (!game.isGameOver()) {
                    game.endGame();
                }
                updateUI();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });
    
        // Beállítja a "Split" gomb eseménykezelőjét
        splitButton.addActionListener(e -> {
            try {
                game.split();
                balance.addBet(balance.getCurrentBet());
                updateUI();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });
    
        // Beállítja a "Back to Menu" gomb eseménykezelőjét
        backButton.addActionListener(e -> {
            showBettingView();
            game.restart();
            balance.resetBet();
            updateUI();
        });
    
        // Beállítja a panelek átlátszóságát
        playerPanel.setOpaque(false);
        dealerPanel.setOpaque(false);
    
        // Létrehozza és beállítja a gombokat tartalmazó panelt
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0, 128, 0)); // Zöld szín
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
    
        // Beállítja a gombok és címkék igazítását
        drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doubleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentBetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Hozzáadja a gombokat és címkéket a gomb panelhez
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(drawButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(stayButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(doubleButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(splitButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(resetButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(balanceLabel);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(currentBetLabel);
        buttonPanel.add(Box.createVerticalGlue());
    
        // Hozzáadja a paneleket és címkéket a fő kerethez
        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.add(dealerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(playerScoreLabel, BorderLayout.WEST);
        frame.add(dealerScoreLabel, BorderLayout.EAST);
    
        // Láthatóvá teszi a fő keretet és frissíti a felhasználói felületet
        frame.setVisible(true);
        updateUI();
    }

    /**
     * Megjeleníti a fogadási nézetet, ahol a játékos kiválaszthatja a fogadni kívánt összeget.
     * 
     * Ez a metódus eltávolítja a jelenlegi tartalmat a fő keretből, és hozzáad egy új panelt,
     * amely lehetővé teszi a játékos számára, hogy kiválassza a fogadni kívánt összeget.
     * A panel tartalmazza a játék címét, a játékos egyenlegét, a jelenlegi fogadást, 
     * valamint a különböző zsetonok gombjait, amelyekkel a játékos növelheti a fogadását.
     * A játékos a "Start Game" gombra kattintva indíthatja el a játékot.
     */
    private void showBettingView() {
        // Eltávolítja a jelenlegi tartalmat a fő keretből
        frame.getContentPane().removeAll();
    
        // Létrehozza a fogadási panelt és beállítja a layoutot
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));
        betPanel.setOpaque(false);
    
        // Létrehozza és beállítja a játék címét
        JLabel titleLabel = new JLabel("BLACKJACK");
        titleLabel.setFont(new Font(FONT_STYLE, Font.BOLD, 44));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Létrehozza és beállítja az egyenleg címkét
        balanceLabel = new JLabel(BALANCE_LABEL + balance.getBalance());
        balanceLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 18));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Létrehozza és beállítja a jelenlegi fogadás címkét
        currentBetLabel = new JLabel(CURRENT_BET_LABEL + balance.getCurrentBet());
        currentBetLabel.setFont(new Font(FONT_STYLE, Font.CENTER_BASELINE, 18));
        currentBetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Hozzáadja az elemeket a fogadási panelhez
        betPanel.add(Box.createVerticalGlue());
        betPanel.add(titleLabel);
        betPanel.add(Box.createVerticalStrut(20));
        betPanel.add(balanceLabel);
        betPanel.add(currentBetLabel);
        betPanel.add(Box.createVerticalStrut(10));
    
        // Létrehozza a zsetonok panelt és beállítja a layoutot
        JPanel chipsPanel = new JPanel();
        chipsPanel.setLayout(new BoxLayout(chipsPanel, BoxLayout.X_AXIS));
        chipsPanel.setOpaque(false);
    
        // Hozzáadja a zseton gombokat a zsetonok panelhez
        addChipButton(chipsPanel, "5.png", 5);
        addChipButton(chipsPanel, "10.png", 10);
        addChipButton(chipsPanel, "20.png", 20);
        addChipButton(chipsPanel, "50.png", 50);
        addChipButton(chipsPanel, "100.png", 100);
    
        // Hozzáadja a zsetonok panelt a fogadási panelhez
        betPanel.add(chipsPanel);
        betPanel.add(Box.createVerticalStrut(10));
    
        // Létrehozza és beállítja a "Start Game" gombot
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.addActionListener(e -> startGame());
        betPanel.add(startGameButton);
        betPanel.add(Box.createVerticalGlue());
    
        // Hozzáadja a fogadási panelt a fő kerethez és láthatóvá teszi
        frame.add(betPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }



    /**
     * Hozzáad egy chip gombot a megadott panelhez.
     *
     * @param panel A panel, amelyhez a chip gombot hozzá kell adni.
     * @param imageName A chip képének neve.
     * @param amount A chip értéke.
     */
    private void addChipButton(JPanel panel, String imageName, int amount) {
        try {
            URL imageUrl = getClass().getClassLoader().getResource("chips/" + imageName);
            if (imageUrl != null) {
                BufferedImage originalImage = ImageIO.read(imageUrl);
                Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                JButton chipButton = new JButton(new ImageIcon(scaledImage));
                chipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                chipButton.setBackground(new Color(0, 128, 0));
                chipButton.setBorderPainted(false); // Keret eltávolítása
                chipButton.setFocusPainted(false); // Fókusz keret eltávolítása
                chipButton.setContentAreaFilled(false); // Tartalom terület kitöltésének eltávolítása
                chipButton.setOpaque(true); // Átlátszatlanság beállítása
                chipButton.addActionListener(e -> {
                    addBet(amount);
                    updateBalanceLabels();
                });
                panel.add(Box.createHorizontalStrut(10));
                panel.add(chipButton);
            } else {
                System.err.println("Kép nem található: " + imageName);
            }
        } catch (IOException e) {
            System.err.println("Hiba a kép betöltésekor: " + imageName);
        }
    }


    /**
     * Hozzáad egy fogadást a játékos egyenlegéhez.
     *
     * @param amount A fogadás összege.
     */
    private void addBet(int amount) {
        try {
            balance.addBet(amount);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }


    /**
     * Frissíti az egyenleg és a jelenlegi fogadás címkéit.
     */
    private void updateBalanceLabels() {
        balanceLabel.setText(BALANCE_LABEL + balance.getBalance());
        currentBetLabel.setText(CURRENT_BET_LABEL + balance.getCurrentBet());
    }


    /**
     * Frissíti a felhasználói felületet a játék aktuális állapotának megfelelően.
     */
    public void updateUI() {
        if (playerPanel == null || dealerPanel == null) {
            throw new IllegalArgumentException("Player panel or dealer panel cannot be null");
        }

        updatePanel(playerPanel, game.getPlayer().getHand(), false);
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



    /**
     * Frissíti a megadott panelt a játékos vagy az osztó kezében lévő kártyákkal.
     *
     * @param panel        A frissítendő JPanel.
     * @param hand         A kártyák listája, amelyeket meg kell jeleníteni a panelen.
     * @param hasHiddenCard Ha igaz, akkor az osztó második kártyája lefordítva jelenik meg.
     * @throws IllegalArgumentException ha a panel null értékű.
     */
    private void updatePanel(JPanel panel, List<Card> hand, boolean hasHiddenCard) {
        if (panel == null) {
            throw new IllegalArgumentException("Panel cannot be null");
        }

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


    /**
     * Megjeleníti a játék végének üzenetét, és frissíti a játékos egyenlegét a játék kimenetele alapján.
     *
     * @param message A megjelenítendő üzenet, amely tartalmazza a játék kimenetelét.
     */
    public void showEndGameMessage(String message) {
        drawButton.setEnabled(false);
        stayButton.setEnabled(false);
        doubleButton.setEnabled(false);
        splitButton.setEnabled(false);
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