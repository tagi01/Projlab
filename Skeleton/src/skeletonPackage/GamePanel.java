package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameFrame gameFrame;

    public GamePanel(GameFrame gamef) {
        super();
        gameFrame=gamef;
    }

    // ha marad még idő
    // először a csöveket, utána a többi mezőt
    // a mezők kirajzolják a karaktereket
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        for(Field f : gameFrame.getGame().getNetwork().getFields()) {
            f.getView().draw(g);
        }
    }

    /*
    public void paintUpdate(Graphics g) {
        //for(Field f : game.getNetwork().getPipes()) {
        //f.getView().draw(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        gameFrame.rePaint();
    }
	*/

    // repaint metódus majd újrarajzol ha Update()
}
