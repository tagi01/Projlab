package skeletonPackage;

import java.util.*;

/** Network osztály */
public class Network {

	/**
	 * Privát, az éppen aktív játékos referenciája, ő tud cselekedni.
	 */
	private Character activeCharacter;

	/**
	 * Privát, a pályán látható karakterek listája.
	 */
	private ArrayList<Character> characters;

	/**
	 * Privát, a pályát alkotó mezők listája.
	 */
	private ArrayList<Field> fields;

	/**
	 * Csak a teszteléshez kell
	 * @param fields az uj ertek
	 */
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	private ArrayList<Pump> pumplist;

	//TODO azert hogy break majd mukodjon

	/**
	 * Az adott csőre lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami az új pumpa és az egyik régi pumpa közt lesz.
	 * @param pump, a Pump típusú változó referenciája, amelyik pumpát letesszük a csőre
	 * @param currentField, olyan Pipe típusú változó referenciája, amelyik csőre tennénk le a pumpát
	 */
	public Network() {
		activeCharacter=null;
		characters= new ArrayList<Character>();
		fields=new ArrayList<Field>();
		pumplist = new ArrayList<Pump>();
	}
	public void addPump(Pump pump, Pipe currentField) {
		Pipe p_new = new Pipe();
		fields.add(p_new);

		p_new.addNeighbour(pump);
		pump.addNeighbour(p_new);
		
		
		p_new.addNeighbour(currentField.getNeighbours().get(0));
		currentField.getNeighbours().get(0).addNeighbour(p_new);
		
		currentField.removeNeighbour(currentField.getNeighbours().get(0));
		currentField.getNeighbours().get(0).removeNeighbour(currentField);
		
		currentField.addNeighbour(pump);
		pump.addNeighbour(currentField);
		
	}
	
	public void addField(Field f) {
		fields.add(f);
	}
	
	public void addField(Pump p) {
		pumplist.add(p);
	}
	
	public void setField(ArrayList<Field> f) {
		fields=f;
	}
	
	public void setPumpList(ArrayList<Pump> p) {
		pumplist=p;
	}

	/**
	 * Publikus metódus, meghívásakor véletlenszerűen eltör egy pumpát a pályán.
	 */
	public void breakPump() {
		Random rn = new Random();
		boolean break_succed = false;
		
		do {
		int random = rn.nextInt((pumplist.size()-0)+1)+0;
				pumplist.get(random).breakField();
				break_succed = true;
		}while(break_succed != true);
	}

}
