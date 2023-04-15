package skeletonPackage;

public class Plumber extends Character {
	
	//A szerelőnél lévő cső vagy pumpa referenciája
	private BreakableField inventory;
	
	//konstruktor
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
	
	/*
	 * Megjavítja az elromlott mezőt, amin éppen a játékos áll
	 */
	public void repair() {
		currentField.getRepaired();
		//TODO:karakter interakcio, hogy mit szeretne csinalni mivel
	}
	
	/*
	 * Az inventory-ból lerakja a csőnek az egyik végét ciszternához, forráshoz vagy pumpához
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
	
	/*
	 * Felvesz egy pumpát a ciszternáról
	 */
	public void getPump() {
		if(inventory == null) {
			inventory = currentField.removePump();	
		}
	}
	
	/*
	 * A ciszternán állva meghívható, hogy felvegyen egy csövet
	 */
	public void getPipe() {
		if(inventory == null) {
			inventory = currentField.removePipe();
		}
	}
	
	/*
	 * A szerelő felveszi a cső egyik végét, ami bekerül az inventory-jába
	 */
	public void grabPipe(Pipe p) {
		if(inventory == null) {
			currentField.removeNeighbour(p);
			inventory = p;
		}
	}
	
	/*
	 * Lerak egy pumpát az aktuális cső közepére
	 */
	public void placePump() {
		if(inventory != null) {		// && Pump :(
			network.addPump(inventory, currentField);
			inventory = null;
		}
	}
}
