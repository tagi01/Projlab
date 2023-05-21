package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private int windowWidth = 1280;
    private int windowHeight = 600;
    private Color background = new Color(229,202,162);
    private Color bcolor = new Color(242,242,242);

    private Game game;

    // felulre
    private JMenuBar menu = new JMenuBar();
    private JMenuItem[] menuitems = {
            new JMenuItem("Uj jatek"),
            new JMenuItem("Mentes"),
            new JMenuItem("Betoltes")
    };

    private JLabel gameLabel = new JLabel();
    private String round, Ppoints, Spoints;

    // gamePanel

    // playerPanel
    private String actChar, actionP;
    private Character actCharacter;

    // buttonPanel
    private JButton javit = new JButton("javitas");
    private JButton pumpa_be = new JButton("bemenet");
    private JButton pumpa_ki = new JButton("kimenet");
    private JButton pumpa_fel = new JButton("felvetel");
    private JButton pumpa_le = new JButton("lerakas");
    private JButton cso_kilyukad = new JButton("kilyukasztas");
    private JButton cso_ragad = new JButton("ragados legyen");
    private JButton cso_csuszos = new JButton("csuszos legyen");
    private JButton passz = new JButton("passz");


    private void init() {

    // PANELS
        // felulre
        JPanel felulre = new JPanel();
        felulre.setBackground(background);
        felulre.setLayout(new BorderLayout());
        felulre.setPreferredSize(new Dimension(windowWidth,50));

        // gamePanel
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(background);
        gamePanel.setPreferredSize(new Dimension(windowWidth-100,windowHeight-200));

        // playerPanel
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(bcolor);
        playerPanel.setPreferredSize(new Dimension(200,windowWidth-100));

        // buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(150,windowHeight-200));


    // ELEMENTS
        // felulre
        for(int i=0; i<3; i++) { menu.add(menuitems[i]); }
        felulre.add(menu, BorderLayout.NORTH);

        felulre.add(gameLabel, BorderLayout.CENTER);

        // gamePanel


        // buttonPanel

        Dimension buttonSize = new Dimension(150,25);
        Dimension bhalf = new Dimension(70,25);
        setButton(javit, buttonSize);
        setButton(pumpa_be, bhalf);
        setButton(pumpa_ki, bhalf);
        setButton(pumpa_fel, bhalf);
        setButton(pumpa_le, bhalf);
        setButton(cso_kilyukad, buttonSize);
        setButton(cso_ragad, buttonSize);
        setButton(cso_csuszos, buttonSize);
        setButton(passz, buttonSize);

        buttonPanel.add(javit);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel("Pumpa"));
        buttonPanel.add(pumpa_be);
        buttonPanel.add(pumpa_ki);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(pumpa_fel);
        buttonPanel.add(pumpa_le);
            buttonPanel.add(new JLabel(" "));

        buttonPanel.add(new JLabel("Cso"));
        buttonPanel.add(cso_kilyukad);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(cso_ragad);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(cso_csuszos);


        // TODO ide a Pumpa es Cso jlabel-ok

        // playerPanel
            // TODO jatekos kepet hozzaadni
        playerPanel.add(new Label(actChar+"     "+actionP));
        playerPanel.add(new Label("\n"));
        playerPanel.add(buttonPanel, BorderLayout.CENTER);

    // ACTIONLISTENERS

    // ADD TO FRAME
        add(felulre, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.EAST);
    }

    public GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Sivatagi vizhalozat");
        this.setSize(windowWidth,windowHeight);
        // this.setResizable(false);

        game = Game.getInstance();

        round = new String(game.getRound()+". kor");
        Ppoints = new String("Szerelok: "+game.getPointsOfPlumber());
        Spoints = new String("Szabotorok: "+game.getPointsOfSaboteur());
        gameLabel.setText("                                               "+round+"     "+Ppoints+"     "+Spoints);

        actCharacter = game.getActiveCharacter();
        actChar = new String("Jatekos_Szerelo"); // TODO játékosok neveit hogy mentjük el?
        actionP = new String(""+game.getActionPoints());



        init();

    }

    private void setButton(JButton b, Dimension dim) {
        b.setMaximumSize(dim);
        b.setBackground(bcolor);
    }

// ButtonActionListener-ek


}
