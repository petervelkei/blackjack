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

    private transient Card rejtettKartya = null; // A rejtett kártya
    public static final JButton hit = new JButton("Hit");
    public static final JButton stay = new JButton("Stay");
    public static final JButton deal = new JButton("Bet");
    public static final JButton doubleB = new JButton("Double");
    public static final JButton splitB = new JButton("Split");

    public static final String SPLIT = "split";
    public static final String BET = "Bet:    ";
    public static final String WEALTH = "Wealth: ";
    public static final String PLAYER = "player";
    public static final String DEALER = "dealer";
    public static final String FONT = "Courier New";

    private final JLayeredPane layeredPanel;
    private final JPanel buttonP;
    private final JPanel p = new JPanel();
    private final JPanel kiosztas = new JPanel();
    private final JPanel fogadasPanel = new JPanel();
    public static final JSlider csuszka = new JSlider();
    private final ArrayList<JLabel> osztoKez = new ArrayList<>();
    private final ArrayList<JLabel> jatekosKez = new ArrayList<>();
    private final ArrayList<JLabel> osztottKez = new ArrayList<>();
    private final JLabel jatekosErtek = new JLabel();
    private final JLabel osztoErtek = new JLabel();
    private final JLabel jatekosOsztottErtek = new JLabel();
    private final JLabel jatekVege = new JLabel();
    private final JLabel penzCimke = new JLabel();
    private final JLabel fogadasCimke = new JLabel();

    /**
     * Konstruktor, amely inicializálja a játék felhasználói felületét.
     *
     * @param list Az ActionListener, amely kezeli a gombnyomásokat
     */
    public GameUI(ActionListener list) {
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(new Color(0, 128, 0));
        layeredPanel = new JLayeredPane();
        layeredPanel.setPreferredSize(new Dimension(1000, 800));
        
        layeredPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Gametable",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        layeredPanel.setBackground(new Color(0, 128, 0));

        hit.addActionListener(list);
        hit.setActionCommand("hit");
        hit.setFont(new Font(FONT, Font.BOLD, 14));
        hit.setEnabled(false);
        hit.setBackground(Color.RED);
        hit.setForeground(Color.BLACK);

        stay.addActionListener(list);
        stay.setActionCommand("stay");
        stay.setFont(new Font(FONT, Font.BOLD, 14));
        stay.setEnabled(false);
        stay.setBackground(Color.RED);
        stay.setForeground(Color.BLACK);

        deal.addActionListener(list);
        deal.setActionCommand("deal");
        deal.setFont(new Font(FONT, Font.BOLD, 14));
        deal.setBackground(Color.RED);
        deal.setForeground(Color.BLACK);

        doubleB.addActionListener(list);
        doubleB.setActionCommand("double");
        doubleB.setFont(new Font(FONT, Font.BOLD, 14));
        doubleB.setEnabled(false);
        doubleB.setBackground(Color.RED);
        doubleB.setForeground(Color.BLACK);

        splitB.addActionListener(list);
        splitB.setActionCommand(SPLIT);
        splitB.setEnabled(false);
        splitB.setBackground(Color.RED);
        splitB.setForeground(Color.BLACK);

        buttonP = new JPanel();
        buttonP.setBackground(new Color(0, 128, 0));
        kiosztas.setPreferredSize(new Dimension(100, 100));
        kiosztas.setBackground(new Color(0, 128, 0));
        buttonP.add(hit);
        buttonP.add(stay);
        buttonP.add(doubleB);
        buttonP.add(splitB);
        
        buttonP.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Actions",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        csuszka.setMajorTickSpacing(100);
        csuszka.setMinorTickSpacing(50);
        csuszka.setPaintTicks(true);
        csuszka.setPaintLabels(true);
        csuszka.setSnapToTicks(true);
        csuszka.setMinimum(100);
        csuszka.setMaximum(1000);
        csuszka.setValue(0);
        fogadasPanel.setLayout(new GridLayout(2, 0));
        
        fogadasPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            "Bets",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font(FONT, Font.BOLD, 18)
        ));

        fogadasPanel.setBackground(new Color(0, 128, 0));
        fogadasPanel.add(csuszka);
        fogadasPanel.add(deal);

        osztoErtek.setBounds(45, 175, 150, 50);
        osztoErtek.setForeground(Color.BLACK);
        osztoErtek.setFont(new Font(FONT, Font.BOLD, 18));
        layeredPanel.add(osztoErtek);
        jatekosErtek.setBounds(45, 595, 150, 25);
        jatekosErtek.setForeground(Color.BLACK);
        jatekosErtek.setFont(new Font(FONT, Font.BOLD, 18));
        layeredPanel.add(jatekosErtek);
        jatekosOsztottErtek.setBounds(480, 580, 150, 50);
        jatekosOsztottErtek.setFont(new Font(FONT, Font.BOLD, 18));
        jatekosOsztottErtek.setForeground(Color.BLACK);
        layeredPanel.add(jatekosOsztottErtek);

        penzCimke.setBounds(660, 30, 200, 40);
        penzCimke.setForeground(Color.BLACK);
        layeredPanel.add(penzCimke);

        fogadasCimke.setBounds(660, 60, 250, 40);
        fogadasCimke.setForeground(Color.BLACK);
        layeredPanel.add(fogadasCimke);

        buttonP.setLayout(new GridLayout(2, 2, 20, 20));

        kiosztas.setLayout(new GridLayout(0, 2));
        kiosztas.add(buttonP);
        kiosztas.add(fogadasPanel);

        p.add(Box.createRigidArea(new Dimension(0, 20)));
        p.add(layeredPanel);
        p.add(Box.createRigidArea(new Dimension(0, 20)));
        p.add(kiosztas);

        jatekVege.setBounds(200, 300, 600, 150);
        jatekVege.setBorder(BorderFactory.createTitledBorder("Game Over: "));
        jatekVege.setFont(new Font(jatekVege.getFont().getFontName(), jatekVege.getFont().getStyle(), 50));
        jatekVege.setForeground(Color.BLACK);
        jatekVege.setVisible(false);
        layeredPanel.add(jatekVege);

        this.add(p);
        this.setTitle("BJ");
        this.setSize(1000, 800);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Kártya kirajzolása a megadott célhelyre.
     *
     * @param k A kirajzolandó kártya
     * @param felhasznalo A célhely (dealer, player, split)
     */
    public void kartyaHuzasUI(Card k, String felhasznalo) {
        ImageIcon kartya;
        JLabel kartyaCimke;
        switch (felhasznalo) {
            case DEALER -> {
                if (osztoKez.size() != 1) {
                    kartya = new ImageIcon(k.getKep());
                    kartya = new ImageIcon(kartya.getImage().getScaledInstance(
                        (int) (kartya.getIconWidth() * 0.2), (int) (kartya.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                } else {
                    rejtettKartya = k;
                    kartya = new ImageIcon("build/kartyas/b1fv.png");
                    kartya = new ImageIcon(kartya.getImage().getScaledInstance(
                        (int) (kartya.getIconWidth() * 1.52), (int) (kartya.getIconHeight() * 1.52), java.awt.Image.SCALE_SMOOTH));
                }
                kartyaCimke = new JLabel(kartya);
                kartyaCimke.setBounds(20 + 37 * osztoKez.size(), 40, kartya.getIconWidth(), kartya.getIconHeight());
                osztoKez.add(kartyaCimke);
                layeredPanel.add(kartyaCimke, Integer.valueOf(osztoKez.size()));
            }
            case PLAYER -> {
                kartya = new ImageIcon(k.getKep());
                kartya = new ImageIcon(kartya.getImage().getScaledInstance(
                    (int) (kartya.getIconWidth() * 0.2), (int) (kartya.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                kartyaCimke = new JLabel(kartya);
                kartyaCimke.setBounds(20 + 37 * jatekosKez.size(), 450, kartya.getIconWidth(), kartya.getIconHeight());
                layeredPanel.add(kartyaCimke, Integer.valueOf(jatekosKez.size()));
                jatekosKez.add(kartyaCimke);
            }
            case SPLIT -> {
                kartya = new ImageIcon(k.getKep());
                kartya = new ImageIcon(kartya.getImage().getScaledInstance(
                    (int) (kartya.getIconWidth() * 0.2), (int) (kartya.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
                kartyaCimke = new JLabel(kartya);
                kartyaCimke.setBounds(460 + 37 * osztottKez.size(), 450, kartya.getIconWidth(), kartya.getIconHeight());
                layeredPanel.add(kartyaCimke, Integer.valueOf(osztottKez.size()));
                osztottKez.add(kartyaCimke);
            }
            default -> {
                //
            }
        }
    }

    /**
     * Gombok engedélyezése vagy letiltása a megadott parancs alapján.
     *
     * @param parancs A parancs, amely alapján a gombokat engedélyezni vagy letiltani kell
     */
    public void gombokatEngedelyez(String parancs) {
        switch (parancs) {
            case "deal" -> {
                GameUI.csuszka.setEnabled(false);
                GameUI.deal.setEnabled(false);
                GameUI.stay.setEnabled(true);
                GameUI.hit.setEnabled(true);
                GameUI.doubleB.setEnabled(true);
            }
            case "stay" -> {
                GameUI.splitB.setEnabled(false);
                GameUI.hit.setEnabled(false);
                GameUI.stay.setEnabled(false);
                GameUI.deal.setEnabled(true);
                GameUI.csuszka.setEnabled(true);
                GameUI.doubleB.setEnabled(false);
            }
            case SPLIT -> {
                GameUI.splitB.setEnabled(false);
                GameUI.doubleB.setEnabled(false);
            }
            case "bust" -> {
                GameUI.hit.setEnabled(false);
                GameUI.stay.setEnabled(false);
                GameUI.deal.setEnabled(true);
                GameUI.csuszka.setEnabled(true);
            }
            case "hit" -> {
                GameUI.doubleB.setEnabled(false);
                GameUI.splitB.setEnabled(false);
            }
            case "double" -> {
                GameUI.hit.setEnabled(false);
                GameUI.stay.setEnabled(false);
                GameUI.doubleB.setEnabled(false);
            }
            default -> throw new IllegalArgumentException("Invalid parancs: " + parancs);
        }
    }

    /**
     * Frissíti a kéz értékét a felhasználói felületen.
     *
     * @param tulaj A kéz tulajdonosa
     * @param ertek A kéz értéke
     */
    public void updateKezErtek(String tulaj, String ertek) {
        switch (tulaj) {
            case DEALER -> osztoErtek.setText(ertek);
            case PLAYER -> jatekosErtek.setText(ertek);
            case SPLIT -> jatekosOsztottErtek.setText(ertek);
            default -> throw new IllegalArgumentException("Invalid tulaj: " + tulaj);
        }
    }

    /**
     * Törli a kártyákat a felhasználói felületről.
     */
    /**
     * Törli az összes kártyát a felhasználói felületről.
     */
    public void torol() {
        for (JLabel jLabel : osztoKez) {
            layeredPanel.remove(jLabel);
        }
        for (JLabel jLabel : jatekosKez) {
            layeredPanel.remove(jLabel);
        }
        for (JLabel jLabel : osztottKez) {
            layeredPanel.remove(jLabel);
        }
        osztoKez.clear();
        jatekosKez.clear();
        osztottKez.clear();
        osztoErtek.setText("");
        jatekosOsztottErtek.setText("");
        jatekVege.setVisible(false);
        this.repaint();
    }
    
    /**
     * Törli a játékos kártyáit a felhasználói felületről.
     */
    public void torolJatekos() {
        for (JLabel jLabel : jatekosKez) {
            layeredPanel.remove(jLabel);
        }
        jatekosKez.clear();
        this.repaint();
    }

    /**
     * Letiltja a kéz kártyáit a felhasználói felületen.
     *
     * @param tulaj A kéz tulajdonosa
     */
    public void kezTiltas(String tulaj) {
        switch (tulaj) {
            case PLAYER -> {
                for (JLabel jLabel : jatekosKez) {
                    jLabel.setEnabled(false);
                }
                this.repaint();
            }
            case SPLIT -> {
                for (JLabel jLabel : osztottKez) {
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
     * @param tulaj A kéz tulajdonosa
     */
    public void kezEngedelyez(String tulaj) {
        switch (tulaj) {
            case PLAYER -> {
                for (JLabel jLabel : jatekosKez) {
                    jLabel.setEnabled(true);
                }
                this.repaint();
            }
            case SPLIT -> {
                for (JLabel jLabel : osztottKez) {
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
    public void felfedRejtettKartya() {
        if (!osztoKez.isEmpty()) {
            layeredPanel.remove(osztoKez.get(1));
            osztoKez.remove(1);
            ImageIcon kartya = new ImageIcon(rejtettKartya.getKep());
            kartya = new ImageIcon(kartya.getImage().getScaledInstance(
                (int) (kartya.getIconWidth() * 0.2), (int) (kartya.getIconHeight() * 0.2), java.awt.Image.SCALE_SMOOTH));
            JLabel kartyaCimke = new JLabel(kartya);
            kartyaCimke.setBounds(20 + 37, 40, kartya.getIconWidth(), kartya.getIconHeight());
            osztoKez.add(1, kartyaCimke);
            layeredPanel.add(kartyaCimke, Integer.valueOf(2));
        }
    }

    /**
     * Megjeleníti a játék végét jelző üzenetet.
     *
     * @param text A megjelenítendő üzenet
     */
    public void vege(String text) {
        jatekVege.setText(text);
        jatekVege.setVisible(true);
    }

    /**
     * Frissíti a fogadások és a játékos vagyonának értékét a felhasználói felületen.
     *
     * @param vagyon A játékos aktuális vagyona
     * @param fogadas A játékos aktuális tétje
     */
    public void tetFrissites(int vagyon, int fogadas) {
        penzCimke.setText(GameUI.WEALTH + vagyon + "€");
        penzCimke.setFont(new Font(FONT, Font.BOLD, 20));
        fogadasCimke.setText(BET + fogadas + "€");
        fogadasCimke.setFont(new Font(FONT, Font.BOLD, 20));
    }
}