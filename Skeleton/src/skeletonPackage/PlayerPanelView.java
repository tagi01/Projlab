package skeletonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanelView extends JPanel {
    private Game game;
    private Character actCharacter;
    private final int windowWidth = 1280;
    private final int windowHeight = 600;
    private final Color background = new Color(229,202,162);
    private final Color bcolor = new Color(242,242,242);

    private JPanel buttonPanel;

    // buttonPanel
    private InventoryPanel inv_1, inv_2, inv_3; // cső egyik- másik vége, pumpa

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
    private JButton passz = new JButton("passz");

    private void init() {

        // buttonPanel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(150,windowHeight-200));
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
        setButton(passz, buttonSize);

        addActionListeners();

        // panelhez hozzáadás
        buttonPanel.add(inv_1);
        buttonPanel.add(inv_2);
        buttonPanel.add(inv_3);

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

        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(passz);
    }

    public PlayerPanelView() {
        game = Game.getInstance();
        actCharacter = game.getActiveCharacter();

        this.setBackground(background);
        this.setPreferredSize(new Dimension(200,windowWidth-100));

        init();
        updateInfo();
        showButtons();
    }

    public void updateInfo() {
        // TODO jatekos kepet hozzaadni
        this.add(new Label("Jatekos neve"+"     "+game.getActionPoints()));
        this.add(new Label("\n"));
        this.add(buttonPanel, BorderLayout.CENTER);
        showButtons();
    }

    public void showButtons() {
        // TODO gombok eltuntetese megfeleloen
        if(game.getActiveCharNum()%2==0) {              // ha szerelo
            cso_csuszos.setVisible(false);
            //inv_1.updateLabel();
            //inv_2.updateLabel();
            //inv_3.updateLabel();

        } else {                                        // ha szabotor
            inv_1.setVisible(false);
            inv_2.setVisible(false);
            inv_3.setVisible(false);

            javit.setVisible(false);
            pumpa_fel.setVisible(false);
            pumpa_le.setVisible(false);
            cso_csuszos.setVisible(true);
        }
    }

    private void setButton(JButton b, Dimension dim) {
        b.setPreferredSize(dim);
        b.setBackground(bcolor);
    }

    private void addActionListeners() {
        javit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.repair();
            }});

        pumpa_be.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }});

        pumpa_ki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }});

        pumpa_fel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.getPummp();
            }});

        pumpa_le.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.placePump();
            }});

        cso_kilyukad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actCharacter.puncturePipe();
            }});

        cso_ragad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actCharacter.turnPipeSticky();
            }});

        cso_csuszos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.turnPipeSlippery();
            }});

        passz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.nextCharacter();
            }});
        
        cso_felvetel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }});
        
        cso_ciszternarol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actCharacter.getPipe();
            }});

        // TODO ActionListenerek normális megcsinálása
        
    }


    private class InventoryPanel extends JPanel {
        private JLabel inventory;
        protected InventoryPanel(String szoveg) {
            this.setPreferredSize(new Dimension(50, 30));
            this.setBackground(bcolor);
            inventory = new JLabel(szoveg);
            this.add(inventory,BorderLayout.CENTER);
        }

        protected void updateLabel(String szoveg) {
            inventory.setText(szoveg);
        }
    }
}
