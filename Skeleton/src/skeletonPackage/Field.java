package skeletonPackage;

import java.util.List;

public abstract class Field {
	//szomszédokat egy fieldnek tárolo lista
	private List<Field> neighbours;
	//A mezon aktuálisan álló karaktert
	private Character currentCharacter;
	
	/*
	 *Egy logikai változó a visszatérési értéke. Character ráléphet-e a mezőre, True ha igen 
	 */
	public boolean acceptCharacter;
	/*
	 *Egy Field-et hozzá lehet-e csatlakoztatni a meghívott
	 *mezőhöz. Absztrakt metódus, logikai változó a visszatérési értéke, True ha igen, False
	 *ha nem
	 */
	public boolean acceptField;

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
	
}
