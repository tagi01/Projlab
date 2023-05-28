package skeletonPackage;

import java.awt.Graphics;

// TODO
public interface View {
	public void update();
	
	public void draw(Graphics g);
	
	public int[] getCoordinates();
}
