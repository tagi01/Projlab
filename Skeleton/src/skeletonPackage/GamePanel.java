package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameFrame gameFrame;

    public GamePanel(GameFrame gamef) {
        super();
        gameFrame=gamef;
    }

  @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        for(Field f : gameFrame.getGame().getNetwork().getFields()) {
            f.getView().draw(g);
        }
    
    }

    public void update(Graphics g){
        paintComponent(g);
    }

}