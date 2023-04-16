package skeletonPackage;

import java.util.ArrayList;

/** Pipe osztály */
public class Pipe extends BreakableField {

	/**
	 * Privát integer, a lyukas csőből kifolyó víz mennyiségét tárolja.
	 */
	private int lostWater;

	/**
	 * Privát integer, a cső vízbefogadó mértékét tárolja.
	 */
	private int size;

	/**
	 * Privát boolean, megadja, hogy mozgatják-e már bármelyik végét.
	 * Értéke true, ha valamelyik szerelőnél van az egyik vége,
	 *     false, ha nem mozgatják egyik végét sem.
	 */
	private boolean taken;

	/**
	 * @param taken az uj ertek
	 */
	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	/**
	 * Privát integer, megadja, hogy a csőben épp mennyi víz van.
	 */
	private int water;
	
	private ArrayList<Field> neighbours;
	//TODO szomszédok amik lehetnek fieldek, mivel barmilyen szomszedja lehet.

	public Pipe() {
		super();
		lostWater = 0;
		size = 1; //default size
		taken = false;
		water = 0;
		neighbours = new ArrayList<Field>();
	}

	public Pipe(int siz, int water) {
		super();
		lostWater = 0;
		size = siz;
		taken = false;
		this.water = water;
		neighbours = new ArrayList<Field>();
	}

	@Override
	public boolean addNeighbour(Field f) {
		Skeleton.printMethod(this, "addNeighbour");
		if(neighbours.contains(f) || f == null) {
			return false;
		}else {
			neighbours.add(f);
			return true;
		}
	}

	@Override
	public boolean addNeighbour(Pipe p) {
		Skeleton.printMethod(this, "addNeighbour");
		return false;
	}

	public boolean removeNeighbour(Field f) {
		Skeleton.printMethod(this, "removeNeighbour");
		if(neighbours.contains(f) && f!=null) {
			neighbours.remove(f);
			return true;
		} else {
			return true;
			}
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor megadja, hogy a karakter ráléphet-e a ciszternára.
	 * @return boolean, true, ha ráléphet a ciszternára, false ha nem
	 */
	@Override
	public boolean acceptCharacter() {
		Skeleton.printMethod(this, "acceptCharacter");
		if(currentCharacters.size() == 0 && taken == false) {
			return true;
		}
		return false;
	}

	/**
	 * Publikus metódus, meghívásakor a pumpa kimenetén lévő csőbe odaadódik a paraméterben lévő egész szám.
	 * @param amount, integer, amennyi víz átkerül a pumpa kimenetén lévő csőbe
	 */
	public void flowWater(int amount) {
		Skeleton.printMethod(this, "flowWater");
		//nem kell megvizsgalni, hogy a cso tulcsordulna, mert csak annyi vizet pumpal majd a pumpa(amount) amennyit tud meg ahhoz, hogy cso ne csorduljon tul
		water+=amount;
	}

	/**
	 * Publikus metódus, megadja, hogy pontosan mennyi vizet tud még befogadni.
	 * @return integer, amennyi vizet be tud még fogadni
	 */
	public int getCapacity() {
		Skeleton.printMethod(this, "getCapacity");
		return size-water;
	}

	/**
	 * A metódus meghívásakor maximum a paraméterként kapott vízzel kevesebb lesz a csőben.
	 * @param amount, integer, ennyit kér tőle a pumpa
	 * @return integer, ténylegesen ennyit tudott ebből adni.
	 */
	public int takeWater(int amount){
		Skeleton.printMethod(this, "takeWater");
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
	 * Publikus metódus, meghíváskor visszaadja, a csőben lévő víz mennyiségét
	 * @return integer, a csőben lévő víz mennyisége
	 */
	public int getWater() {
		Skeleton.printMethod(this, "getWater");
		return water;
	}

	public boolean acceptField(Pipe pipe) {
		Skeleton.printMethod(this, "acceptField");
		return false;
	}

	public boolean acceptField(Cistern cistern) {
		Skeleton.printMethod(this, "acceptField");
		return true;
	}

	public boolean acceptField(Source source) {
		Skeleton.printMethod(this, "acceptField");
		return true;
	}

	public boolean acceptField(Pump pump) {
		Skeleton.printMethod(this, "acceptField");
		return true;
	}

	public ArrayList<? extends Field> getNeighbours() {
		Skeleton.printMethod(this, "getNeighbours");
		return neighbours;
	}
	//TODO javadoc

	// FIXME Ez a resz csak a netwrok miatt kerul bele
	//private Pump in;
	//private Pump out;

	//public void setIn(Pump pump) { in=pump;}
	//public void setOut(Pump pump) { out=pump;}
	//public Pump getIn() {return in;}
	//public Pump getOut() {return out;}
	
	public void changeNeighbour(Pump p, Pump pump) {
		neighbours.remove(p);
		neighbours.add(pump);
		Skeleton.printMethod(this, "changeNeighbour");
	}
	@Override
	public boolean removeNeighbour(Pipe p) {
		Skeleton.printMethod(this, "removeNeighbour");
		return false;
	}

	@Override
	public boolean interactPlumber(Plumber p, Pump pump) {
		Skeleton.printMethod(this, "interactPlumber");
		if(isBroken) {
			return false;
		} else {
			network.addPump(pump, this);
			p.setInventoryPump(null);
			return true;
		}
	}
}
