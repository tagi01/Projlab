package skeletonPackage;
import java.util.ArrayList;

/** Field absztrakt osztály */
public abstract class Field {

// ATTRIBUTUMOK
	/**
	 * A mezőn aktuálisan tartózkodó karakterek referenciájának listája
	 */
	protected ArrayList<Character> currentCharacters = new ArrayList<Character>();

	/**
	 * Network típusú referenciát tárol, vagyis a mező melyik hálózat része
	 */
	protected Network network;

	/** A játékra mutató referencia */
	protected Game game;

// KONSTRUKTOR
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
	 * Getter, megadja, hogy a mezőt melyik játékban használják
	 * @return Game típus
	 */
	public Game getGame() { return game; }

	/**
	 * Setter, beállítja, melyik játék része.
	 * @param game, Game referencia, amelyik játékban van a mező
	 */
	public void setGame(Game game) { this.game = game; }

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

	/** Cső kilyukasztásához (1), cső ragadóssá tétele (2) */
	public void interact(int n) {}

	/** Pumpa átállításához */
	public void interact(Pipe p1, Pipe p2) {}

	/** Cső csúszóssá tételéhez */
	public void interact(Saboteur s) {}

	/** BreakableField megjavításához (pump vagy pipe) */
	public  void interact(Plumber p) {}

	/** Cső lehelyezése, felvétele */
	public void interactPlumber(Plumber p, Pipe pipe) {}

	/** Pumpa lehelyezése, felvétele */
	public void interactPlumber(Plumber p, Pump pump) {}

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

	// FLOW WATER

	/** Network-kel interakció, ami ha meghívja, akkor a vizet valamely módon mozgatja.
	 *  Forrás vizet ad, pumpa vizet vesz el/ad csöveitől, ciszterna begyűjti a vizet.
	 */
	public abstract void flowWater();
}
