package skeletonPackage;

public class Pipe extends BreakableField {
	// Egész szám változó. A lyukas csőből kifolyó víz mennyiségét tárolja
	private int lostWater;
	//A csőbe vizet pumpáló pumpa referenciája
	private Pump in;
	// A csőből vizet kérő pumpa referenciája
	private Pump out;
	// Egész szám, ami jellemzi a cső vízbefogadó mértékét
	private int size;
	//Logikai érték. True, ha valamelyik szerelőnél van az egyik vége, False, ha nem mozgatják egyik végét sem
	private boolean taken;
	//Egész szám, megadja, hogy a csőben épp mennyi víz van
	private int water;
	
	public Pipe(int lwater, Pump i, Pump o, int siz, boolean take, int wat) {
		super();
		lostWater=0;
		in=i;
		out=o;
		size=siz;
		taken=take;
		water=wat;
	}
	
	//csobe pumpalo pumpa bemenetet allitja be
	public void setIn(Pump p) {in=p;}
	//a csobe vizet pumpalo pumpa referenciajat adja meg
	public Pump getIn() {return in;}
	//a csobol vizet kero pumpa referenciajat adja vissza
	public Pump getOut() {return out;}
	//amelyik pumpaba folyik a viz, annak referenciajat allitja be
	public void setOut(Pump p) {out=p;}
	/*
	 *Logikai változóval tér vissza. Megmondja, hogy a játékos ráléphet-e a csőre. True, ha igen, False, ha nem. Field-ben szereplő metódus
	 *felülírása
	 */
	public boolean acceptField(Field f) {
		//TODO:
		return true;
	}
	
	/*
	 *A pumpa kimenetén lévő csőbe adódik oda a paraméterben lévő egész szám
	 */
	public void flowWater(int amount) {
		//nem kell megvizsgalni, hogy a cso tulcsordulna, mert csak annyi vizet pumpal majd a pumpa(amount) amennyit tud meg ahhoz, hogy cso ne csorduljon tul
		water+=amount;
	}
	/*
	 *  Egész számot ad vissza meghívásakor, pontosan mennyi vizet tud még befogadni
	 */
	public int getCapacity() {
		return size-water;
	}
	/*
	 * A metódus meghívásakor maximum a paraméterként
	 *kapott vízzel kevesebb lesz a csőben (water attribútum). Visszaadja, hogy ténylegesen
	 *mennyit tudott ebből adni
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
}
