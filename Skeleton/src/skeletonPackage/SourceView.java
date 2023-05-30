package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

	@Override
	public void setCoordinates(int x, int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
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

		ArrayList<Character> currentCharacters = source.getCurrentCharacter();
		int charNum = currentCharacters.size();
		int n = 0;
		if(charNum > 0) n = 360 / charNum;
		int i = 0;
		for(Character c : currentCharacters) {
			CharacterView view = c.getView();
			BufferedImage image = view.getImage();
			g.drawImage(image, (int)(x + width / 2 + Math.sin(Math.toRadians(n * i)) * (width / 2 - 6)/* - eltolás*/),
							   (int)(y + height / 2 + Math.cos(Math.toRadians(n * i)) * (height / 2 - 6)/* - eltolás*/), gamePanel);
			i++;
		}
	}

	@Override
	public GamePanel getGamePanel() { return gamePanel; }
}
