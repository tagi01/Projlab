package skeletonPackage;

import java.util.ArrayList;

import javax.swing.JPanel;

/** Cistern osztály */
public class Cistern extends Field {
	//*****************************************************************
	public boolean getHasPump() {return hasPump;}
	public boolean getHasPipe() {return hasPipe;}
	public void setCollectedWater(int water) {collectedWater = water;}
	//*****************************************************************
	/**
	 * Privát integer, amely egy játékos köre alatt összegyűjtött víz mennyiségét tárolja
	 */
	private int collectedWater;

	/**
	 * Privát boolean, amely megadja, hogy van-e felvehető pumpa a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPump;

	/**
	 * Privát ArrayList<>, a ciszterna szomszédos csöveit tárolja.
	 */
	private ArrayList<Pipe> neighbours;
	
	/**
	 * Private boolean, amely megadja, hogy van-e felvehető cső a ciszternán. True ha igen, false ha nincs.
	 */
	private boolean hasPipe;
	
	private CisternView cisternView;
	
	public View getView() {return cisternView;}
	
	/**
	 * Csak a skeletonba kell
	 * @param hasPump az uj ertek
	 */
	public void setHasPump(boolean hasPump) {
		this.hasPump = hasPump;
	}
	
	/**
	 * Csak a skeletonba kell
	 * @param hasPipe az uj ertek
	 */
	public void setHasPipe(boolean hasPipe) {
		this.hasPipe = hasPipe;
	}

	/**
	 * Publikus metódus, a ciszterna konstruktora
	 */
	public Cistern(GamePanel jp) {
		neighbours = new ArrayList<Pipe>();
		cisternView = new CisternView(this, jp);
	}
	
	//
	//METÓDUSOK
	//

	/**
	 * Publikus metódus, meghívásakor a paraméterben kapott csövet a szomszédsági listához adja.
	 * Field-ből örökölt függvény felülírása.
	 * @param p, Pipe amit hozzáadnánk a ciszterna szomszédaihoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean addNeighbour(Pipe p) {
		//Program.printMethod(this, "addNeighbour");
		if(neighbours.contains(p) || p == null) { 
			return false; 
		}
		else {
			neighbours.add(p);
			return true;
		}
	}
	
	/** 
	 * Publikus metódus. Hozzáad egy mezőt a neighbours listához. 
	 * Field-ből örökölt függvény felülírása.
	 * @param: f, az új szomszéd
	 * @return: boolean, true ha sikerült, false ha nem
	 */
	@Override
	public boolean addNeighbour(Field f) {
		//Program.printMethod(this, "addNeighbour");
		return false;
	}

	/**
	 * Publikus metódus. Kivesz egy mezőt a neighbours listából.
	 * Field-ből örökölt függvény felülírása.
	 * @param: f, az eltávolítandó szomszéd
	 * @return: boolean, true ha sikerült, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Field f) {
		//Program.printMethod(this, "removeNeighbour");
		return false;
	}

	/**
	 * Publikus metódus,  meghívásakor a paraméterben kapott csövet kitöröljük a szomszédsági listából.
	 * Field-ből örökölt függvény felülírása.
	 * @param p, Pipe amit törölnénk a ciszterna szomszédaiból
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Pipe p) {
		//Program.printMethod(this, "removeNeighbour");
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
		//Program.printMethod(this, "resetItems");
		hasPump = true;
		hasPipe = true;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról pumpát szeretnénk felvenni, ekkor, ha még van pumpa, akkor visszaadja az új pumpát, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pump a felvett pumpa referenciája (null, ha nincs felvehető pumpa)
	 */
	private Pump removePump() {
		//Program.printMethod(this, "removePump");
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null, game.getGameFrame().getGamePanel());
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
	private Pipe removePipe() {
		//Program.printMethod(this, "removePipe");
		if(hasPipe == true) {
			hasPipe = false;
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
	 * Publikus metódus. Meghívásakor megadja, hogy a paraméterben kapott cső hozzáadható-e szomszédnak.
	 * @param p, egy Pipe referenciája
	 * @return boolean, true ha hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Pipe p) {
		//Program.printMethod(this, "acceptField"); 
		if(neighbours.contains(p) && p!=null) {
			return false;
		} 
		else {
			return true;
		}
	}
	
	/**
	 * Publikus metódus. Meghívásakor megadja, hogy a paraméterben kapott mező hozzáadható-e szomszédnak.
	 * @param f, egy mező referenciája
	 * @return boolean, true ha hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Field f) {
		//Program.printMethod(this, "acceptField");
		return false;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszterna elveszi a hozzá beérkező csövektől az összes vizet és eltárolja.
	 * Az összegyűjtött vizet odaadja a Game objektumnak és lenullázza az összegyűjtött vizet.
	 */
	@Override
	public void flowWater() {
		//Program.printMethod(this, "collectWater");
		for(int i = 0; i < neighbours.size() ; i++) {
			int mennyit = neighbours.get(i).getWater();
			collectedWater += neighbours.get(i).takeWater(mennyit);
		}
		game.givePlumberPoint(collectedWater);
		collectedWater = 0;
	}
	
	/**
	 * Publikus metódus, meghívásakor a plumber inventory-ba helyez egy pipe-ot, ha van a ciszternán pipe. 
	 * Field-ből örökölt függvény felülírása.
	 * @param: plumber, aki meghívta a függényt
	 * @param: p, egy Pipe referenciája
	 * @return: boolean, true ha feltudta venni a csövet, false ha nem
	 */
	@Override
	public void interactPlumber(Plumber plumber, Pipe p) {
		//Program.printMethod(this, "interactPlumber");
		if(p == null) {
			if(hasPipe == true) {
				p = removePipe();
				plumber.setInventoryPipe(p);
				game.removeActionPoints();
			}
		} else {
			if(neighbours.contains(p)) {
				boolean removed = removeNeighbour(p);
				if(removed) {
					p.removeNeighbour(this);
					plumber.setInventoryPipe(p);
					game.removeActionPoints();
				}
			} else {
				boolean added = addNeighbour(p);
				if(added) {
					p.addNeighbour(this);
					plumber.setInventoryPipe(null);
					game.removeActionPoints();
				}
			}
		}
	}
	
	/**
	 * Publikus metódus, meghívásakor a plumber inventory-ba helyez egy pump-át, ha van a ciszternán pumpa. 
	 * Field-ből örökölt függvény felülírása.
	 * @param: plumber, aki meghívta a függényt 
	 * @param: p, egy pumpa referenciája
	 * @return: boolean, true ha feltudta venni a pumpát, false ha nem
	 */
	@Override
	public void interactPlumber(Plumber plumber, Pump p) {
		//Program.printMethod(this, "interactPlumber");
		if(hasPump == true) {
			p = removePump();
			plumber.setInventoryPump(p);
			game.removeActionPoints();
		}
	}


	/** 
	 * Publikus metódus. Visszaadja a szomszédokat
	 * Field-ből örökölt függvény felülírása.
	 * @return ArrayList<>, a cistern szomszédai
	 */
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		//Program.printMethod(this, "getNeighbours");
		return neighbours;
	}

}
