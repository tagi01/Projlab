package skeletonPackage;

public class Plumber extends Character{
	
	//A szerelőnél lévő cső vagy pumpa referenciája
	private BreakableField inventory;
	
	//konstruktor
	public Plumber(Field f, Network n) {
		super(f, n);
		inventory=null;
	}
	/*
	 * Megjavítja az elromlott mezőt, amin éppen a játékos áll
	 */
	public void repair() {
		currentField.getRepaired();
	}
	/*
	 * Az inventory-ból lerakja a csőnek az egyik végét ciszternához, forráshoz vagy pumpához
	 */
	public void placePipe() {
		if(inventory!=null) {
			if(currentField.acceptField(inventory)) {
				currentField.addNeighbour(inventory);
				inventory.addNeighbour(currentField);
			}else {
				System.out.println("Nem sikerult learakni a cso veget");
			}		
		}else {
			System.out.println("Inventory ures");
		}
	}
	/*
	 * Felvesz egy pumpát a ciszternáról
	 */
	public void getPump() {}
	/*
	 * A ciszternán állva meghívható, hogy felvegyen egy csövet
	 */
	public void getPipe() {}
	/*
	 * A szerelő felveszi a cső egyik végét, ami bekerül az inventory-jába
	 */
	public void grabPipe(Pipe p) {}
	/*
	 * Lerak egy pumpát az aktuális cső közepére
	 */
	public void placePump() {}
}
