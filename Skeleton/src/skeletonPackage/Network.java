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

	private ArrayList<Pump> pumplist;

	//TODO azert hogy break majd mukodjon

	/**
	 * Az adott csőre lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami az új pumpa és az egyik régi pumpa közt lesz.
	 * @param pump, a Pump típusú változó referenciája, amelyik pumpát letesszük a csőre
	 * @param currentField, olyan Pipe típusú változó referenciája, amelyik csőre tennénk le a pumpát
	 */
	public void addPump(Pump pump, Pipe currentField) {
		Pipe p_new = new Pipe();
		fields.add(p_new);

		p_new.addNeighbour(pump);
		pump.addNeighbour(p_new);

		activeCharacter.removeInventory();
		//kerdes? itt adjuk hozza  pumplisthez, vagy amikor berakjuk inventoryba?

		//Mivel nem emlekszem mit beszeltunk, ezét ide ket változatot csinalok, egyik ahol be is allitom a pumpot
		//viszont itt ugy csinaltam, hogy a pipe-nak visszatettem a kett atibutumot (Pump in/out) és par getter setter. Ezt a Pipe osztály aljan lesz majd
		//ha mégse mi állítsuk majd be ezeket. akkor konnyi lesz kitorolni.
		
		//az egyik szomszed pumpa, mihez viszonyitjuk a beallitast
		//a pipe szomszédait megvizsgaljuk, és ha van koztuk pump(egynek muszáj lenni, akkor beéllítom a segédváltozoba)

		//AZ A VALTOZAT, AHOL AUTOATIKUSAN BEALLITJUK A SZOMSZEDOKAT ES  FOLYAST IS
		/*
		Pump p = new Pump();
		for(int i=0; i< currentField.getNeighbours().size(); i++) {
			for(int j = 0; j< pumplist.size(); j++) {
			if(currentField.getNeighbours().get(i) == pumplist.get(j)) {
					p=pumplist.get(j);
				}
			}
		}
		int temp_set = 0;
		if(currentField.getIn().equals(p)) {
			temp_set=2;
		}
		else if(currentField.getOut().equals(p)) {
			temp_set=1;
		}
		else {
			temp_set=0;
		}

		if(temp_set == 1) {
			p_new.addNeighbour(p);
			p_new.setOut(p);
			p_new.setIn(pump);
			p.setIn(p_new);
			pump.setOut(p_new);
			
			p.removeNeighbour(currentField);
			p.addNeighbour(p_new);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setOut(pump);
			pump.setIn(currentField);
		}
		else if(temp_set == 2) {
			p_new.addNeighbour(p);
			p_new.setIn(p);
			p_new.setOut(pump);
			p.setOut(p_new);
			pump.setIn(p_new);
			
			p.removeNeighbour(currentField);
			p.addNeighbour(p_new);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setIn(pump);
			pump.setOut(currentField);
			
		} else {
			p_new.addNeighbour(p);
			p_new.setOut(p);
			p_new.setIn(pump);
			p.setIn(p_new);
			pump.setOut(p_new);
			
			p.removeNeighbour(currentField);
			p.addNeighbour(p_new);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setOut(pump);
			pump.setIn(currentField);
		}
		*/
		//AZ A VÁLTOZAT, AHOL NEM ES ITT NEM IS KELLENEK A PIPEBAN FELVETT PLUSZ ATIBUTUM ES METPDUSOK
		Pump p = new Pump();
		for(int i=0; i< currentField.getNeighbours().size(); i++) {
			for(int j = 0; j< pumplist.size(); j++) {
			if(currentField.getNeighbours().get(i) == pumplist.get(j)) {
					p=pumplist.get(j);
				}
			}
		}

			p_new.addNeighbour(p);
			p.removeNeighbour(currentField);
			p.addNeighbour(p_new);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);

		
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
