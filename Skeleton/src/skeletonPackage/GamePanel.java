package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // ha marad még idő
    // először a csöveket, utána a többi mezőt
    // a mezők kirajzolják a karaktereket
	GameFrame gr;
	
	public GamePanel(GameFrame gamef) {
		super();
		gr=gamef;
	}
	
    public void paintUpdate(Graphics g) {
        //for(Field f : game.getNetwork().getPipes()) {
            //f.getView().draw(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
    	gr.rePaint();
    }
    

    // repaint metódus majd újrarajzol ha Update()
}
