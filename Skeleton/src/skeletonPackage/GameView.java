package skeletonPackage;

import javax.swing.*;
import java.awt.*;

public class GameView implements View {

    private GameFrame gf;
    private Game game;

    public GameView(GameFrame gameFrame) {
        game = Game.getInstance();
        gf = gameFrame;
    }

    @Override
    public void update() {
        gf.updateGameLabel();
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public int[] getCoordinates() {
        return new int[0];
    }

    @Override
    public void setCoordinates(int i, int j) {

    }

    public void gameOver(String kimenetel) {
        JOptionPane.showMessageDialog(gf, kimenetel, "Game Over", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
	public GamePanel getGamePanel() { return null; }	// FIXME
}
