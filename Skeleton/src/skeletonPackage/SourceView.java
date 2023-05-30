package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(x, y, width, height);
		
		if (source.getCurrentCharacter().size() == 1) {
			BufferedImage image = source.getCurrentCharacter().get(0).getView().getImage();
			int seg[] = getCoordinates();
			g2d.drawImage(image, seg[0], seg[1], null);
		}
		else {
			for(int i=0; i < source.getCurrentCharacter().size(); i++) {
				BufferedImage image = source.getCurrentCharacter().get(i).getView().getImage();
				int seg[] = getCoordinates();
				g2d.drawImage(image, seg[0]+(i*10), seg[1]+(i*10), null);
			}
		}

	}

	@Override
	public GamePanel getGamePanel() { return gamePanel; }
}
