package skeletonPackage;

import java.util.List;

public class Network {
		//Character típusú változó referenciája. Az éppen aktív játékos az értéke, ő tud cselekedni
	private Character activeCharacter;
		//A pályán található karakterek listája
	private List<Character> characters;
		//A pályát alkotó mezők listája
	private List<Field> fields;
	
	/*
	 *Az adott csőre(currentField)
	 *lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami
	 *az új pumpa és az egyik régi pumpa közt lesz
	 */
	public void addPump(Pump pump, Field currentField) {}
	/*
	 *  Véletlenszerűen eltör egy pumpát a pályán
	 */
	public void breakPump() {}
}
