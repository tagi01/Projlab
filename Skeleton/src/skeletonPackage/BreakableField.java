package skeletonPackage;

/** BreakableField osztály */
public abstract class BreakableField extends Field {

// ATTRIBUTUMOK
	/**
	 * Mező tulajdonsága, elvan-e törve, vagy nem.
	 * Eltörve (true), ha nincs és működik, akkor (false)
	 */
	protected boolean isBroken;

// GETTER, SETTER
	/** Publikus metódus, beállítja, hogy a BreakableField elvan-e törve vagy nem.
	 * @param isBroken, boolean, true ha eltört a mező, false ha nem.
	 */
	public void setBroken(boolean isBroken) {
		this.isBroken = isBroken;
	}

	/** Getter, megadja, hogy a BreakableField elvan-e törve, vagy nem */
	public boolean getBroken() { return isBroken; } // nem használtuk eddig, de beírtam, ha kéne

// KONSTRUKTOR
	/**
	 * Publikus metódus, BreakableField paraméter nélküli konstruktora, isBroken false
	 */
	public BreakableField() {
		isBroken = false;
	}

// METODUSOK Field-ből örökölve

	/**
	 * Publikus metódus. Meghívásakor a hálózat itt ront el pumpát.
	 */
	public void breakField() {
		if (!isBroken) { isBroken = true; }
	} // Network itt ront el pumpat

	/**
	 * Publikus metódus, meghívásakor a szerelő megjavítja a pumpát vagy csövet.
	 * @param p, amelyik szerelő meghívja ezt az interakciót
	 */
	@Override
	public void interact(Plumber p) {
		if(isBroken) {
			isBroken = false;
			game.removeActionPoints();
		}
	}

	/**
	 * Publikus metódus, meghívásakor a szabotőr elrontja a pumpát vagy kilyukasztja csövet.
	 * @param n, 1 esetén kilyukasztják
	 */
	@Override
	public void interact(int n) {
		if(n == 1) {
			if (!isBroken) {
				isBroken = true;
				game.removeActionPoints();
			}
		}
	}
}
