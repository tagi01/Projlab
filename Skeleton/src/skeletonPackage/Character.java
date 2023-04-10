package skeletonPackage;

public abstract class Character {
	//Az aktuális mező referenciája, amin a karakter áll
	private Field currentField;
	//Az aktuális hálózat referenciája
	private Network network;
	/*
	 * Lépteti a játékost a pályán a paraméterként megadott mezőre
	 */
	public void move(Field f) {}
	/*
	 * Beállítja az adott pumpa mezőn, hogy melyik csőből melyik csőbe pumpáljon a pumpa
	 */
	public void setPump(Pipe from , Pipe to) {}
	
}
