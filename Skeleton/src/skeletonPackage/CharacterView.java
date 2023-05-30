package skeletonPackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterView implements View {

    private Character character;
    private BufferedImage bufferedImage;
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public int[] getCoordinates() {
        return new int[0];
    }

    @Override
    public void setCoordinates(int i, int j) {

    }

    public BufferedImage getImage() {
        return bufferedImage;
    }
}
