package skeletonPackage;

import java.awt.image.BufferedImage;

public class CharacterView {

    //private Character character;
    private BufferedImage image;
    
    public CharacterView(BufferedImage img) {
    	image = img;
    }

    public BufferedImage getImage() {
        return image;
    }
}
