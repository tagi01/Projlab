package skeletonPackage;
import java.util.ArrayList;

/** Field absztrakt osztály */
public abstract class Field {

// PRIVAT TAGOK
	/**
	 * Privát, a mezőn aktuálisan tartózkodó karakterek referenciájának listája
	 */
	protected ArrayList<Character> currentCharacters = new ArrayList<Character>();

	/**
	 * Privát, Network típuső referenciát tárol, vagyis a mező melyik hálózat része
	 */
	protected Network network;
	
	public Field() {
		currentCharacters = new ArrayList<Character>();
	}

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

	public Network getNetwork() { return network; }
	public void setNetwork(Network n) { network = n; }

	public abstract ArrayList<? extends Field> getNeighbours();

// METODUSOK
	// ACCEPT
	/**
	 * Publikus metódus. Megadja, hogy egy karakter ráléphet-e a mezőre. True ha igen, false ha nem.
	 * @return boolean, true ha ráléphet, false ha nem
	 */
	public boolean acceptCharacter() {
		Skeleton.printMethod(this, "acceptCharacter");
		return true;
	} // ugye ez csak a Pipe-nal valtozik

	/**
	 * Publikus metódus, egy Field-et hozzá lehet-e csatlakoztatni a meghívott mezőhöz.
	 * @param f, Field-ből leszármazó típusú változó, amelyet hozzácsatlakoztatnánk a meghívott mezőhöz
	 * @return boolean, true ha a paraméter hozzácsatlakoztatható, false ha nem
	 */
	public boolean acceptField(Field f) {
		Skeleton.printMethod(this, "acceptField");
		return true;
	}

	// ADD AND REMOVE
	public abstract boolean addNeighbour(Field f);

	public abstract boolean addNeighbour(Pipe p);

	public abstract boolean removeNeighbour(Field f);

	public abstract boolean removeNeighbour(Pipe p);

	// INTERACTS
	public boolean interact(Plumber plumber) { return false; }

	public boolean interact(Saboteur saboteur) { return false; }

	public boolean interact(Pipe from, Pipe to) { return false; }

	public boolean interactPlumber(Plumber p, Pipe pipe) { return false; }

	public boolean interactPlumber(Plumber p, Pump pump) { return false; }

	// MOVE ON FIELD
	/**
	 * A karaktert a mezőre teszi, bekerül a mezőn lévő karakterek listájába.
	 * @param c Character, aki a meghívott mezőre lépne
	 */
	public void onField(Character c) {
		Skeleton.printMethod(this, "onField");
		if(currentCharacters.contains(c) == false) { currentCharacters.add(c); }
	}

	/**
	 * A karaktert leveszi a mezőről, kikekerül a mezőn lévő karakterek listájából.
	 * @param c Character, aki a meghívott mezőre lelépne
	 */
	public void offField(Character c) {
		Skeleton.printMethod(this, "offField");
		if (currentCharacters.contains(c)) { currentCharacters.remove(c); }
	}
}
