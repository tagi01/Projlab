package skeletonPackage;

import java.util.*;

public abstract class Character {
	//Az aktuális mező referenciája, amin a karakter áll
	protected Field currentField;
	//Az aktuális hálózat referenciája
	protected Network network;
	
	//konstruktor
	public Character(Field f, Network n) {
		currentField=f;
		network=n;
	}
	//setter Field-re
	public void setCurrentField(Field f) {currentField=f;}
	//getter Fieldre
	public Field getField() {return currentField;}
	/*
	 * Lépteti a játékost a pályán a paraméterként megadott mezőre
	 */
	public void move(Field f) {
		ArrayList<Field> field = new ArrayList<Field>(currentField.getNeighbours());
			if(field.contains(currentField)){
				if(f.acceptCharacter()) {
					currentField.setCurrentCharacter(null);
					f.setCurrentCharacter(this);
					currentField=f;
				}else {
					System.out.println("Nem tudsz rálépni a mezoe, mert már állnak rajta");
				}		
			}else {
				System.out.println("nem szomszédos s valasztott mezo, igy nem tudsz ralepni");
			}
	}
	/*
	 * Beállítja az adott pumpa mezőn, hogy melyik csőből melyik csőbe pumpáljon a pumpa
	 */
	public void setPump(Pipe from , Pipe to) {
		if(currentField.setPump(from,to)) {
			System.out.println("Sikerult atallitani a pumpat");
		}else {
			System.out.println("Nem Sikerult beallitani a pumpat");
		}
	}
	
}
