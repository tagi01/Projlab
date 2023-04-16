package skeletonPackage;

/** BreakableField osztály */
public abstract class BreakableField extends Field {

// PRIVAT TAGOK
	/**
	 * Privát tulajdonsága a mezőnek, hogy elvan-e törve, vagy nem.
	 */
	protected boolean isBroken;

// GETTER, SETTER
	/**
	 * Publikus metódus, BreakableField konstruktora.
	 */
	public BreakableField() {
		isBroken = false;
	}

// METODUSOK Field-ből örökölve

	// Network itt ront el pumpat
	public void breakField() {
		if (isBroken) { isBroken = false; }
		else { isBroken = true; }
	}

	@Override
	public boolean interact(Plumber plumber) {
		if(isBroken) { isBroken = false; return true; }
		else { return false; }
	}

	@Override
	public boolean interact(Saboteur saboteur) {
		if(!isBroken) { isBroken = true; return true; }
		else { return false; }
	}
}
