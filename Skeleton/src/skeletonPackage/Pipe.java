package skeletonPackage;

public class Pipe extends BreakableField {
	/**
	 *  Egesz szam valtozo. A lyukas csobol kifolyo viz mennyiseget tarolja
	 */
	private int lostWater;
	/**
	 * A csobe vizet pumpalo pumpa referenciaja
	 */
	private Pump in;
	/**
	 *  A csobol vizet kero pumpa referenciaja
	 */
	private Pump out;
	/**
	 *  Egesz szam, ami jellemzi a cso vizbefogado merteket
	 */
	private int size;
	/**
	 * Logikai ertek. True, ha valamelyik szerelonel van az egyik vege, False, ha nem mozgatjak egyik veget sem
	 */
	private boolean taken;
	/**
	 * Egesz szam, megadja, hogy a csoben epp mennyi viz van
	 */
	private int water;
	
	public Pipe() {
		super();
		lostWater=0;
		in=null;
		out=null;
		size=1; //default size
		taken=false;
		water=0;
	}
	public Pipe(Pump i, Pump o, int siz, boolean take, int wat) {
		super();
		lostWater=0;
		in=i;
		out=o;
		size=siz;
		taken=take;
		water=wat;
	}
	
	/**
	 * Csobe pumpalo pumpa bemenetet allitja be
	 * @param p Az uj bemeneti pumpa
	 */
	public void setIn(Pump p) {in=p;}
	/**
	 * A csobe vizet pumpalo pumpa referenciajat adja meg
	 * @return A cso bemeneti pumpaja
	 */
	public Pump getIn() {return in;}
	/**
	 * A csobol vizet kero pumpa referenciajat adja vissza
	 * @return A cso kimeneti pumpaja
	 */
	public Pump getOut() {return out;}
	/**
	 * Amelyik pumpaba folyik a viz, annak referenciajat allitja be
	 * @param p A cso uj kimeneti pumpaja
	 */
	public void setOut(Pump p) {out=p;}
	/**
	 * Logikai valtozoval ter vissza. Megmondja, hogy a jatekos ralephet-e a csore. True, ha igen, False, ha nem.
	 */
	@Override
	public boolean acceptCharacter() {
		if(currentCharacters.size() == 0 && taken == false) {
			return true;
		}
		return false;
	}
	
	/**
	 * Vizet ad a csobe
	 * @param amount A csobe kerulo viz mennyisege
	 */
	public void flowWater(int amount) {
		//nem kell megvizsgalni, hogy a cso tulcsordulna, mert csak annyi vizet pumpal majd a pumpa(amount) amennyit tud meg ahhoz, hogy cso ne csorduljon tul
		water+=amount;
	}
	/**
	 *  Egesz szamot ad vissza, megmondja, pontosan mennyi vizet tud meg befogadni
	 *  @return A cso szabad kapacitasa
	 */
	public int getCapacity() {
		return size-water;
	}
	/**
	 * Maximum a parameterkent kapott vizzel kevesebb lesz a csoben (water attributum). Visszaadja, hogy tenylegesen
	 * mennyit tudott ebbol adni
	 * @return Az atadott viz mennyisege
	 */
	public int takeWater(int amount){
		if(water==0) {
			return 0;
		}
		else if(amount<water) {
			water-=amount;
			return amount;
		}
		else if(amount == water) {
			water=0;
			return amount;
		}
		else {
			//azert kell egy seged valtozo, mert ha a vizemennyiseget meg az elott megvaltoztatjuk mielott viszakuldjuk false ertek lesz,
			//visszeteres utan, pedig nem tudjuk megvaltoztatni igy nem csokken majd a csoben levo vizmennyiseg
			int temp_water=water;
			water=0;
			return temp_water;
		}	
	}
	/**
	 * 
	 * @return a csoben levo viz mennyisege
	 */
	public int getWater() {
		return water;
	}
	/**
	 * 
	 */
	@Override
	public boolean acceptField(Field f) {
		// TODO Auto-generated method stub
		return true;
	}
}
