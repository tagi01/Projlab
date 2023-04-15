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

	/**
	 * Publikus metódus, Field osztály gettere, meghívásakor visszaadja a mezőn álló karakterek listáját
	 * @return ArrayList, amiben a mezőn álló karakterek vannak
	 */
	public ArrayList<Character> getCurrentCharacter() { return currentCharacters; }

	/**
	 * Publikus metódus, Field osztály settere, meghívásakor hozzáad a mezőn álló karakterek listájához
	 * @param newChar új Character referenciája, amely a mezőn fog állni
	 */

	public abstract boolean addNeighbour();

	public abstract void removeNeighbour(Pipe p);

	public void setCurrentCharacters(Character newChar) { currentCharacters.add(newChar); }

	public abstract boolean interact();

	public abstract boolean interact(Pipe from, Pipe to);

	public abstract boolean interactPlumber(Plumber p); // TODO itt még kéne egy paraméter, hogy majd pumpát vagy csövet venne fel

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

	public abstract boolean addNeighbour(Field f);

	public abstract ArrayList<? extends Field> getNeighbours();
}
