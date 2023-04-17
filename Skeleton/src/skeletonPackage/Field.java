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

	/**
	 * Field paraméter nélküli konstruktora.
	 */
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

	/**
	 * Getter, megadja, hogy a mező melyik hálózat tagja.
	 * @return Network típus
	 */
	public Network getNetwork() { return network; }

	/**
	 * Setter, beállítja, melyik hálózat része.
	 * @param n, Network objektum, amelyik hálózatban van a mező
	 */
	public void setNetwork(Network n) { network = n; }

	/**
	 * Absztrakt metódus, meghívásakor az implementált metódusokban visszaad egy listát, amelyben a mező szomszédjait adja meg.
	 * @return ArrayList, a leszármazottaktól függ a visszatérési érték típusa
	 */
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
	/**
	 * Publikus absztrakt metódus, a paraméterben kapott mezőt hozzáadja a szomszédsági listához.
	 * @param f, Field típusú objektum, amelyet hozzáadunk a szomszédsági listához
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public abstract boolean addNeighbour(Field f);

	/**
	 * Publikus absztrakt metódus, a paraméterben kapott mezőt hozzáadja a szomszédsági listához.
	 * @param p, Pipe típusú objektum, amelyet hozzáadunk a szomszédsági listához
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public abstract boolean addNeighbour(Pipe p);

	/**
	 * Publikus absztrakt metódus, a paraméterben kapott mezőt törölnénk a szomszédsági listából.
	 * @param f, Field típusú objektum, amelyet törlünk a szomszédsági listából
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public abstract boolean removeNeighbour(Field f);

	/**
	 * Publikus absztrakt metódus, a paraméterben kapott mezőt törölnénk a szomszédsági listából.
	 * @param p, Pipe típusú objektum, amelyet törlünk a szomszédsági listából
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public abstract boolean removeNeighbour(Pipe p);

	// INTERACTS

	/**
	 * Publikus metódus, meghívásakor a szerelő interakcióba tud lépni leszármazottnak specifikus módon.
	 * @param plumber, amelyik szerelő meghívja ezt az interakciót
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean interact(Plumber plumber) { return false; }

	/**
	 * Publikus metódus, meghívásakor a szabotőr interakcióba tud lépni leszármazottnak specifikus módon.
	 * @param saboteur, amelyik szabotőr meghívja az interakciót
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean interact(Saboteur saboteur) { return false; }

	/**
	 * Publikus metódus, meghívásakor a karakter interakcióba tud lépni a leszármazottnak specifikus módon.
	 * @param from, Pipe objektum, valamilyen csövet lecserél
	 * @param to, Pipe típusú objektum, valamilyen csövet erre cserél
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean interact(Pipe from, Pipe to) { return false; }

	/**
	 * Publikus metódus, meghívásakor a szerelő interakcióba lép leszármazottnak specifikus módon.
	 * @param p, amelyik szerelő meghívja ezt az interakciót
	 * @param pipe, cső, amelyik szükséges az interakcióhoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean interactPlumber(Plumber p, Pipe pipe) { return false; }

	/**
	 * Publikus metódus, meghívásakor a szerelő interakcióba lép leszármazottnak specifikus módon.
	 * @param p, amelyik szerelő meghívja ezt az interakciót
	 * @param pump, pumpa, amelyik szükséges az interakcióhoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
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
