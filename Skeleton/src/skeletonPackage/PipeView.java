package skeletonPackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

public class PipeView implements View {

	/**
	 * A megjelenített cső
	 */
	private Pipe pipe;
	
	private GamePanel gamePanel;

	/**
	 * A cső közepének koordinátái
	 */
	private int x, y;

	/**
	 * Konstruktor
	 * 
	 * @param p A megjelenítendő cső
	 */
	public PipeView(Pipe p, GamePanel gp) {
		pipe = p;
		gamePanel = gp;
		ArrayList<? extends Field> neighbours = pipe.getNeighbours();
		if (neighbours.size() > 1) {
			int[] coord1 = neighbours.get(0).getView().getCoordinates();
			int[] coord2 = neighbours.get(1).getView().getCoordinates();
			x = ((coord1[0] + coord2[0]) / 2);
			y = ((coord1[1] + coord2[1]) / 2);
		}
	}

	@Override
	public void update() {
		gamePanel.repaint();
	}

	/**
	 * Beállítja a koordinátákat
	 * 
	 * @param x  a beállítandó x koordináta
	 * @param ya beállítandó y koordináta
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Visszaadja a koordinátákat
	 */
	@Override
	public int[] getCoordinates() {
		return new int[] { x, y };
	}

	/**
	 * Kirajzolja a csövet
	 * 
	 * @param g a megjelenítésre használt Graphics objektum
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		StateOfPipe state = pipe.getState();
		boolean broken = pipe.getBroken();
		if (broken) {
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
		} else
			g2d.setStroke(new BasicStroke(3));
		switch (state) {
		case NORMAL:
			g2d.setColor(Color.GRAY);
			break;
		case SLIPPERY:
			g2d.setColor(Color.YELLOW);
			break;
		case STICKY:
			g2d.setColor(Color.RED);
			break;
		default:
			g2d.setColor(Color.GRAY);
			break;
		}
		ArrayList<? extends Field> neighbours = pipe.getNeighbours();
		if (neighbours.size() > 0) {
			int[] coord1 = neighbours.get(0).getView().getCoordinates();
			if (neighbours.size() > 1) {
				int[] coord2 = neighbours.get(1).getView().getCoordinates();
				g2d.drawLine(coord1[0], coord1[1], coord2[0], coord2[1]);
				if (pipe.getWater() > 0) {
					if (broken) {
						g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
								new float[] { 9 }, 0));
					} else {
						g2d.setStroke(new BasicStroke(1));
					}
					g2d.setColor(Color.BLUE);
					g2d.drawLine(coord1[0], coord1[1], coord2[0], coord2[1]);
				}
			} else {
				g2d.drawLine(coord1[0], coord1[1], x, y);
				if (pipe.getWater() > 0) {
					if (broken) {
						g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
								new float[] { 9 }, 0));
					} else {
						g2d.setStroke(new BasicStroke(1));
					}
					g2d.setColor(Color.BLUE);
					g2d.drawLine(coord1[0], coord1[1], x, y);
				}
			}
		}
		if (pipe.getCurrentCharacter().size() == 1) {
			BufferedImage image = pipe.getCurrentCharacter().get(0).getView().getImage();
			g2d.drawImage(image, x, y, null);
		}
	}

}
