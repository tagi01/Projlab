package skeletonPackage;

import java.awt.image.BufferedImage;

public class CharacterView {

    //private Character character;
    private BufferedImage image;
    
    public void setImage(BufferedImage img) {
    	image = img;
    }

    public BufferedImage getImage() {
        return image;
    }
}
