package skeletonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame implements ActionListener{

    private int windowWidth = 1280;
    private int windowHeight = 600;
    private Color background = new Color(229,202,162);
    private Color bcolor = new Color(242,242,242);

    private Game game;

// View-k
    private GamePanel gamePanel;
    private GameView gameView;

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
    private JButton pumpa_be = new JButton("be");
    private JButton pumpa_ki = new JButton("ki");
    private JButton pumpa_fel = new JButton("fel");
    private JButton pumpa_le = new JButton("le");
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
        gamePanel = new GamePanel(this);
        gamePanel.setBackground(background);
        gamePanel.setPreferredSize(new Dimension(windowWidth-100,windowHeight-200));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // playerPanel
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(background);
        playerPanel.setPreferredSize(new Dimension(200,windowWidth-100));

        // buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(150,windowHeight-200));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // pumpPanel
        JPanel pumpPanel = new JPanel();
        pumpPanel.setLayout(new GridLayout(2,2));
        pumpPanel.setBackground(background);
        pumpPanel.setMaximumSize(new Dimension(120,50));


    // ELEMENTS
        // felulre
        for(int i=0; i<3; i++) { menu.add(menuitems[i]); }
        felulre.add(menu, BorderLayout.NORTH);

        felulre.add(gameLabel, BorderLayout.CENTER);

        // gamePanel

        // buttonPanel

        Dimension buttonSize = new Dimension(150,25);
        Dimension bhalf = new Dimension(60,25);
        setButton(javit, buttonSize);
            javit.addActionListener(this);
        setButton(pumpa_be, bhalf);
            pumpa_be.addActionListener(this);
        setButton(pumpa_ki, bhalf);
            pumpa_ki.addActionListener(this);
        setButton(pumpa_fel, bhalf);
            pumpa_fel.addActionListener(this);
        setButton(pumpa_le, bhalf);
            pumpa_le.addActionListener(this);
        setButton(cso_kilyukad, buttonSize);
            cso_kilyukad.addActionListener(this);
        setButton(cso_ragad, buttonSize);
            cso_ragad.addActionListener(this);
        setButton(cso_csuszos, buttonSize);
            cso_csuszos.addActionListener(this);
        setButton(passz, buttonSize);
            passz.addActionListener(this);

        buttonPanel.add(javit);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel("Pumpa"));
        pumpPanel.add(pumpa_be); pumpPanel.add(pumpa_ki); pumpPanel.add(pumpa_fel); pumpPanel.add(pumpa_le);
        buttonPanel.add(pumpPanel);
            buttonPanel.add(new JLabel(" "));

        buttonPanel.add(new JLabel("Cso"));
        buttonPanel.add(cso_kilyukad);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(cso_ragad);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(cso_csuszos);
            buttonPanel.add(new JLabel(" "));
        buttonPanel.add(passz);

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
        this.setResizable(false);

        game = Game.getInstance();

        updateGameLabel();

        actCharacter = game.getActiveCharacter();
        actChar = new String("Jatekos_Szerelo"); // TODO játékosok neveit hogy mentjük el?
        actionP = new String(""+game.getActionPoints());



        init();

        // TODO gombok eltuntetese megfeleloen
        /*
        if(game.getActiveCharNum()%2==0) { cso_csuszos.setVisible(false); }
        else {
            // inventorypanel is setVisible(false)
            javit.setVisible(false);
            pumpa_fel.setVisible(false);
            pumpa_le.setVisible(false);
            cso_csuszos.setVisible(true);
        }
        */
    }

    public GamePanel getGamePanel() {return gamePanel;}

    private void setButton(JButton b, Dimension dim) {
        b.setPreferredSize(dim);
        b.setBackground(bcolor);
    }

    /**Felső sorban az ablakban a játék adatait frissíti*/
    public void updateGameLabel() {
        round = new String(game.getRound()+". kor");
        Ppoints = new String("Szerelok: "+game.getPointsOfPlumber());
        Spoints = new String("Szabotorok: "+game.getPointsOfSaboteur());
        gameLabel.setText("                                               "+round+"     "+Ppoints+"     "+Spoints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(pumpa_be)) {
            this.rePaint();
        }

    }
    public void rePaint() {
        Program.rajz();
    }

// ButtonActionListener-ek


}
