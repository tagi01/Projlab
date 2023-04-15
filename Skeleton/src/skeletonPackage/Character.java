package skeletonPackage;

import java.util.ArrayList;

/** Character osztály */
public abstract class Character {

	/**
	 * Privát Field típusú referencia, amely tárolja, hogy ezen a mezőn van épp a karakter.
	 */
	protected Field currentField;

	/**
	 * Privát Network típusú referencia, amely tárolja, hogy ebben a hálózatban van a karakter.
	 */
	protected Network network;

	/**
	 * Publikus metódus, Character kétparaméteres konstruktora, amellyel beállítható az aktuális mező és hálózat.
	 * @param f, Field-ből származót típusú mező, amelyen a karakter áll
	 * @param n, Network, amely hálózatban a karakter és a mező van
	 */
	public Character(Field f, Network n) {
		currentField = f;
		network = n;
	}

	/**
	 * Publikus metódus, meghívásakor beállítja a paraméterben kapott mezőt, ezen áll a karakter.
	 * @param f, olyan Field-ből származó típus, amely mezőn áll a karakter
	 */
	public void setCurrentField(Field f) { currentField = f; }

	/**
	 * Publikus metódus, meghívásakor megadja, melyik mezőn áll a karakter.
	 * @return Field-ből leszármazó típusú objektum referenciáját, amelyen a karakter áll.
	 */
	public Field getField() { return currentField; }

	/**
	 * Publikus metódus, amely meghívásával a karaktert mozgatni lehet.
	 * @param f Field típusú mező, amelyre szerente lépni
	 */
	public void move(Field f) {
		ArrayList<Field> field = new ArrayList<Field>(currentField.getNeighbours());
			if(field.contains(currentField)){
				if(f.acceptCharacter()) {
					currentField.setCurrentCharacters(null);
					f.setCurrentCharacters(this);
					currentField=f;
				} else {
					System.out.println("Nem tudsz ralepni a mezoe, mert mar allnak rajta");
				}		
			} else {
				System.out.println("nem szomszedos s valasztott mezo, igy nem tudsz ralepni");
			}
	}

	/**
	 * Beállítja az adott pumpa mezőn, hogy melyik csőből melyik csőbe pumpáljon a pumpa.
	 * @param from Pipe típusú mező, amelyik csőből pumpál majd a pumpa
	 * @param to Pipe típusú mező, amelyik csőbe fog a pumpa pumpálni
	 */
	public void setPump(Pipe from , Pipe to) {
		if(currentField.interact(from,to)) {
			//TODO:karakter interakcio 
			System.out.println("Sikerult atallitani a pumpat");
		} else {
			System.out.println("Nem Sikerult beallitani a pumpat");
		}
	}
	
	
	/*
	 * Plumber inventory miatt kell, mivel a network csak charactereket tarol
	 */
	public void removeInventory() {}
	public void addInventory(BreakableField bf) {}
	
}
