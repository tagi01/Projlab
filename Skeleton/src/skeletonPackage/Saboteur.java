package skeletonPackage;

public class Saboteur extends Character{
	/**
	 * Konstruktor
	 * 
	 * @param f a mezo amin all a szabotor
	 * @param n a halozat
	 */
	public Saboteur(Field f, Network n) {
		super(f, n);
	}

	/**
	 * Kilyukasztja a csovet, amin all
	 */
	public void puncturePipe() {
		currentField.bReak();
	}
}
