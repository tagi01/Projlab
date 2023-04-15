package skeletonPackage;

import java.util.ArrayList;

/** Cistern osztály */
public class Cistern extends Field {

	/**
	 * Privát integer, amely egy játékos köre alatt összegy?jtött víz mennyiségét tárolja
	 */
	private int collectedWater;

	/**
	 * Privát boolean, amely megadja, hogy van-e felvehet? pumpa a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPump;

	/**
	 * Private boolean, amely megadja, hogy van-e felvehet? cs? a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPipe;
	
	private ArrayList<Pipe> neighbours;

	//TODO: Ezt miert vettuk ki?(Pump es Pipe ot?)
	//private Pump pu;
	//private Pipe pi;

	//TODO javaodc
	public boolean addNeighbour(Pipe p) {
		if(neighbours.contains(p) && p == null) { return false; }
		else {
			neighbours.add(p);
			return true;
		}
	}
	//TODO javadoc
	public boolean removeNeighbour(Pipe p) {
		if(neighbours.contains(p) && p!=null) {
			neighbours.remove(p);
			return true;
		} else {
			return true;
			}
	}

	/*
	 * hasPump es hasPipe ertekeket True-ra allitja, vagyis most mar a
	 *ciszternarol fel lehet venni pumpat es csovet egyarant
	*/
	public void resetItems() {
		hasPump = true;
		hasPipe = true;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról pumpát szeretnénk felvenni, ekkor, ha még van pumpa, akkor visszaadja az új pumpát, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pump a felvett pumpa referenciája (null, ha nincs felvehet? pumpa)
	 */
	public Pump removePump() {
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null);
			return pu;
		}
		else
			return null;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról csövet szeretnénk felvenni, ha lehetséges, ekkor visszaadja az új csövet, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pipe, a felvett cs? referenciája (null, ha nincs felvehet? cs?)
	 */
	public Pipe removePipe() {
		if(hasPipe == true) {
			hasPipe = false;
			//Pipe pi = new Pipe(0, null, null, 9999, true, 0); //size-t allitani kell meg, meg a ciszternahoz hozza van kotve 
			//return pi;
			return null;
		}
		else
			return null;
	}

	/**
	 * Publikus metódus, Field-b?l örökölt függvény felülírása. Meghívásakor megadja, hogy a karakter ráléphet-e a ciszternára.
	 * @return boolean, true, ha ráléphet a ciszternára, false ha nem
	 */
	@Override
	public boolean acceptCharacter() {
		return false;
	}

	/**
	 * Publikus metódus, Field-b?l örökölt függvény felülírása. Meghívásakor megadja, hogy a paraméterben kapott mez? hozzáadató-e szomszédnak.
	 * @param f, Field-b?l leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mez?höz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Field f) {
		//TODO cistern melle nem tehetunk pumpot.
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		return true;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszterna elveszi a hozzá beérkez? csövekt?l az összes vizet és eltárolja.
	 */
	public void collectWater() {
		for(int i = 0; i < neighbours.size() ; i++) {
			Pipe tmp = (Pipe) neighbours.get(i);
			int mennyit = 0;// = tmp.getWater();
			collectedWater += tmp.takeWater(mennyit);
		}
	}
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}

}
