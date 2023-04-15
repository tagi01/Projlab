package skeletonPackage;

public class Saboteur extends Character{
	/**
	 * Publikus metódus, Saboteur konstruktora, Character-hez hasonló.
	 * @param f a mezo amin all a szabotor
	 * @param n a halozat
	 */
	public Saboteur(Field f, Network n) {
		super(f, n);
	}

	/**
	 * Publikus metódus, meghívásakor kilyukasztja a csövet, amelyen áll.
	 */
	public void puncturePipe() {
		currentField.breakField();
	}
}
