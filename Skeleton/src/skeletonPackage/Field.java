package skeletonPackage;
import java.util.ArrayList;
import java.util.Collection;
/** Field absztrakt osztály */
public abstract class Field {

	/**
	 * Privát, a mezőn aktuálisan tartózkodó karakterek referenciájának listája
	 */
	protected ArrayList<Character> currentCharacters = new ArrayList<Character>();

	/**
	 * Absztrakt metódus. Megadja, hogy egy karakter ráléphet-e a mezőre. True ha igen, false ha nem.
	 * @return boolean, true ha ráléphet, false ha nem
	 */
	public boolean acceptCharacter() {
		return true;
	}

	/**
	 * Absztrakt metódus, egy Field-et hozzá lehet-e csatlakoztatni a meghívott mezőhöz.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField( Field f) {
		return true;
	}

	public ArrayList<Character> getCurrentCharacter() { return currentCharacters; }

	public void setCurrentCharacters(Character newChar) { currentCharacters.add(newChar); }


	/**
	 * Egy mezőnek eltávolítja az paraméterként kapott mezőt, mint szomszédot
	 * @param f, Field-ből leszármazó típusú változó, amelyet kitörölnénk a meghívott mező szomszédai közül
	 * @return boolean, true ha sikeres volt a törlés, false ha nem
	 */

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

	public abstract ArrayList<? extends Field> getNeighbours();

}
