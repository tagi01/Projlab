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
}
