package skeletonPackage;

/** Plumber osztály */
public class Plumber extends Character {

	/**
	 * Privát, egy BreakableField-ből származó objektum referenciát tárol, amely éppen a szerelő birtokában van. (Cső vagy pumpa)
	 */
	private BreakableField inventory;

	/**
	 * Publikus metódus, Plumber kétparaméteres konstruktora, hasonlóan a Character konstruktorához.
	 * Beállítja, hogy a szerelőnek létrehozásakor nincs az inventory-ban semmi.
	 * @param f, Field-ből származót típusú mező, amelyen a karakter áll
	 * @param n, Network, amely hálózatban a karakter és a mező van
	 */
	public Plumber(Field f, Network n) {
		super(f, n);
		inventory = null;
	}
	
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
		if(inventory != null) {
			if(currentField.acceptField(inventory)) {
				currentField.addNeighbour(inventory);
				//TODO: interakcio
				inventory.addNeighbour(currentField);	// (szekvencia diagramon nem igy volt)
				inventory = null;
			} else {
				System.out.println("Nem sikerult learakni az inventory tartalmat");
			}		
		} else {
			System.out.println("Az inventory ures");
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy pumpát a ciszternáról.
	 */
	public void getPump() {
		if(inventory == null) {
			inventory = currentField.removePump();
		}
	}

	/**
	 * Publikus metódus, meghívásakor felvesz egy csövet.
	 * (Ciszternán állva ajánlott meghívni, csak onnan lehet felvenni)
	 */
	public void getPipe() {
		if(inventory == null) {
			inventory = currentField.removePipe();
		}
	}

	/**
	 * Publikus metódus, meghívásakor a szerelő felveszi a cső egyik végét, ami bekerül az inventory-jába.
	 * @param p, Pipe típusú objektum referenciája, amelyik csövet vesszük fel
	 */
	public void grabPipe(Pipe p) {
		if(inventory == null) {
			currentField.removeNeighbour(p);
			inventory = p;
		}
	}

	/**
	 * Publikus metódus, meghívásakor lerak egy pumpát az aktuális cső közepére.
	 */
	public void placePump() {
		if(inventory != null) {		// && Pump :(
			network.addPump(inventory, currentField);
			inventory = null;
		}
	}
}
