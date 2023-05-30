package skeletonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame{

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
        JPanel playerPanel = new PlayerPanelView();

        // ELEMENTS
        // felulre
        for(int i=0; i<3; i++) { menu.add(menuitems[i]); }
        felulre.add(menu, BorderLayout.NORTH);

        felulre.add(gameLabel, BorderLayout.CENTER);

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

        init();
    }

    public GamePanel getGamePanel() {return gamePanel;}
    
    public Game getGame() { return game; }

    /**Felső sorban az ablakban a játék adatait frissíti*/
    public void updateGameLabel() {
        round = new String(game.getRound()+". kor");
        Ppoints = new String("Szerelok: "+game.getPointsOfPlumber());
        Spoints = new String("Szabotorok: "+game.getPointsOfSaboteur());
        gameLabel.setText("                                               "+round+"     "+Ppoints+"     "+Spoints);
    }

    //@Override
    //public void actionPerformed(ActionEvent e) {
    //if(e.getSource().equals(pumpa_be)) {
    //this.rePaint();
    //}

    //}
    /*public void rePaint() {
        Program.rajz();
    }*/

// ButtonActionListener-ek


}
