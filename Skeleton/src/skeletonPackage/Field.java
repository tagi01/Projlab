package skeletonPackage;
import java.util.ArrayList;

/** Field absztrakt osztály */
public abstract class Field {

// PRIVAT TAGOK
	/**
	 * Privát, a mezőn aktuálisan tartózkodó karakterek referenciájának listája
	 */
	protected ArrayList<Character> currentCharacters = new ArrayList<Character>();

// GETTER, SETTER

	/**
	 * Publikus metódus, Field osztály gettere, meghívásakor visszaadja a mezőn álló karakterek listáját
	 * @return ArrayList, amiben a mezőn álló karakterek vannak
	 */
	public ArrayList<Character> getCurrentCharacter() { return currentCharacters; }

	/**
	 * Publikus metódus, Field osztály settere, meghívásakor hozzáad a mezőn álló karakterek listájához
	 * @param newChar új Character referenciája, amely a mezőn fog állni
	 */
	public void setCurrentCharacters(Character newChar) { currentCharacters.add(newChar); }

// METODUSOK
	// ACCEPT
	/**
	 * Publikus metódus. Megadja, hogy egy karakter ráléphet-e a mezőre. True ha igen, false ha nem.
	 * @return boolean, true ha ráléphet, false ha nem
	 */
	public boolean acceptCharacter() {
		return true;
	} // ugye ez csak a Pipe-nal valtozik

	/**
	 * Publikus metódus, egy Field-et hozzá lehet-e csatlakoztatni a meghívott mezőhöz.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField( Field f) {
		return true;
	}

	// ADD AND REMOVE
	public abstract boolean addNeigbhour(Field f);

	public abstract boolean addNeighbour(Pipe p);

	public abstract boolean removeNeighbour(Field f);

	public abstract boolean removeNeighbour(Pipe p);

	// INTERACTS
	public abstract boolean interact(Plumber plumber);

	public abstract boolean interact(Saboteur saboteur);

	public abstract boolean interact(Pipe from, Pipe to);

	public abstract boolean interactPlumber(Plumber p, Pipe pipe);

	public abstract boolean interactPlumber(Plumber p, Pump pump);

	// MOVE ON FIELD
	/**
	 * A karaktert a mezőre teszi, bekerül a mezőn lévő karakterek listájába.
	 * @param c Character, aki a meghívott mezőre lépne
	 */
	public void onField(Character c) {
		if(currentCharacters.contains(c) == false) { currentCharacters.add(c); }
	}

	/**
	 * A karaktert leveszi a mezőről, kikekerül a mezőn lévő karakterek listájából.
	 * @param c Character, aki a meghívott mezőre lelépne
	 */
	public void offField(Character c) {
		if (currentCharacters.contains(c)) { currentCharacters.remove(c); }
	}
}
