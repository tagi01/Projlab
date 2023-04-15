package skeletonPackage;

/** Plumber osztály */
public class Plumber extends Character {
	/**
	 * Privát, egy Pipe referenciát tárol, amely éppen a szerelő birtokában van.
	 */
	private Pipe inventoryPipe;
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
		inventoryPump = null;
	}
	
	/*
	// nem kell, nem?
	@Override
	public void addInventory(BreakableField bf) {
		inventory = bf;
	}

	// nem kell, nem?
	@Override
	public void removeInventory() {
		inventory = null;
	}
	*/

	/**
	 * Publikus metódus, meghívásakor a szerelő megjavítja az elromlott mezőt, amin éppen a játékos áll.
	 */
	public void repair() {
		currentField.getRepaired();
		//TODO:karakter interakcio, hogy mit szeretne csinalni mivel
	}

	/**
	 * Publikus metódus, meghívásakor az inventory-ból lerakja a csőnek az egyik végét ciszternához, forráshoz vagy pumpához.
	 */
	public void placePipe() {
		if(inventoryPipe != null) {
			if(currentField.acceptField(inventoryPipe)) {
				currentField.addNeighbour(inventoryPipe);
				//TODO: interakcio
				inventoryPipe.addNeighbour(currentField);	// (szekvencia diagramon nem igy volt)
				inventoryPipe = null;
			} else {
				System.out.println("Nem sikerult learakni a csovet");
			}		
		} else {
			System.out.println("Nincs cso az inventory-ban");
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy pumpát a ciszternáról.
	 */
	public void getPump() {
		if(inventoryPump == null && inventoryPipe == null) {
			inventoryPump = currentField.removePump();
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy csövet.
	 * (Ciszternán állva ajánlott meghívni, csak onnan lehet felvenni)
	 */
	public void getPipe() {
		if(inventoryPipe == null && inventoryPump == null) {
			inventoryPipe = currentField.removePipe();
		}
	}

	/**
	 * Publikus metódus, meghívásakor a szerelő felveszi a cső egyik végét, ami bekerül az inventory-jába.
	 * @param p, Pipe típusú objektum referenciája, amelyik csövet vesszük fel
	 */
	public void grabPipe(Pipe p) {
		if(inventoryPipe == null && inventoryPump == null) {
			currentField.removeNeighbour(p);
			inventoryPipe = p;
		}
	}

	/**
	 * Publikus metódus, meghívásakor lerak egy pumpát az aktuális cső közepére.
	 */
	public void placePump() {
		if(inventoryPump != null) {
			network.addPump(inventoryPump, currentField);
			inventoryPump = null;
		}
	}
}
