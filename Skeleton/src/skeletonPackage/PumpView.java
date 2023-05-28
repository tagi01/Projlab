package skeletonPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// TODO dokumentálás
public class PumpView implements View {
	// FIXME itt legyenek?
	private static final int width = 40;  // ?
	private static final int pipeEndWidth = 14;		// ?
	private static final Color WORKING  = Color.GRAY;
	private static final Color BROKEN = Color.RED;
	private static final Color INPIPE = Color.WHITE;
	private static final Color OUTPIPE = Color.BLACK;
	
	private Pump pump;
	
	private int x, y;		// középpont vagy bal felső sarok?
	
	private GamePanel gamePanel;	// TODO ez jó?
	
	public PumpView(Pump p) {
		pump = p;
		//x,y
	}
	
	//@Override?
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int[] getCoordinates() {		// középpontot kéne visszaadnia
		return new int[]{x,y};
	}
	
	public void setGamePanel(GamePanel g) { gamePanel = g; }	// TODO meghívni
	
	@Override
	public void update() {
		gamePanel.repaint();
	}
	
	@Override
	public void draw(Graphics g) {
		/*if(x == 0 || y == 0)		ha inventory-ban van, nem kéne kirajzolni
			return;*/
		
		Color color = pump.getBroken() ? BROKEN : WORKING;
		g.setColor(color);
		g.fillOval(x, y, width, width);
		
		// ha a cső fel van véve? - kéne a csőnek is egy középpont koordináta?	
		Pipe in = pump.getIn();
		if(in != null) {
			ArrayList<? extends Field> inNeighbours = in.getNeighbours();
			for(Field f : inNeighbours) {
				if(f != pump) {
					int[] inPipeOtherEnd = f.getView().getCoordinates();
					int[] activeInCoordinates = getActivePipeCoordinates(inPipeOtherEnd);
					g.setColor(INPIPE);
	            	g.fillOval(activeInCoordinates[0], activeInCoordinates[1], pipeEndWidth, pipeEndWidth);
				}
			}
		}
		
		Pipe out = pump.getOut();
		if(out != null) {
			ArrayList<? extends Field> outNeighbours = out.getNeighbours();
			for(Field f : outNeighbours) {
				if(f != pump) {
					int[] outPipeOtherEnd = f.getView().getCoordinates();
					int[] activeOutCoordinates = getActivePipeCoordinates(outPipeOtherEnd);
					g.setColor(OUTPIPE);
	            	g.fillOval(activeOutCoordinates[0], activeOutCoordinates[1], pipeEndWidth, pipeEndWidth);					
				}
			}
		}
		
		ArrayList<Character> currentCharacters = pump.getCurrentCharacter();
		//int charNum = currentCharacters.size();	- körbe lehetne rakni őket
		for(Character c : currentCharacters) {
			///*Character*/View view = c.getView();
			//BufferedImage image = view.getImage();
			//g.drawImage(image, x+width/2, y+width/2, null);		// TODO JPanel null helyére? - gamePanel
		}
	}
	
	private int[] getActivePipeCoordinates(int[] otherEndCoordinates) {
		int r = width / 2;
		// a két field középpontjai (ha nem középpontot kap)
		int x1 = x + r;
    	int y1 = y + r;
    	int x2 = otherEndCoordinates[0] + r;		// nem ez, ha nem pump
    	int y2 = otherEndCoordinates[1] + r;		// nem ez, ha nem pump
		
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
}
