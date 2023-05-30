package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SourceView implements View{

	private static final int width = 40;
	private static final int height = 40;

	private Source source;

	//bal felső sarok
	private int x, y;

	private GamePanel gamePanel;

	public SourceView(Source sr, GamePanel gamePanel) {
		source=sr;
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
		draw(gamePanel.getGraphics());
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}


}
