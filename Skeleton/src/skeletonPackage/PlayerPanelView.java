package skeletonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanelView extends JPanel {
    private Game game;
    private Character actCharacter;
    private final int windowWidth = 1280;
    private final int windowHeight = 600;
    private final Color background = new Color(229,202,162);
    private final Color bcolor = new Color(242,242,242);
    private ImageIcon image;

    private Dimension buttonSize = new Dimension();

    private JPanel buttonPanel;
    private Field selectedField;

    private JPanel imagePanel;
    private JLabel karakterszoveg = new JLabel();

    // buttonPanel
    private InventoryPanel inv_1, inv_2, inv_3; // cső egyik- másik vége, pumpa
    private JButton mozog = new JButton("masik mezore lepes");
    private JButton javit = new JButton("javitas");
    private JButton pumpa_be = new JButton("be");
    private JButton pumpa_ki = new JButton("ki");
    private JButton pumpa_fel = new JButton("fel");
    private JButton pumpa_le = new JButton("le");
    private JButton cso_kilyukad = new JButton("kilyukasztas");
    private JButton cso_ragad = new JButton("ragados legyen");
    private JButton cso_csuszos = new JButton("csuszos legyen");
    private JButton cso_felvetel = new JButton("cso felvetel");
    private JButton cso_ciszternarol = new JButton("ciszternarol");
    private JButton cso_lerakas = new JButton("cso lerakasa");
    private JButton passz = new JButton("passz");


    private Pipe in = null;
    private Pipe out = null;

    private void init() {

        // buttonPanel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(150,windowHeight));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // pumpPanel
        JPanel pumpPanel = new JPanel();
        pumpPanel.setLayout(new GridLayout(2,2));
        pumpPanel.setBackground(background);
        pumpPanel.setMaximumSize(new Dimension(120,50));

        // buttonPanel
        Dimension buttonSize = new Dimension(150,25);
        Dimension bhalf = new Dimension(60,25);

        // inventory panelek, benne egy labellel
        inv_1 = new InventoryPanel(" ");
        inv_2 = new InventoryPanel(" ");
        inv_3 = new InventoryPanel(" ");

        setButton(mozog, buttonSize);
        setButton(javit, buttonSize);
        setButton(pumpa_be, bhalf);
        setButton(pumpa_ki, bhalf);
        setButton(pumpa_fel, bhalf);
        setButton(pumpa_le, bhalf);
        setButton(cso_kilyukad, buttonSize);
        setButton(cso_ragad, buttonSize);
        setButton(cso_csuszos, buttonSize);
        setButton(cso_felvetel,buttonSize);
        setButton(cso_ciszternarol, buttonSize);
        setButton(cso_lerakas,buttonSize);
        setButton(passz, buttonSize);

        addActionListeners();

        // panelhez hozzáadás
        buttonPanel.add(inv_1);
        buttonPanel.add(inv_2);
        buttonPanel.add(inv_3);

        buttonPanel.add(mozog);
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

        buttonPanel.add(cso_felvetel);
        buttonPanel.add(cso_ciszternarol);
        buttonPanel.add(cso_lerakas);

        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(passz);

        add(imagePanel);
        add(karakterszoveg);
        add(buttonPanel, BorderLayout.CENTER);
    }

    public PlayerPanelView() {
        game = Game.getInstance();
        actCharacter = game.getActiveCharacter();

        this.setBackground(background);
        this.setPreferredSize(new Dimension(200,windowWidth));

        imagePanel = new JPanel();

        imagePanel.add(new JLabel(new ImageIcon(game.getActiveCharacter().getView().getImage())));

        init();
        updateInfo();
        showButtons();
    }

    public void updateImage() {
        imagePanel.removeAll();
        image = new ImageIcon(actCharacter.getView().getImage());
        imagePanel.add(new JLabel(image));
    }

    public void updateInfo() {

        actCharacter = game.getActiveCharacter();
        karakterszoveg.setText(Program.getNameOfCharacter(game.getActiveCharacter())+""+"     "+game.getActionPoints());
        updateImage();
        showButtons();
    }

    public void showButtons() {
        if(game.getActiveCharNum()%2==0) { // ha szerelo
            //inv_1.updateLabel();
            //inv_2.updateLabel();
            //inv_3.updateLabel();

            inv_1.setVisible(true);
            inv_2.setVisible(true);
            inv_3.setVisible(true);

            javit.setVisible(true);
            pumpa_fel.setVisible(true);
            pumpa_le.setVisible(true);
            cso_csuszos.setVisible(false);
            cso_felvetel.setVisible(true);
            cso_ciszternarol.setVisible(true);

        } else { // ha szabotor
            inv_1.setVisible(false);
            inv_2.setVisible(false);
            inv_3.setVisible(false);

            javit.setVisible(false);
            pumpa_fel.setVisible(false);
            pumpa_le.setVisible(false);
            cso_csuszos.setVisible(true);
            cso_felvetel.setVisible(false);
            cso_ciszternarol.setVisible(false);
        }
    }

    private void setButton(JButton b, Dimension dim) {
        b.setPreferredSize(dim);
        b.setBackground(bcolor);
    }

    private void addActionListeners() {
        mozog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<String> fields = new ArrayList<>();
        		for (Field f : actCharacter.getField().getNeighbours()) {
                	fields.add(Program.getKeyFromFieldMaps(f));
                }
                JButton button = new JButton("Ok");
                FieldChooserFrame fcf = new FieldChooserFrame("Hova szeretnel lepni?",fields,button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedField != null) {
                            actCharacter.move(selectedField);
                            updateInfo();
                        }
                        fcf.dispose();
                    }
                });
                fcf.setLocationRelativeTo(null);
                fcf.setVisible(true);
        }});

        javit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.repair();
            	Plumber currentPlumber = null;
        		for (Plumber p : Program.getPlumbers().values()) {
        			if (game.getActiveCharacter() == p)
        				currentPlumber = p;
        		}
        		if (currentPlumber != null) {
        			currentPlumber.repair();
                    updateInfo();
        		}
            }});

        pumpa_be.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pump pump = null;
        		for (Pump p : Program.getPumps().values()) {
        			if (actCharacter.getField() == p)
        				pump = p;
        		}

        		if (pump != null)
        			in = pump.getIn();
                ArrayList<String> pipes = new ArrayList<>();
                for (Field f : actCharacter.getField().getNeighbours()) {
                	if (f != in)
                		pipes.add(Program.getKeyFromFieldMaps(f));
                }

                JButton button = new JButton("Ok");
                FieldChooserFrame fcf = new FieldChooserFrame("Melyik cso legyen a pumpa bemenete?",pipes,button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(selectedField!=null) {
                            Pipe pipe = null;
                            for (Pipe p : Program.getPipes().values()) {
                                if (selectedField == p)
                                    pipe = p;
                            }
                            if(pipe != null && in != null) { actCharacter.setPump(in, pipe); }
                            updateInfo();
                        }
                        fcf.dispose();
                    }
                });
                fcf.setLocationRelativeTo(null);
                fcf.setVisible(true);
            }});

        pumpa_ki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pump pump = null;
        		for (Pump p : Program.getPumps().values()) {
        			if (actCharacter.getField() == p)
        				pump = p;
        		}

        		if (pump != null)
        			out = pump.getOut();
                ArrayList<String> pipes = new ArrayList<>();
                for (Field f : actCharacter.getField().getNeighbours()) {
                	if (f != out)
                		pipes.add(Program.getKeyFromFieldMaps(f));
                }

                JButton button = new JButton("Ok");
                FieldChooserFrame fcf = new FieldChooserFrame("Melyik cso legyen a pumpa kimenete?",pipes,button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedField != null) {
                            Pipe pipe = null;
                            for (Pipe p : Program.getPipes().values()) {
                                if (selectedField == p)
                                    pipe = p;
                            }
                            if(pipe != null && out != null) { actCharacter.setPump(out, pipe); }
                            updateInfo();
                        }
                        fcf.dispose();
                    }
                });
                fcf.setLocationRelativeTo(null);
                fcf.setVisible(true);
            }});

        pumpa_fel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.getPump();
            	Plumber currentPlumber = null;
        		for (Plumber p : Program.getPlumbers().values()) {
        			if (game.getActiveCharacter() == p)
        				currentPlumber = p;
        		}
        		if (currentPlumber != null) {
        			currentPlumber.getPump();
                    updateInfo();
        		}
            }});

        pumpa_le.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.placePump();
            	Plumber currentPlumber = null;
        		for (Plumber p : Program.getPlumbers().values()) {
        			if (game.getActiveCharacter() == p)
        				currentPlumber = p;
        		}
        		if (currentPlumber != null) {
        			currentPlumber.placePump();
                    updateInfo();
        		}
            }});

        cso_kilyukad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actCharacter.puncturePipe();
                updateInfo();
            }});

        cso_ragad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actCharacter.turnPipeSticky();
                updateInfo();
            }});

        cso_csuszos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.turnPipeSlippery();
            	Saboteur currentSaboteur = null;
        		for (Saboteur s : Program.getSaboteurs().values()) {
        			if (game.getActiveCharacter() == s)
        				currentSaboteur = s;
        		}
        		if (currentSaboteur != null) {
        			currentSaboteur.turnPipeSlippery();
                    updateInfo();
        		}
            }});
        
        cso_lerakas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Plumber currentPlumber = null;
        		for (Plumber p : Program.getPlumbers().values()) {
        			if (game.getActiveCharacter() == p)
        				currentPlumber = p;
        		}
        		if (currentPlumber != null) {
        			currentPlumber.placePipe();
                    updateInfo();
        		}
            }});

        cso_felvetel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> pipes = new ArrayList<>();
                for (Field f : actCharacter.getField().getNeighbours()) {
                    pipes.add(Program.getKeyFromFieldMaps(f));
                }

                JButton button = new JButton("Ok");

                FieldChooserFrame fcf = new FieldChooserFrame("Melyik csovet szeretned felvenni?",pipes,button);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedField != null) {
                            // itt már megvan a selectedField
                            Pipe pipe = null;
                            for (Pipe p : Program.getPipes().values()) {
                                if (selectedField == p)
                                    pipe = p;
                            }
                            Plumber currentPlumber = null;
                            for (Plumber p : Program.getPlumbers().values()) {
                                if (game.getActiveCharacter() == p)
                                    currentPlumber = p;
                            }
                            if (currentPlumber != null && pipe != null) {
                                currentPlumber.grabPipe(pipe);
                                updateInfo();
                            }
                        }
                        fcf.dispose();
                    }
                });
                fcf.setLocationRelativeTo(null);
                fcf.setVisible(true);
            }});

        cso_ciszternarol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.getPipe();
                Plumber currentPlumber = null;
                for (Plumber p : Program.getPlumbers().values()) {
                    if (game.getActiveCharacter() == p)
                        currentPlumber = p;
                }
                if (currentPlumber != null) {
                    currentPlumber.getPipe();
                    updateInfo();
                }
            }});

        // TODO cso_lerakas.addActionListener()

        passz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.nextCharacter();
                updateInfo();
            }});
    }

    /** Inventory-kat megjelenítő panelek, ebben csak egy label van, amit be lehet állítani, frissíteni */
    private class InventoryPanel extends JPanel {
        private JLabel inventory;
        protected InventoryPanel(String szoveg) {
            this.setPreferredSize(new Dimension(70, 30));
            this.setBackground(bcolor);
            inventory = new JLabel(szoveg);
            this.add(inventory,BorderLayout.CENTER);
        }

        /** Frissíti a panelen lévő szöveget */
        protected void updateLabel(String szoveg) {
            inventory.setText(szoveg);
        }
    }

    /** Felugró ablak, itt lehet megfelelő akcióhoz kiválasztani azt a csövet, amin végrehajtódik a játékos akciója. */
    public class FieldChooserFrame extends JFrame implements ActionListener {

        private JPanel buttons;
        private JLabel label;
        private ArrayList<String> fieldList;
        public FieldChooserFrame(String szoveg, ArrayList<String> fields, JButton jbutton) {
            this.setMinimumSize(new Dimension(300,150));
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setResizable(true);

            fieldList = fields;
            buttons = new JPanel();
            buttons.setLayout(new FlowLayout());
            createJRadioButtons();

            JLabel felirat = new JLabel(szoveg);
            felirat.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(felirat,BorderLayout.NORTH);
            this.add(buttons, BorderLayout.CENTER);

            JPanel alul = new JPanel();
            alul.setLayout(new FlowLayout());

            JButton megse = new JButton("Megse");

            megse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedField = null;
                    FieldChooserFrame.this.dispose();
                }});

            alul.add(jbutton);
            alul.add(megse);
            this.add(alul, BorderLayout.SOUTH);
        }

        private void createJRadioButtons() {
            for(String s :fieldList) {
                JRadioButton button = new JRadioButton(s);
                button.addActionListener(this);
                buttons.add(button);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e != null && e.getSource().getClass()==JRadioButton.class) {
                selectedField = Program.getValueFromFieldMaps(((JRadioButton)e.getSource()).getText());
            }
        }
    }
}
