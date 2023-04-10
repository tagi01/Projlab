package skeletonPackage;

public abstract class BreakableField extends Field {
	private boolean isBroken;
	
	public BreakableField(){
		isBroken = false;
	};
	
	/*
	 * megjavítj azt a mezőt, amin a játékos áll
	 */
	public boolean getRepaired;
	/*
	 * metódus meghívasakor elromlik egy mező
	 */
	public boolean bReak;
}
