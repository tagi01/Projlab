package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CisternView implements View {
	private static final int width = 40;
	private static final int height = 40;
	
	private Cistern cistern;
	
	private int x, y;		// középpont vagy bal felső sarok?
	
	private GamePanel gamePanel;
	
	public CisternView(Cistern cr, GamePanel gamePanel) {
		cistern=cr;
		this.gamePanel=gamePanel;
	}

	//@Override?
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		gamePanel.paintUpdate(gamePanel.getGraphics());
	}
	
	@Override
	public int[] getCoordinates() {		// középpontot kéne visszaadnia
		return new int[]{x,y};
	}
	
	@Override
	public void update() {

		draw(gamePanel.getGraphics());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}

