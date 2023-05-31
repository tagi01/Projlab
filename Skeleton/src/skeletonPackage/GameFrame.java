package skeletonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DigestException;


public class GameFrame extends JFrame{

    private int windowWidth = 1280;
    private int windowHeight = 675;
    private Color background = new Color(229,202,162);
    private Color bcolor = new Color(242,242,242);

    private Game game;

    // View-k
    private GamePanel gamePanel;
    private PlayerPanelView playerPanel;

    // felulre
    private JPanel felulre;
    private JMenuBar menu = new JMenuBar();
    private JMenuItem[] menuitems = {
            new JMenuItem("Uj jatek"),
            new JMenuItem("Mentes"),
            new JMenuItem("Betoltes")
    };

    private JLabel gameLabel = new JLabel();
    private String round, Ppoints, Spoints;

    private void init() {

        // PANELS
        // felulre
        felulre.setBackground(background);
        felulre.setLayout(new BorderLayout());
        felulre.setPreferredSize(new Dimension(windowWidth,50));

        // gamePanel
        gamePanel.setBackground(background);
        gamePanel.setPreferredSize(new Dimension(windowWidth-100,windowHeight-200));

        // playerPanel
        JPanel playerPanel = new PlayerPanelView();

        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // ELEMENTS
        // felulre
        for(int i=0; i<3; i++) {
            menu.add(menuitems[i]);
            if(i==0) {
                menuitems[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewGameFrame ngframe = new NewGameFrame();
                        ngframe.setLocationRelativeTo(null);
                        ngframe.setVisible(true);
                    }
                });
            }
        }
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
        this.setResizable(true);

        game = Game.getInstance();

        felulre = new JPanel();
        gamePanel = new GamePanel(this);
        playerPanel = new PlayerPanelView();


        Game.getInstance().setPlayerPanelView(playerPanel);

        updateGameLabel();


        init();
    }

    public GamePanel getGamePanel() { return gamePanel; }
    
    public Game getGame() { return game; }

    public void updateGame(){
        gamePanel.update(getGraphics());
        playerPanel.updateInfo();
    }

    /**Felső sorban az ablakban a játék adatait frissíti*/
    public void updateGameLabel() {
        round = new String(game.getRound()+". kor");
        Ppoints = new String("Szerelok: "+game.getPointsOfPlumber());
        Spoints = new String("Szabotorok: "+game.getPointsOfSaboteur());
        gameLabel.setText("                                               "+round+"     "+Ppoints+"     "+Spoints);
    }

    public void gameOver(String kimenetel) {
        JOptionPane.showMessageDialog(GameFrame.this, kimenetel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

// felugró ablak, új játék indítása

    private class NewGameFrame extends JFrame {

        private JLabel error = new JLabel(" ");
        private JTextField p_1 = new JTextField("Plumber_1");
        private JTextField p_2 = new JTextField("Plumber_2");
        private JTextField p_3 = new JTextField("Plumber_3");
        private JTextField s_1 = new JTextField("Saboteur_1");
        private JTextField s_2 = new JTextField("Saboteur_2");
        private JTextField s_3 = new JTextField("Saboteur_3");

        protected NewGameFrame() {
            this.setTitle("Uj jatek letrehozasa");
            this.setMinimumSize(new Dimension(350,300));
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setResizable(true);

            error.setForeground(Color.RED);
            error.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel kozepre = new JPanel();
            kozepre.setLayout(new GridLayout(6,2));

            JLabel sze_1 = new JLabel("Szerelo 1     ");
            sze_1.setHorizontalAlignment(SwingConstants.RIGHT);
            JLabel sze_2 = new JLabel("Szerelo 2     ");
            sze_2.setHorizontalAlignment(SwingConstants.RIGHT);
            JLabel sze_3 = new JLabel("Szerelo 3     ");
            sze_3.setHorizontalAlignment(SwingConstants.RIGHT);

            JLabel sza_1 = new JLabel("Szabotor 1     ");
            sza_1.setHorizontalAlignment(SwingConstants.RIGHT);
            JLabel sza_2 = new JLabel("Szabotor 2     ");
            sza_2.setHorizontalAlignment(SwingConstants.RIGHT);
            JLabel sza_3 = new JLabel("Szabotor 3     ");
            sza_3.setHorizontalAlignment(SwingConstants.RIGHT);

            kozepre.add(sze_1);
            kozepre.add(p_1);
            kozepre.add(sze_2);
            kozepre.add(p_2);
            kozepre.add(sze_3);
            kozepre.add(p_3);

            kozepre.add(sza_1);
            kozepre.add(s_1);
            kozepre.add(sza_2);
            kozepre.add(s_2);
            kozepre.add(sza_3);
            kozepre.add(s_3);

            this.add(error, BorderLayout.NORTH);
            this.add(kozepre, BorderLayout.CENTER);

            JPanel EscButtons = new JPanel();
            EscButtons.setLayout(new FlowLayout());

            JButton ok = new JButton("Uj jatek letrehozasa");
            ok.setBackground(bcolor);
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ellenorizni hogy megvan-e az elso 4 és ha igen akkor van-e 6 teli text
                    if (p_1.getText().equals("") || p_2.getText().equals("") || s_1.getText().equals("") || s_2.getText().equals("")) {
                        error.setText("Nincs eleg jatekos megadva !");
                        return;
                    } else {
                		if (p_3.getText().equals("") && s_3.getText().equals("")) {
                			String[] names = new String[] {p_1.getText(), p_2.getText(), s_1.getText(), s_2.getText()};
                			//Program.createGame(names);
                		} else {
                			if (p_3.getText().equals("") || s_3.getText().equals("")) {
                                error.setText("Nincs eleg jatekos megadva !");
                				return;
                			} else {
                				String[] names = new String[] {p_1.getText(), s_1.getText(), p_2.getText(), s_2.getText(), p_3.getText(), s_3.getText()};

                    		    Program.createGame(names);

                			}
                		}
                	}
                    NewGameFrame.this.dispose(); // meg itt még elmenteni a neveket
                }
            });

            JButton megse = new JButton("Megse");
            megse.setBackground(bcolor);
            megse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NewGameFrame.this.dispose();
                }
            });

            EscButtons.add(ok);
            EscButtons.add(new Label("     "));
            EscButtons.add(megse);

            this.add(EscButtons, BorderLayout.SOUTH);

        }
    }

}
