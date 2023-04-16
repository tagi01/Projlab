package skeletonPackage;

import java.util.ArrayList;

/** Cistern osztály */
public class Cistern extends Field {
	/**
	 * Privát integer, amely egy játékos köre alatt összegyűjtött víz mennyiségét tárolja
	 */
	private int collectedWater;

	/**
	 * Privát boolean, amely megadja, hogy van-e felvehető pumpa a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPump;

	/**
	 * Csak a skeletonba kell
	 * @param hasPump az uj ertek
	 */
	public void setHasPump(boolean hasPump) {
		this.hasPump = hasPump;
	}

	/**
	 * Private boolean, amely megadja, hogy van-e felvehető cső a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPipe;

	/**
	 * Csak a skeletonba kell
	 * @param hasPipe az uj ertek
	 */
	public void setHasPipe(boolean hasPipe) {
		this.hasPipe = hasPipe;
	}

	/**
	 * Privát, a ciszterna szomszédos csöveit tárolja.
	 */
	private ArrayList<Pipe> neighbours;
	
	// Ezt miert vettuk ki?(Pump es Pipe ot?) - ciszternanak nem lesz ki-es bemenete pump
	//private Pump pu;
	//private Pipe pi;

	/**
	 * Publikus metódus, meghívásakor a paraméterben kapott csövet a szomszédsági listához adja.
	 * @param p, Pipe amit hozzáadnánk a ciszterna szomszédaihoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean addNeighbour(Pipe p) {
		if(neighbours.contains(p) || p == null) { 
			System.out.println("Nem sikerült hozzáadni a szomszédot a ciszternához.");
			return false; 
		}
		else {
			neighbours.add(p);
			System.out.println("Sikerült hozzáadni a szomszédot a ciszternához.");
			return true;
		}
	}

	/**
	 * Publikus metódus,  meghívásakor a paraméterben kapott csövet kitöröljük a szomszédsági listából.
	 * @param p, Pipe amit törölnénk a ciszterna szomszédaiból
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	//TODO Ennek most booleannal kéne visszatérnie vagy nem? + a kiírás
	public void removeNeighbour(Pipe p) {
		if(neighbours.contains(p) && p!=null) {
			neighbours.remove(p);
			//return true;
		} 
		else {
			//return false;
		}
	}

	/**
	 * Publikus metódus, amely a hasPump és hasPipe értékeket True-ra állítja, vagyis most már a ciszternáról fel lehet venni pumpát és csövet egyaránt.
	 */
	public void resetItems() {
		hasPump = true;
		hasPipe = true;
		System.out.println("Újralett töltve a ciszterna csővel és pumpával.");
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról pumpát szeretnénk felvenni, ekkor, ha még van pumpa, akkor visszaadja az új pumpát, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pump a felvett pumpa referenciája (null, ha nincs felvehető pumpa)
	 */
	public Pump removePump() {
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null);
			System.out.println("Sikerült felvenni a pumpát.");
			return pu;
		}
		else
			System.out.println("Nem sikerült felvenni a pumpát.");
			return null;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról csövet szeretnénk felvenni, ha lehetséges, ekkor visszaadja az új csövet, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pipe, a felvett cső referenciája (null, ha nincs felvehető cső)
	 */
	public Pipe removePipe() {
		if(hasPipe == true) {
			hasPipe = false;
			//TODO A pipe rendes létrehozása és a ciszterna szomszéd beállitása
			Pipe pi = new Pipe();
			System.out.println("Sikerült felvenni a csövet.");
			return pi;
		}
		else
			System.out.println("Nem sikerült felvenni a csövet.");
			return null;
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor megadja, hogy a paraméterben kapott mező hozzáadató-e szomszédnak.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Field f) {
		//TODO cistern melle nem tehetunk pumpot.
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		//if(pumplist.contains(f) && f!=null) {
		//	return false;
		//} 
		//else {
			return true;
		//}
	}

	/**
	 * Publikus metódus, meghívásakor a ciszterna elveszi a hozzá beérkező csövektől az összes vizet és eltárolja.
	 */
	public void collectWater() {
		for(int i = 0; i < neighbours.size() ; i++) {
			int mennyit = neighbours.get(i).getWater();
			collectedWater += neighbours.get(i).takeWater(mennyit);
		}
		System.out.println("Begyűjtöttem a vizet a csövekből.");
	}
	
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}

	public boolean interactPump(Plumber p) {
		
		return false;
	}
	
	public boolean interactPipe(Plumber p) {
		
		return false;
	}
	//asd

	@Override
	public boolean addNeighbour() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interact() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interact(Pipe from, Pipe to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interactPlumber(Plumber p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addNeighbour(Field f) {
		// TODO Auto-generated method stub
		return false;
	}
}
//asd

