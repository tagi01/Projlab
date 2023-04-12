package skeletonPackage;

import java.util.*;

public class Network {
		//Character típusú változó referenciája. Az éppen aktív játékos az értéke, ő tud cselekedni
	private Character activeCharacter;
		//A pályán található karakterek listája
	private ArrayList<Character> characters;
		//A pályát alkotó mezők listája
	private ArrayList<Field> fields;
	/*
	 *Az adott csőre(currentField)
	 *lerakja a pumpát, beállítja az új mezők szomszédságait, létrehozza a plusz csövet, ami
	 *az új pumpa és az egyik régi pumpa közt lesz
	 */
	public void addPump(Pump pump, Pipe currentField) {
		Pipe p_new = new Pipe();
		p_new.addNeighbour(pump);
		pump.addNeighbour(p_new);
		activeCharacter.removeInventory();
		
		//egyszeruseg kedveert egy rovidebb változoban ami a currentField szomszedsagi listabol kivalasztja azt a szomszedot, ami pump.
		//0 v 1 fix pump, mert mashogy pumpat lerakni nem lehet 
		Pump p= new Pump();
		
		if(currentField.neighbours.get(0) instanceof Pump) 
			p = (Pump)currentField.neighbours.get(0);		
		else
			p = (Pump)currentField.neighbours.get(1);
		//ahhoz kell, hogy megnezzem a pumpa a currentFieldnek ad/kap vizet, vagy semmit nem csinal (kap=1,ad=2,semmi=0)
		int temp_sett = 0;
		if(p.getIn().equals(currentField))
			temp_sett=1;
		else if(p.equals(currentField))
			temp_sett=2;
		else
			temp_sett=0;
		
		p.addNeighbour(p_new);
		p_new.addNeighbour(p);
		
		if(temp_sett == 1) {
			p_new.setOut(p);
			p_new.setIn(pump);
			p.setIn(p_new);
			pump.setOut(p_new);
			
			p.removeNeighbour(currentField);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setOut(pump);
			pump.setIn(currentField);
		}
		else if(temp_sett == 2) {
			p_new.setIn(p);
			p_new.setOut(pump);
			p.setOut(p_new);
			pump.setIn(p_new);
			
			p.removeNeighbour(currentField);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setIn(pump);
			pump.setOut(currentField);
			
		}else {
			p_new.setOut(p);
			p_new.setIn(pump);
			p.setIn(p_new);
			pump.setOut(p_new);
			
			p.removeNeighbour(currentField);
			currentField.changeNeighbour(p, pump);
			pump.addNeighbour(currentField);
			
			currentField.setOut(pump);
			pump.setIn(currentField);
		}	
		
	}
	/*
	 *  Véletlenszerűen eltör egy pumpát a pályán
	 */
	public void breakPump() {
		Random rn = new Random();
		boolean break_succed = false;
		
		do {
		int random = rn.nextInt((fields.size()-0)+1)+0;
			if(fields.get(random) instanceof Pump) {
				fields.get(random).bReak();
				break_succed = true;
			}
		}while(break_succed != true);
	}
}
