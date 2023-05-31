package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PumpView implements View {
	/** A pumpát ábrázoló kör átmérője */
	private static final int diameter = 40;
	
	/** A csővéget ábrázoló kör átmérője */
	private static final int pipeEndDiameter = 14;
	
	/** Az ábrázolandó pumpa */
	private Pump pump;
	
	/** A pumpa bal felső sarkának koordinátái */
	private int x, y;
	
	private GamePanel gamePanel;
	
	public PumpView(Pump p, GamePanel gp) {
		pump = p;
		gamePanel = gp;
	}
	
	/**
	 * Beállítja a pumpa középpontját
	 */
	@Override
	public void setCoordinates(int x, int y) {
		this.x = x - diameter / 2;
		this.y = y - diameter / 2;
	}
	
	/**
	 * Visszaadja a pumpa középpontját
	 */
	@Override
	public int[] getCoordinates() {
		return new int[]{x + diameter / 2, y + diameter / 2};
	}
	
	@Override
	public void update() {
		gamePanel.repaint();
	}
	
	@Override
	public void draw(Graphics g) {
		if(x == 0 || y == 0)		//ha inventory-ban van, nem rajzolja ki
			return;
		
		Graphics2D g2d = (Graphics2D) g.create();
		Color color = pump.getBroken() ? Color.RED : Color.GRAY;
		g2d.setColor(color);
		g2d.fillOval(x, y, diameter, diameter);
			
		Pipe in = pump.getIn();
		if(in != null) {
			int[] pipeCenter = in.getView().getCoordinates();
			int[] activeInCoordinates = getActivePipeCoordinates(pipeCenter);
			g2d.setColor(Color.WHITE);
        	g2d.fillOval(activeInCoordinates[0], activeInCoordinates[1], pipeEndDiameter, pipeEndDiameter);
		}
		
		Pipe out = pump.getOut();
		if(out != null) {
			int[] pipeCenter = out.getView().getCoordinates();
			int[] activeOutCoordinates = getActivePipeCoordinates(pipeCenter);
			g2d.setColor(Color.BLACK);
        	g2d.fillOval(activeOutCoordinates[0], activeOutCoordinates[1], pipeEndDiameter, pipeEndDiameter);
		}
		
		ArrayList<Character> currentCharacters = pump.getCurrentCharacter();
		int charNum = currentCharacters.size();
		int n = 0;
		if(charNum > 0) n = 360 / charNum;
		int i = 1;
		for(Character c : currentCharacters) {
			CharacterView view = c.getView();
			BufferedImage image = view.getImage();
			//g2d.drawImage(image, (int)(x + diameter / 2 + Math.sin(Math.toRadians(n * i)) * (diameter / 2 - 6)/* - eltolás*/),
						//	   (int)(y + diameter / 2 + Math.cos(Math.toRadians(n * i)) * (diameter / 2 - 6)/* - eltolás*/), gamePanel);
			int[] asd = getCoordinates();
			g2d.drawImage(image, asd[0]-(i*5), asd[1]-diameter-(i*5), null);
			i++;
	
		}
	}
	
	private int[] getActivePipeCoordinates(int[] pipeCenter) {
		int r = diameter / 2;
		// a két field középpontjai
		int x1 = x + r;
    	int y1 = y + r;
    	int x2 = pipeCenter[0];
    	int y2 = pipeCenter[1];
		
    	if(x1 == x2) {
    		int pipeY = (y1 > y2) ? y1 - r : y1 + r;
        	return new int[]{x1 - pipeEndDiameter/2, pipeY - pipeEndDiameter/2};
    	} else if(y1 == y2) {
    		int pipeX = (x1 > x2) ? x1 - r : x1 + r;
        	return new int[]{pipeX - pipeEndDiameter/2, y1 - pipeEndDiameter/2};
    	}
    	
    	// y = mx + b egyenes egyenlet
    	float m = (float)(y1-y2)/(float)(x1-x2);      		
    	float b = y1 - x1 * m;
    	
    	// (x-a)^2 + (y-b)^2 = r^2 köregyenlet
    	float pipeX = x1, pipeY = y1;
    	if(y1 > y2) {
    		for(pipeY = y1 - r; pipeY <= y1; pipeY++) {
        		pipeX = (pipeY - b) / m;
        		if(Math.pow(pipeX - x1, 2) + Math.pow(pipeY - y1, 2) <= Math.pow(r, 2)) {
        			break;
        		}
        	}
    	} else {
    		for(pipeY = y1 + r; pipeY >= y1; pipeY--) {
    			pipeX = (pipeY - b) / m;
        		if(Math.pow(pipeX - x1, 2) + Math.pow(pipeY - y1, 2) <= Math.pow(r, 2)) {
        			break;
        		}
    		}
    	}
    	return new int[]{(int)pipeX - pipeEndDiameter/2, (int)pipeY - pipeEndDiameter/2};
	}
	
	@Override
	public GamePanel getGamePanel() { return gamePanel; }
}
