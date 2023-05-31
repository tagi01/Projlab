package skeletonPackage;

import java.util.ArrayList;

/** Cistern osztály */
public class Cistern extends Field {
	//*****************************************************************
	/**
	 * hasPump gettere
	 * @return a hasPump értéke
	 */
	public boolean getHasPump() {return hasPump;}
	
	/**
	 * hasPipe gettere
	 * @return a hasPipe értéke
	 */
	public boolean getHasPipe() {return hasPipe;}
	
	/**
	 * collectedWater settere
	 * @param water a beállítandó érték
	 */
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
	public Cistern(GamePanel jp, Game g) {
		super(g);
		neighbours = new ArrayList<Pipe>();
		cisternView = new CisternView(this, jp);
		hasPipe = true;
		hasPump = true;
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
		hasPump = true;
		hasPipe = true;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszternáról pumpát szeretnénk felvenni, ekkor, ha még van pumpa, akkor visszaadja az új pumpát, ha nincs, akkor egy null-lal visszatér a metódus.
	 * @return Pump a felvett pumpa referenciája (null, ha nincs felvehető pumpa)
	 */
	private Pump removePump() {
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null, game.getGameFrame().getGamePanel(), game);
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
		if(hasPipe == true) {
			hasPipe = false;
			Pipe pi = new Pipe(game.getGameFrame().getGamePanel(), game);
			pi.setTaken(true);
			pi.addNeighbour(this);
			int[] cs = this.getView().getCoordinates();
			pi.getView().setCoordinates(cs[0], cs[1] - 35);
			network.addField(pi);
			return pi;
		}
		else
			return null;
	}

	/**
	 * Publikus metódus, meghívásakor a ciszterna elveszi a hozzá beérkező csövektől az összes vizet és eltárolja.
	 * Az összegyűjtött vizet odaadja a Game objektumnak és lenullázza az összegyűjtött vizet.
	 */
	@Override
	public void flowWater() {
		for(int i = 0; i < neighbours.size() ; i++) {
			int mennyit = neighbours.get(i).getWater();
			collectedWater += neighbours.get(i).takeWater(mennyit);
		}
		game.givePlumberPoint(collectedWater);
		collectedWater = 0;
		resetItems();
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
		if(p == null) {
			if(hasPipe == true) {
				p = removePipe();
				cisternView.update();
				plumber.setInventoryPipe(p);
				game.removeActionPoints();
			}
		} else {
			if(neighbours.contains(p)) {
				boolean removed = removeNeighbour(p);
				if(removed) {
					p.removeNeighbour(this);
					cisternView.update();
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
		if(hasPump == true) {
			p = removePump();
			cisternView.update();
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
		return neighbours;
	}
	
	/**
	 * Publikus metódus, meghívásakor a megadott karakter rákerül a ciszternáról. 
	 * Field-ből örökölt függvény felülírása.
	 * @param: c, a karakter, aki rálép a ciszternára.
	 */
	@Override
	public void onField(Character c) {
		if(currentCharacters.contains(c) == false) { 
			currentCharacters.add(c); 
			cisternView.update();
		}
	}
	
	/**
	 * Publikus metódus, meghívásakor az adott karater lekeról a ciszternáról.
	 * Field-ből örökölt függvény felülírása.
	 * @param: c, karakter, aki lelép a ciszternáról.
	 * @return: boolean, true ha letudott lépni a ciszternáról, false ha nem
	 */
	@Override
	public boolean offField(Character c) {
		if (currentCharacters.contains(c)) { 
			currentCharacters.remove(c); 
			cisternView.update();
			return true;
		}
		return false;
	}

}
