package skeletonPackage;

/** Saboteur osztály */
public class Saboteur extends Character {

	/**
	 * Publikus metódus, Saboteur kétparaméteres konstruktora, Character
	 * konstruktorához hasonlóan.
	 * 
	 * @param f, Field-ből származót típusú mező, amelyen a karakter áll
	 * @param n, Network, amely hálózatban a karakter és a mező van
	 */
	public Saboteur(Field f, Network n) {
		super(f, n);
	}

	/**
	 * Publikus metódus, meghívásakor csúszóssá teszi a csövet, amelyen áll.
	 */
	public void turnPipeSlippery() {
		currentField.interact(this);
	}
}
