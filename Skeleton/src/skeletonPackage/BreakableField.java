package skeletonPackage;

public abstract class BreakableField extends Field {
	protected boolean isBroken;
	
	public BreakableField(){
		isBroken = false;
	};
	//gettere ami visszaadja, hogy elromlott e a cso(lyukas-e),vagy pump
	public boolean getIsBroken() {return isBroken;}
	//beallitja ha egy cso kilyukad, vagy pumpa elromlik, az erteket true-ra
	public void setIsBroken(boolean value) {isBroken=value;}
	
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
