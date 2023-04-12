package skeletonPackage;

public abstract class BreakableField extends Field {
	protected boolean isBroken;
	
	public BreakableField(){
		isBroken = false;
	};
	
	/*
	 * megjavítj azt a mezőt, amin a játékos áll
	 */
	final public boolean getRepaired(){
		isBroken=false;
		return true;
	}
	/*
	 * metódus meghívasakor elromlik egy mező
	 */
	@Override
	final public boolean bReak(){
		isBroken=true;
		return true;
	}
	
}
