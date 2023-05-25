package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // ha marad még idő
    // először a csöveket, utána a többi mezőt
    // a mezők kirajzolják a karaktereket
    public void paintComponent(Graphics g, Game game) {
        for(Field f : game.getNetwork().getPipes()) {
            //f.getView().draw(g);
        }
    }

    // repaint metódus majd újrarajzol ha Update()
}
