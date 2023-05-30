package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CisternView implements View {
	private static final int width = 40;
	private static final int height = 40;

	private Cistern cistern;

	// bal felső sarok
	private int x, y;

	private GamePanel gamePanel;

	public CisternView(Cistern cr, GamePanel gamePanel) {
		cistern=cr;
		this.gamePanel=gamePanel;
	}

	//@Override?
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		//gamePanel.paintUpdate(gamePanel.getGraphics());
	}
	
	// középpontot ad vissza
	@Override
	public int[] getCoordinates() {
		return new int[]{x + width / 2, y + height / 2};
	}

	@Override
	public void update() {
		gamePanel.repaint();
		//draw(gamePanel.getGraphics());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		if(cistern.getHasPipe()) {
			g.drawString("pipe", x - 5, y + height + 5);
		}
		if(cistern.getHasPump()) {
			g.drawString("pump", x + (width / 2) + 5, y + height + 5);
		}
		ArrayList<Character> currentCharacters = cistern.getCurrentCharacter();
		for(Character c : currentCharacters) {
			CharacterView view = c.getView();
			BufferedImage image = view.getImage();
			g.drawImage(image, x+width/2, y+width/2, null);
		}
	}

	@Override
	public GamePanel getGamePanel() { return gamePanel; }
}

