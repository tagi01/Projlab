package skeletonPackage;

import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;

/** Pipe osztály */
public class Pipe extends BreakableField {
	// ***********************************************************************************************
	public void setSize(int value) {
		size = value;
	}

	public void setLostWater(int value) {
		lostWater = value;
	}

	public void setWater(int value) {
		water = value;
	}

	public void setCantPuncture(int value) {
		cantPuncture = value;
	}

	public int getCantPuncture() {
		return cantPuncture;
	}
	
	public void setState(StateOfPipe s) {
		state = s;
	}
	
	public StateOfPipe getState() {
		return state;
	}
	
	public int getStateTimer() {
		return stateTimer;
	}
	
	public View getView() {
		return view;
	}

	// ************************************************************************************************
	

	/**
	 * A megjelenítésért felelős view
	 */
	private PipeView view;
	
	/**
	 * Privát integer, a lyukas csőből kifolyó víz mennyiségét tárolja.
	 */
	private int lostWater;

	/**
	 * Privát StateOfPipe, a cső állapotát jelzi
	 */
	private StateOfPipe state;

	/**
	 * Privát integer, amíg nagyobb nullánál, nem lehet kilyukasztani a csövet.
	 */
	private int cantPuncture;

	/**
	 * Privát integer, csúszós, vagy ragadós cső visszakerül normál állapotba amikor
	 * nullára vált
	 */
	private int stateTimer;

	/**
	 * Privát integer, a cső vízbefogadó mértékét tárolja.
	 */
	private int size;

	/**
	 * Privát boolean, megadja, hogy mozgatják-e már bármelyik végét. Értéke true,
	 * ha valamelyik szerelőnél van az egyik vége, false, ha nem mozgatják egyik
	 * végét sem.
	 */
	private boolean taken;

	/**
	 * Privát integer, megadja, hogy a csőben épp mennyi víz van.
	 */
	private int water;

	/**
	 * A cső szomszédait tárolja
	 */
	private ArrayList<Field> neighbours;

	/**
	 * taken settere
	 * 
	 * @param taken az uj ertek
	 */
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	/**
	 * taken gettere
	 * @return taken értéke
	 */
	public boolean getTaken() {
		return taken;
	}

	/**
	 * Konstruktor
	 */
	public Pipe(GamePanel gp, Game g) {
		super(g);
		lostWater = 0;
		size = 1; // default size
		taken = false;
		water = 0;
		neighbours = new ArrayList<Field>();
		state = StateOfPipe.NORMAL;
		view = new PipeView(this, gp);
	}

	/**
	 * Konstruktor
	 * 
	 * @param siz   a cső mérete
	 * @param water a csőben lévő víz mennyisége
	 */
	public Pipe(int siz, int water, GamePanel gp, Game g) {
		super(g);
		lostWater = 0;
		size = siz;
		taken = false;
		this.water = water;
		neighbours = new ArrayList<Field>();
		view = new PipeView(this, gp);
	}

	/**
	 * Hozzáad egy szomszédot a neighbours listához
	 * 
	 * @param f Az új szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean addNeighbour(Field f) {
		if (neighbours.contains(f) || f == null) {
			return false;
		} else {
			neighbours.add(f);
			view.update();
			return true;
		}
	}

	/**
	 * Hozzáad egy szomszédot a neighbours listához
	 * 
	 * @param f Az új szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean addNeighbour(Pipe p) {
		return false;
	}

	/**
	 * Kivesz egy szomszédot a neighbours listából
	 * 
	 * @param f Az eltávolítandó szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Field f) {
		if (neighbours.contains(f) && f != null) {
			neighbours.remove(f);
			view.update();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor
	 * megadja, hogy a karakter ráléphet-e a ciszternára.
	 * 
	 * @return boolean, true, ha ráléphet a ciszternára, false ha nem
	 */
	@Override
	public boolean acceptCharacter() {
		if (currentCharacters.size() == 0 && taken == false) {
			return true;
		}
		return false;
	}

	/**
	 * Publikus metódus, meghívásakor a pumpa kimenetén lévő csőbe odaadódik a
	 * paraméterben lévő egész szám.
	 * 
	 * @param amount, integer, amennyi víz átkerül a pumpa kimenetén lévő csőbe
	 */
	public void addWater(int amount) {
		// nem kell megvizsgalni, hogy a cso tulcsordulna, mert csak annyi vizet pumpal
		// majd a pumpa(amount) amennyit tud meg ahhoz,
		// hogy cso ne csorduljon tul
		if (isBroken) {
			lostWater += amount;
		} else {
			water += amount;
			view.update();
		}
	}

	/**
	 * Publikus metódus, megadja, hogy pontosan mennyi vizet tud még befogadni.
	 * 
	 * @return integer, amennyi vizet be tud még fogadni
	 */
	public int getCapacity() {
		return size - water;
	}

	/**
	 * A metódus meghívásakor maximum a paraméterként kapott vízzel kevesebb lesz a
	 * csőben.
	 * 
	 * @param amount, integer, ennyit kér tőle a pumpa
	 * @return integer, ténylegesen ennyit tudott ebből adni.
	 */
	public int takeWater(int amount) {
		if (water == 0) {
			return 0;
		} else if (amount < water) {
			water -= amount;
			return amount;
		} else if (amount == water) {
			water = 0;
			view.update();
			return amount;
		} else {
			// azert kell egy seged valtozo, mert ha a vizemennyiseget meg az elott
			// megvaltoztatjuk mielott viszakuldjuk false ertek lesz,
			// visszeteres utan, pedig nem tudjuk megvaltoztatni igy nem csokken majd a
			// csoben levo vizmennyiseg
			int temp_water = water;
			water = 0;
			view.update();
			return temp_water;
		}
	}

	/**
	 * Publikus metódus, meghíváskor visszaadja, a csőben lévő víz mennyiségét
	 * 
	 * @return integer, a csőben lévő víz mennyisége
	 */
	public int getWater() {
		return water;
	}

	/**
	 * Visszaadja a szomszédokat
	 * 
	 * @return a pipe szomszédai
	 */
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}

	/**
	 * Kivesz egy szomszédot a neighbours listából
	 * 
	 * @param p Az eltávolítandó szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Pipe p) {
		return false;
	}

	/**
	 * A szerelő használja a képességét, lerak egy pumpát
	 * 
	 * @param p    a szerelő
	 * @param pump a lerakni való pumpa
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public void interactPlumber(Plumber p, Pump pump) {
		if (!isBroken) {
			network.addPump(pump, this);
			p.setInventoryPump(null);
			game.removeActionPoints();
			view.update();
		}
	}

	/**
	 * Csúszóssá teszi a csövet
	 */
	@Override
	public void interact(Saboteur s) {
		if (state == StateOfPipe.NORMAL) {
			state = StateOfPipe.SLIPPERY;
			stateTimer = 5;
			game.removeActionPoints();
			view.update();
		}
	}

	/**
	 * @param i megadja, hogy mit csináljon a függvény, kilyukasztja(1), vagy
	 *          ragadóssá teszi(2) a csövet
	 */
	@Override
	public void interact(int i) {
		if (i == 1) {
			if (!isBroken) {
				if (cantPuncture == 0) {
					isBroken = true;
					game.removeActionPoints();
					view.update();
				}
			}
		} else if (i == 2) {
			if (state == StateOfPipe.NORMAL) {
				state = StateOfPipe.SETSTICKY;
				game.removeActionPoints();
				view.update();
			}
		}
	}

	/**
	 * Field-ből származó metódus megvalósítása. A kifolyt víz mennyiségét hozzáadja
	 * a szabotőrök pontjaihoz. cantPuncture, stateTimer értékét csökkenti.
	 */
	@Override
	public void flowWater() {
		game.giveSaboteurPoint(lostWater);
		lostWater = 0;
		if (cantPuncture > 0) {
			cantPuncture -= 1;
		}
		if (stateTimer > 0) {
			stateTimer -= 1;
			if (stateTimer == 0) {
				state = StateOfPipe.NORMAL;
				view.update();
			}
		}
	}

	/**
	 * A karakter lelépését valósítja meg
	 * @param c a lelépő karakter
	 */
	@Override
	public boolean offField(Character c) {
		if (state == StateOfPipe.STICKY) {
			return false;
		} else if (state == StateOfPipe.SETSTICKY) {
			state = StateOfPipe.STICKY;
			stateTimer = 5;
		}
		if (currentCharacters.contains(c)) {
			currentCharacters.remove(c);
			view.update();
		}
		return true;
	}

	/**
	 * Field metódusának felüldefiniálása. Ha csúszós a cső, akkor a cső egyik
	 * szomszédjára kerül a karakter.
	 */
	@Override
	public void onField(Character c) {
		if(taken) {
			return;
		} else if (state == StateOfPipe.SLIPPERY) {
			Field f = neighbours.get(0);// ThreadLocalRandom.current().nextInt(0, 2));
			f.onField(c);
			c.setCurrentField(f);
		} else if (currentCharacters.contains(c) == false) {
			currentCharacters.add(c);
			view.update();
		}

	}
	
	@Override
	public void interact(Plumber p) {
		if(isBroken) {
			isBroken = false;
			cantPuncture = 10;
			game.removeActionPoints();
			view.update();
		}
	}
}
