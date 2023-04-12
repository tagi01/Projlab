package skeletonPackage;
/** BreakableField osztály */
public abstract class BreakableField extends Field {
	/**
	 * Privát tulajdonsága a mezőnek, hogy elvan-e törve, vagy nem.
	 */
	private boolean isBroken;

	/**
	 * Publikus metódus, BreakableField konstruktora.
	 */
	public BreakableField() {
		isBroken = false;
	}

	/**
	 * Publikus metódus, amely meghívásakor megjavítja a mezőt.
	 * @return boolean, vagyis true ha sikerült megjavítani, egyébként false
	 */
	public boolean getRepaired() {
		if(isBroken) { // elvan-e torve a field
			isBroken=false;
			return true; // sikeresen eltortuk
		} else return false; // sikertelen
	}

	/**
	 * Publikus metódus, amely meghívásakor eltörik a mező.
	 * @return boolean, vagyis true ha sikerült eltörni, egyébként false
	 */
	public boolean breakField() {
		if(!isBroken) { // elvan-e torve a field
			isBroken=true;
			return true; // sikeresen eltoruk
		} else return false; // sikertelen
	}
	
}
