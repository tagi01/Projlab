package skeletonPackage;

import java.util.List;

public abstract class Field {
	//szomszédokat egy fieldnek tárolo lista
	protected List<Field> neighbours;
	//A mezon aktuálisan álló karaktert
	protected Character currentCharacter;
	
	
	//TODO: Konstruktorok+
	
	/*
	 *Egy logikai változó a visszatérési értéke. Character ráléphet-e a mezőre, True ha igen 
	 */
	public boolean acceptCharacter() {
		if(currentCharacter != null) {
			return false;
		}
		return true;
	}
	/*
	 *Egy Field-et hozzá lehet-e csatlakoztatni a meghívott
	 *mezőhöz. Absztrakt metódus, logikai változó a visszatérési értéke, True ha igen, False
	 *ha nem
	 */
	public abstract boolean acceptField(Field f);
	//visszaadja egy Fieldnek a szomszédsági listáját
	public List<Field> getNeighbours(){return neighbours;}
	//az aktualis karakter referenciajat adja vissza, aki a mezon all
	public Character getCurrentCharacter() {return currentCharacter;}
	//beallitja az aktualis karaktert, aki eppen ralepett a mezore
	public void setCurrentCharacter(Character newChar) {currentCharacter=newChar;}
	
	
	/*
	 *Hozzáadja a paraméterben kapott mezőt a szomszédjainak listájához, (neighbours lista)
	 */
	public boolean addNeighbour(Field f) {
		//TODO:......
		return true;
		}
	/*
	 * Egy mezőnek eltávolítja az paraméterként kapott mezőt, mint szomszédot (a neighbours listából)
	 */
	public boolean removeNeighbour(Field f) {
		//TODO
		return true;
		}
	/*
	 * A régi szomszédos mezőt kicseréli egy új mezőre a szomszédok listájában
	 */
	public void changeNeighbour(Field oldfield, Field newfield) {}
	/*
	 * A Charactert a mezőre teszi, bekerül a currentCharacters listába
	 */
	public void onField(Character c) {}
	/*
	 * A Charactert leveszi a mezőről, kiveszi a currentCharacters listából
	 */
	public void offField(Character c) {}
	
	
	
	
	
	/*
	 *  a pumpa beállításához, majd a pumpa osztály overrideolja
	 */
	public boolean setPump(Pipe from, Pipe to) {
		System.out.println("Pumpanak tudod beallitani");
		return false;
	}
	
	/*
	 * A BreakableField metodusai miatt kellettek
	 */
	public boolean getRepaired() {
		System.out.println("Nem sikerult javitas");
		return false;
	}
	public boolean bReak(){
		System.out.println("Nem sikerult a mezo elrontasa");
		return false;
	}
	
	
}
