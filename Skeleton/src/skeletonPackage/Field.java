package skeletonPackage;
import java.util.ArrayList;
/** Field absztrakt osztály */
public abstract class Field {

	/**
	 * Privát, a szomszédos mezők referenciáját tároló lista
	 */
	private ArrayList<Field> neighbours = new ArrayList<Field>();

	/**
	 * Privát, a mezőn aktuálisan tartózkodó karakterek referenciájának listája
	 */
	private ArrayList<Character> currentCharacters = new ArrayList<Character>();

	/**
	 * Absztrakt metódus. Megadja, hogy egy karakter ráléphet-e a mezőre. True ha igen, false ha nem.
	 * @return boolean, true ha ráléphet, false ha nem
	 */
	public abstract boolean acceptCharacter();

	/**
	 * Absztrakt metódus, egy Field-et hozzá lehet-e csatlakoztatni a meghívott mezőhöz.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public abstract boolean acceptField(Field f);

	// TODO getterek és setterek átnézése
	public ArrayList<Field> getNeighbours() { return neighbours; }

	public ArrayList<Character> getCurrentCharacter() { return currentCharacters; }

	public void setCurrentCharacters(Character newChar) { currentCharacters.add(newChar); }

	/**
	 * Hozzáadja a paraméterben kapott mezőt a szomszédjainak listájához
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzáadnánk a meghívott mező szomszédaihoz
	 * @return boolean, true ha sikeres volt a hozzáadás, false ha nem
	 */
	public boolean addNeighbour(Field f) {
		if(neighbours.contains(f)) { return false; }
		else {
			neighbours.add(f);
			return true;
		}
	}

	/**
	 * Egy mezőnek eltávolítja az paraméterként kapott mezőt, mint szomszédot
	 * @param f, Field-ből leszármazó típusú változó, amelyet kitörölnénk a meghívott mező szomszédai közül
	 * @return boolean, true ha sikeres volt a törlés, false ha nem
	 */
	public boolean removeNeighbour(Field f) {
		if(neighbours.contains(f)) {
			neighbours.remove(f);
			return true;
		} else { return true; }
	}

	/**
	 * A régi szomszédos mezőt kicseréli egy új mezőre a szomszédok listájában.
	 * @param oldField, Field-ből leszármazó típusú változó, amelyik mezőt szerenténk kicserélni egy másikra
	 * @param newField, Field-ből leszármazó típusú változó, amelyik mezőre szeretnénk kicserélni a régit
	 */
	public void changeNeighbour(Field oldField, Field newField) {
		// akkor kell lecserélni, ha az oldField benne van, a newField még nincs
		if (neighbours.contains(oldField)== true || neighbours.contains(newField)== false) {
			neighbours.remove(oldField);
			neighbours.add(newField);
			//return true;
		} //else { //return false; }
		// TODO changeNeighbour visszatérési értéke ne legyen boolean?
	}

	/**
	 * A karaktert a mezőre teszi, bekerül a mezőn lévő karakterek listájába.
	 * @param c Character, aki a meghívott mezőre lépne
	 */
	public void onField(Character c) {
		if(currentCharacters.contains(c) == false) { currentCharacters.add(c); }
		// TODO visszateresi ertek itt is
	}

	/**
	 * A karaktert leveszi a mezőről, kikekerül a mezőn lévő karakterek listájából.
	 * @param c Character, aki a meghívott mezőre lelépne
	 */
	public void offField(Character c) {
		if (currentCharacters.contains(c)) { currentCharacters.remove(c); }
		// TODO visszateresi ertek itt is, itt is
	}
}
