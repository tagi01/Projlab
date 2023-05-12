package skeletonPackage;

/** Plumber osztály */
public class Plumber extends Character {
	//***************************************************************************
	@Override
	public Pump getInventoryPump() {return inventoryPump;}
	public Pipe getInventoryPipe() {return inventoryPipe;}
	public int getPipeEnds() {return pipeEnds;}
	//***************************************************************************
	/**
	 * Privát, egy Pipe referenciát tárol, amely éppen a szerelő birtokában van.
	 */
	private Pipe inventoryPipe;
	/**
	 * Privát, azt jelzi, hogy a szerelőnél lévő csőnek hány vége van nála.
	 */
	private int pipeEnds;
	/**
	 * Privát, egy Pump referenciát tárol, amely éppen a szerelő birtokában van.
	 */
	private Pump inventoryPump;
	
	/**
	 * Publikus metódus, Plumber kétparaméteres konstruktora, hasonlóan a Character konstruktorához.
	 * Beállítja, hogy a szerelőnek létrehozásakor nincs az inventory-ban semmi.
	 * @param f, Field-ből származót típusú mező, amelyen a karakter áll
	 * @param n, Network, amely hálózatban a karakter és a mező van
	 */
	public Plumber(Field f, Network n) {
		super(f, n);
		inventoryPipe = null;
		pipeEnds = 0;
		inventoryPump = null;
	}
	
	/**
	 * A Plumber inventoryPump-nak az értékét beállítja a paraméterként kapott pumpára 
	 * @param p a beállítandó pumpa
	 * @return ha beállítja arra az étékre, akkor igazzal tér vissza, ha nem tudja beállítani, akkor hamissal
	 */
	@Override
	public boolean setInventoryPump(Pump p) {
		if(p == null || (inventoryPump == null && inventoryPipe == null)) {
			inventoryPump = p;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * A Plumber inventoryPipe-nak az értékét beállítja a paraméterként kapott csőre 
	 * @param p a beállítandó cső
	 * @return ha beállítja arra az étékre, akkor igazzal tér vissza, ha nem tudja beállítani, akkor hamissal
	 */
	public boolean setInventoryPipe(Pipe p) {		
		if(p == null) {		// egy csővég eltávolítását jelzi
			if(pipeEnds > 0) {
				pipeEnds--;
				if(pipeEnds == 0) {
					inventoryPipe.setTaken(false);
					inventoryPipe = p;
				}
			}
			return true;	// akkor is, ha null volt alapból?
		}
		if(inventoryPipe == p/* && pipeEnds == 1?*/) {
			pipeEnds = 2;
			return true;
		}
		if(inventoryPipe == null && inventoryPump == null) {
			inventoryPipe = p;
			inventoryPipe.setTaken(true);
			pipeEnds = 1;
			return true;
		}
		return false;
	}

	/**
	 * Publikus metódus, meghívásakor a szerelő megjavítja az elromlott mezőt, amin éppen a játékos áll.
	 */
	public void repair() {
		currentField.interact(this);
	}

	/**
	 * Publikus metódus, meghívásakor az inventory-ból lerakja a csőnek az egyik végét ciszternához, forráshoz vagy pumpához.
	 */
	public void placePipe() {
		if(inventoryPipe != null) {
			currentField.interactPlumber(this, inventoryPipe);
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy pumpát a ciszternáról.
	 */
	public void getPump() {
		if(inventoryPump == null && inventoryPipe == null) {
			currentField.interactPlumber(this, inventoryPump);
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy csövet.
	 * (Ciszternán állva ajánlott meghívni, csak onnan lehet felvenni)
	 */
	public void getPipe() {
		if(inventoryPipe == null && inventoryPump == null) {
			currentField.interactPlumber(this, inventoryPipe);
		}
	}

	/**
	 * Publikus metódus, meghívásakor a szerelő felveszi a cső egyik végét, ami bekerül az inventory-jába.
	 * @param p, Pipe típusú objektum referenciája, amelyik csövet vesszük fel
	 */
	public void grabPipe(Pipe p) {
		if(inventoryPipe == null && inventoryPump == null) {
			currentField.interactPlumber(this, p);
		}
	}

	/**
	 * Publikus metódus, meghívásakor lerak egy pumpát az aktuális cső közepére.
	 */
	public void placePump() {
		if(inventoryPump != null) {
			currentField.interactPlumber(this, inventoryPump);
		}
	}
}
