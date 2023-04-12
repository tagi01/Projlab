package skeletonPackage;

public class Saboteur extends Character{
	
	public Saboteur(Field f, Network n) {
		super(f, n);
	}

	/*
	 * Kilyukasztja a csövet, amin áll
	 */
	public void puncturePipe() {
		currentField.bReak();
	}
}
