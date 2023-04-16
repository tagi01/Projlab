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
	
	public Cistern() {
		neighbours = new ArrayList<Pipe>();
	}
	
	//
	//METÓDUSOK
	//

	/**
	 * Publikus metódus, meghívásakor a paraméterben kapott csövet a szomszédsági listához adja.
	 * @param p, Pipe amit hozzáadnánk a ciszterna szomszédaihoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean addNeighbour(Pipe p) {
		Skeleton.printMethod(this, "addNeighbour");
		if(neighbours.contains(p) || p == null) { 
			return false; 
		}
		else {
			neighbours.add(p);
			return true;
		}
	}
	
	@Override
	public boolean addNeighbour(Field f) {
		Skeleton.printMethod(this, "addNeighbour");
		return false;
	}

	@Override
	public boolean removeNeighbour(Field f) {
		Skeleton.printMethod(this, "removeNeighbour");
		return false;
	}

	/**
	 * Publikus metódus,  meghívásakor a paraméterben kapott csövet kitöröljük a szomszédsági listából.
	 * @param p, Pipe amit törölnénk a ciszterna szomszédaiból
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Pipe p) {
		Skeleton.printMethod(this, "removeNeighbour");
		if(neighbours.contains(p) && p!=null) {
			neighbours.remove(p);
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * Publikus metódus, amely a hasPump és hasPipe értékeket True-ra állítja, vagyis most már a ciszternáról fel lehet venni pumpát és csövet egyaránt.
	 */
	public void resetItems() {
		Skeleton.printMethod(this, "resetItems");
		hasPump = true;
		hasPipe = true;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról pumpát szeretnénk felvenni, ekkor, ha még van pumpa, akkor visszaadja az új pumpát, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pump a felvett pumpa referenciája (null, ha nincs felvehető pumpa)
	 */
	public Pump removePump() {
		Skeleton.printMethod(this, "removePump");
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null);
			network.addField(pu);
			return pu;
		}
		else
			return null;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról csövet szeretnénk felvenni, ha lehetséges, ekkor visszaadja az új csövet, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pipe, a felvett cső referenciája (null, ha nincs felvehető cső)
	 */
	public Pipe removePipe() {
		Skeleton.printMethod(this, "removePipe");
		if(hasPipe == true) {
			hasPipe = false;
			//TODO A pipe rendes létrehozása?
			Pipe pi = new Pipe();
			pi.setTaken(true);
			pi.addNeighbour(this);
			network.addField(pi);
			return pi;
		}
		else
			return null;
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor megadja, hogy a paraméterben kapott mező hozzáadató-e szomszédnak.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Pipe p) {
		Skeleton.printMethod(this, "acceptField"); 
		if(neighbours.contains(p) && p!=null) {
			return false;
		} 
		else {
			return true;
		}
	}
	public boolean acceptField(Field f) {
		Skeleton.printMethod(this, "acceptField");
		return false;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszterna elveszi a hozzá beérkező csövektől az összes vizet és eltárolja.
	 */
	public void collectWater() {
		Skeleton.printMethod(this, "collectWater");
		for(int i = 0; i < neighbours.size() ; i++) {
			int mennyit = neighbours.get(i).getWater();
			collectedWater += neighbours.get(i).takeWater(mennyit);
		}
	}
	
	/**
	 * Publikus metódus, meghívásakor a plumber inventory-ba helyez egy pipe-ot, ha van a ciszternán pipe. 
	 * @param: plumber, aki meghívta a függényt
	 * @return: boolean, true ha feltudta venni a csövet, false ha nem
	 */
	@Override
	public boolean interactPlumber(Plumber plumber, Pipe p) {
		Skeleton.printMethod(this, "interactPlumber");
		if(hasPipe == true) {
			p = removePipe();
			plumber.setInventoryPipe(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Publikus metódus, meghívásakor a plumber inventory-ba helyez egy pump-át, ha van a ciszternán pumpa. 
	 * @param: plumber, aki meghívta a függényt
	 * @return: boolean, true ha feltudta venni a pumpát, false ha nem
	 */
	@Override
	public boolean interactPlumber(Plumber plumber, Pump p) {
		Skeleton.printMethod(this, "interactPlumber");
		if(hasPipe == true) {
			p = removePump();
			plumber.setInventoryPump(p);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ArrayList<? extends Field> getNeighbours() {
		Skeleton.printMethod(this, "getNeighbours");
		return neighbours;
	}
}
