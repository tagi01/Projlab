package skeletonPackage;

import java.util.ArrayList;

public class Pipe extends BreakableField {
	/**
	 *  Egesz szam valtozo. A lyukas csobol kifolyo viz mennyiseget tarolja
	 */
	private int lostWater;
	
	private int size;
	/**
	 * Logikai ertek. True, ha valamelyik szerelonel van az egyik vege, False, ha nem mozgatjak egyik veget sem
	 */
	private boolean taken;
	/**
	 * Egesz szam, megadja, hogy a csoben epp mennyi viz van
	 */
	private int water;
	
	private ArrayList<Field> neighbours;
	//TODO szomszédok amik lehetnek fieldek, mivel barmilyen szomszedja lehet.
	
	public Pipe() {
		super();
		lostWater=0;
		size=1; //default size
		taken=false;
		water=0;
	}
	public Pipe(Pump i, Pump o, int siz, boolean take, int wat) {
		super();
		lostWater=0;
		size=siz;
		taken=take;
		water=wat;
	}
	
	public boolean addNeighbour(Field f) {
		if(neighbours.contains(f) && f == null) { return false; }
		else {
			neighbours.add(f);
			return true;
		}
	}
	
	public boolean removeNeighbour(Field f) {
		if(neighbours.contains(f) && f!=null) {
			neighbours.remove(f);
			return true;
		} else { 
			return true; 
			}
	}
	
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
	
	@Override
	public boolean acceptField(Field f) {
		//TODO:csovet nem lehet hozzakotni azt valahogy meg kell tiltani
		return true;
	}
	
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		// TODO Auto-generated method stub
		return neighbours;
	}
	//TODO javadoc
	
	
	
	
	
	
	
	//Ez a resz csak a netwrok miatt kerul bele
	private Pump in;
	private Pump out;

	public void setIn(Pump pump) { in=pump;}
	public void setOut(Pump pump) { out=pump;}
	public Pump getIn() {return in;}
	public Pump getOut() {return out;}
	public void changeNeighbour(Pump p, Pump pump) {
		neighbours.remove(p);
		neighbours.add(pump);
	}
	
	
}
