package skeletonPackage;

/** BreakableField osztály */
public abstract class BreakableField extends Field {

// PRIVAT TAGOK
	/**
	 * Privát tulajdonsága a mezőnek, hogy elvan-e törve, vagy nem.
	 */
	protected boolean isBroken;

	/** Publikus metódus, beállítja, hogy a BreakableField elvan-e törve vagy nem.
	 * @param isBroken, boolean, true ha eltört a mező, false ha nem.
	 */
	public void setBroken(boolean isBroken) {
		this.isBroken = isBroken;
	}

	// GETTER, SETTER
	/**
	 * Publikus metódus, BreakableField paraméter nélküli konstruktora.
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
	 * @param plumber, amelyik szerelő meghívja ezt az interakciót
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean interact(Plumber plumber) {
		Skeleton.printMethod(this, "interact");
		if(isBroken) { isBroken = false; return true; }
		else { return false; }
	}

	/**
	 * Publikus metódus, meghívásakor a szabotőr elrontja a pumpát vagy kilyukasztja csövet.
	 * @param plumber, amelyik szabotőr meghívja ezt az interakciót
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean interact(Saboteur saboteur) {
		Skeleton.printMethod(this, "interact");
		if(!isBroken) { isBroken = true; return true; }
		else { return false; }
	}
}
