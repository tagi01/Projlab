package skeletonPackage;

import java.awt.image.BufferedImage;

/** Saboteur osztály */
public class Saboteur extends Character {

	/**
	 * Publikus metódus, Saboteur kétparaméteres konstruktora, Character
	 * konstruktorához hasonlóan.
	 * 
	 * @param f, Field-ből származót típusú mező, amelyen a karakter áll
	 * @param n, Network, amely hálózatban a karakter és a mező van
	 */
	public Saboteur(Field f, Network n, BufferedImage im) {
		super(f, n, im);
	}

	/**
	 * Publikus metódus, meghívásakor csúszóssá teszi a csövet, amelyen áll.
	 */
	public void turnPipeSlippery() {
		currentField.interact(this);
	}
}
