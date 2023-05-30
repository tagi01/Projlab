package skeletonPackage;

import java.awt.image.BufferedImage;

public class CharacterView{

    private Character character;
    private BufferedImage image;
    
   
    
    public CharacterView(BufferedImage im, Character cr) {
    	image=im;
    	character=cr;
    }

    public BufferedImage getImage() {
    		return image;
    }
}
