package skeletonPackage;

import java.util.*;

/** Network osztály */
public class Network {


	/**
	 * Privát, a pályát alkotó mezők listája.
	 */
	private ArrayList<Field> fields;
	/*
	 * egy lista a pumpakna
	 */
	private ArrayList<Pump> pumplist;
	/*
	 * allithato parameter a randomnak
	 */
	private boolean random;
	
	/*
	 * Konstruktor
	 */
	public Network() {
		fields = new ArrayList<Field>();
		pumplist = new ArrayList<Pump>();
		random=false;
	}
	/*
	 * randomsg beállítása
	 */
	public void setRandom(boolean value) {random=value;}
	/*
	 * randomsag lekerdezése
	 */
	public boolean getRandom() {return random;}
	
	/**
	 * A vízhálózat mezőit adja vissza
	 * @return a mezők
	 */
	public ArrayList<Field> getFields() { return fields; }

		/**
	 * Az adott csőre lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami az új pumpa és az egyik régi pumpa közt lesz.
	 * @param pump, a Pump típusú változó referenciája, amelyik pumpát letesszük a csőre
	 * @param currentField, olyan Pipe típusú változó referenciája, amelyik csőre tennénk le a pumpát
	 */

	public void addPump(Pump pump, Pipe currentField) {
		int[] pumpCoordinates = currentField.getView().getCoordinates();		// TODO ellenőrizni
		pump.getView().setCoordinates(pumpCoordinates[0], pumpCoordinates[1]);
		Program.setNewPump(pump);
		
		Pipe p_new = new Pipe(currentField.getView().getGamePanel(), currentField.getGame());
		Program.setNewPipe(p_new);
		fields.add(p_new);
		
		p_new.addNeighbour(pump);
		pump.addNeighbour(p_new);
		
		pump.setIn(p_new);
		
		p_new.addNeighbour(currentField.getNeighbours().get(0));
		currentField.getNeighbours().get(0).addNeighbour(p_new);
		
		currentField.removeNeighbour(currentField.getNeighbours().get(0));
		currentField.getNeighbours().get(0).removeNeighbour(currentField);
		
		currentField.addNeighbour(pump);
		pump.addNeighbour(currentField);
		
		pump.setOut(currentField);
		
		
		
		
	}
	
	/**A field listához hozzáad egy új elemet
	 * @param f: Field típus
	 */
	public void addField(Field f) {
		fields.add(f);
	}
	
	/**A pumplist listához hozzáad egy új elemet
	 * @param p egy Pump amit a listához adunk
	 */
	public void addField(Pump p) {
		fields.add(p);
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
		if(random) {
			Random rn = new Random();
			boolean break_succed = false;
			
			do {
			int random = rn.nextInt((pumplist.size()));
					pumplist.get(random).breakField();
					break_succed = true;
			}while(break_succed != true);
		}
		else
			System.out.println("Random pumpa eltores ki van kapcsolva. Elotte kapcsold vissza a randomsagot.");
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
