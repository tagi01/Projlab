package skeletonPackage;

import java.util.*;

/** Network osztály */
public class Network {

	/*
	 * eppen aktiv karakter akcio pontja
	 */
	private int actionPoint;
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
	
	private ArrayList<Pump> pumplist;

	/*
	 * Konstruktor
	 */
	public Network() {
		fields = new ArrayList<Field>();
		characters = new ArrayList<Character>();
		pumplist = new ArrayList<Pump>();
	}
	
	//**************************************************************
	public void setActiveCharacter(Character value) {activeCharacter=value;}
	public void setActionPoint(int value) {actionPoint=value;}
	public int getActionPoint() {return actionPoint;}
	public Character getCurrentCharacter() {return activeCharacter;}
	//**************************************************************
	
	/**
	 * Csak a teszteléshez kell
	 * @param fields az uj ertek
	 */
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	/**
	 * Az adott csőre lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami az új pumpa és az egyik régi pumpa közt lesz.
	 * @param pump, a Pump típusú változó referenciája, amelyik pumpát letesszük a csőre
	 * @param currentField, olyan Pipe típusú változó referenciája, amelyik csőre tennénk le a pumpát
	 */

	public void addPump(Pump pump, Pipe currentField) {
		//Program.printMethod(this, "addPump");
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
	
	/**A field listához hozzáad egy új elemet
	 * @param f: Field típus
	 */
	public void addField(Field f) {
		//Program.printMethod(this, "addField");
		fields.add(f);
	}
	
	/**A pumplist listához hozzáad egy új elemet
	 * @param p egy Pump amit a listához adunk
	 */
	public void addField(Pump p) {
		//Program.printMethod(this, "addField");
		pumplist.add(p);
	}
	
	/**
	 * Beállítja a paraméterben kapott listát a network listájára
	 * @param f ArrayList<Field> típusu, ami egy Field lista
	 */
	public void setField(ArrayList<Field> f) {
		fields=f;
	}
	
	/**
	 * Beállítja a network pumplistájára a kapott p listát
	 * @param p ArrayList<Pump> egy Pump lista
	 */
	public void setPumpList(ArrayList<Pump> p) {
		pumplist=p;
	}

	/**
	 * Publikus metódus, meghívásakor véletlenszerűen eltör egy pumpát a pályán.
	 */
	public void breakPump() {
		//Program.printMethod(this, "breakPump");
		Random rn = new Random();
		boolean break_succed = false;
		
		do {
		int random = rn.nextInt((pumplist.size()));
				pumplist.get(random).breakField();
				break_succed = true;
		}while(break_succed != true);
	}
	
	/*
	 * Végig iterál a field listan es meghivja a flowWater metodust
	 */
	public void flowWaterOnField() {
		for(int i=0; i<fields.size(); i++) {
			fields.get(i).flowWater();
		}
	}

}
