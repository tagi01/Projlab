package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

// TODO dokumentálás
public class PumpView implements View {
	// FIXME itt legyenek?
	private static final int width = 40;  // ?
	private static final int pipeEndWidth = 14;		// ?
	
	private Pump pump;
	
	/** A pumpa bal felső sarkának koordinátái */
	private int x, y;
	
	private GamePanel gamePanel;	// TODO ez jó?
	
	public PumpView(Pump p, GamePanel gp) {
		pump = p;
		gamePanel = gp;
		//x,y
	}
	
	//@Override?
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		//gamePanel.paintUpdate(gamePanel.getGraphics());
	}
	
	/**
	 * Visszaadja a pumpa középpontját
	 */
	@Override
	public int[] getCoordinates() {
		return new int[]{x + width / 2, y + width / 2};
	}
	
	//public void setGamePanel(GamePanel g) { gamePanel = g; }	// TODO meghívni
	
	@Override
	public void update() {
		gamePanel.repaint();
		//draw(gamePanel.getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		/*if(x == 0 || y == 0)		ha inventory-ban van, nem kéne kirajzolni
			return;*/
		
		Color color = pump.getBroken() ? Color.RED : Color.GRAY;
		g.setColor(color);
		g.fillOval(x, y, width, width);
			
		Pipe in = pump.getIn();
		if(in != null) {
			int[] pipeCenter = in.getView().getCoordinates();
			int[] activeInCoordinates = getActivePipeCoordinates(pipeCenter);
			g.setColor(Color.WHITE);
        	g.fillOval(activeInCoordinates[0], activeInCoordinates[1], pipeEndWidth, pipeEndWidth);
		}
		
		Pipe out = pump.getOut();
		if(out != null) {
			int[] pipeCenter = out.getView().getCoordinates();
			int[] activeOutCoordinates = getActivePipeCoordinates(pipeCenter);
			g.setColor(Color.BLACK);
        	g.fillOval(activeOutCoordinates[0], activeOutCoordinates[1], pipeEndWidth, pipeEndWidth);
		}
		
		ArrayList<Character> currentCharacters = pump.getCurrentCharacter();
		//int charNum = currentCharacters.size();	- TODO körbe lehetne rakni őket
		for(Character c : currentCharacters) {
			CharacterView view = c.getView();
			BufferedImage image = view.getImage();
			g.drawImage(image, x+width/2, y+width/2, null);		// TODO JPanel null helyére? - gamePanel
		}
	}
	
	private int[] getActivePipeCoordinates(int[] pipeCenter) {
		int r = width / 2;
		// a két field középpontjai
		int x1 = x + r;
    	int y1 = y + r;
    	int x2 = pipeCenter[0];
    	int y2 = pipeCenter[1];
		
    	if(x1 == x2) {
    		int pipeY = (y1 > y2) ? y1 - r : y1 + r;
        	return new int[]{x1 - pipeEndWidth/2, pipeY - pipeEndWidth/2};
    	} else if(y1 == y2) {
    		int pipeX = (x1 > x2) ? x1 - r : x1 + r;
        	return new int[]{pipeX - pipeEndWidth/2, y1 - pipeEndWidth/2};
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
    	return new int[]{(int)pipeX - pipeEndWidth/2, (int)pipeY - pipeEndWidth/2};
	}
	
	@Override
	public GamePanel getGamePanel() { return gamePanel; }
}
