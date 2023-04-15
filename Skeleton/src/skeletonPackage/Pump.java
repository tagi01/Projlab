package skeletonPackage;

import java.util.ArrayList;

public class Pump extends BreakableField {
	/** Privát, Pipe típusú cső referenciáját tárolja, amelyikből folyik be a víz a pumpába. */
	private Pipe in;
	/** Privát, Pipe típusú cső referenciáját tárolja, amerre folyik a víz a pumpából*/
	private Pipe out;
	
	private ArrayList<Pipe> neighbours;
	
	public Pump() {
		super();
		in = null;
		out = null;
	}

	/**
	 * Publikus metódus, Pump kétparaméteres konstruktora, beállítja a pumpa be- és kimeneteit.
	 * @param i, Pipe cső, amelyik a pumpa bemenete, innen jön a víz a pumpába
	 * @param o, Pipe cső, amelyik a pumpa kimenete, ide megy a víz a pumpából
	 */
	public Pump(Pipe i, Pipe o) {
		super();
		in = i;
		out = o;
	}

	public void setIn(Pipe new_p) { in = new_p; }

	public Pipe getIn() { return in; }

	public Pipe getOut() { return out; }
	
	public void setOut(Pipe new_p) { out = new_p; }

	//TODO javaodc
	public boolean addNeighbour(Pipe p) {
		if(neighbours.contains(p) && p == null) { return false; }
		else {
			neighbours.add(p);
			return true;
		}
	}
	
	//TODO javadoc
	public boolean removeNeighbour(Pipe p) {
		if(neighbours.contains(p) && p!=null) {
			neighbours.remove(p);
			return true;
		} else {
			return true;
			}
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor megadja, hogy a karakter ráléphet-e a ciszternára.
	 * @return boolean, true, ha ráléphet a ciszternára, false ha nem
	 */
	public boolean setPump(Pipe from , Pipe to) {
		if(!neighbours.contains(to)) {
			System.out.println("A beallitando cso nem a pumpa szomszedja");
			return false;
		}
		if(!neighbours.contains(from)) {
			System.out.println("A lecserelendo cso nem a pumpa szomszedja");
			return false;
		}

		if(from.equals(in)) {
			in = to;
			return true;
		} else if(from.equals(out)) {
			out = to;
			return true;
		} else {
			return false;	// from nem aktiv csove a pumpanak
		}
	}

		/*
		 * Field metódus felülírása. Egy Field-et hozzá lehet-e
		 *csatlakoztatni a meghívott pumpához
		 */
	public boolean acceptField(Field f) {
		if(neighbours.size() < 8) {		//TODO mennyi a max, vagy mi a feltetel??
			return true;
		} else {
			return false;
		}
	}

		/**
		 * Publikus metódus, meghívásakor a pumpa a bemenetéből átpumpálja a megfelelő mennyiségű vizet a kimenetén lévő csőbe.
		 */
	public void pumpWater() {
		int out_capacity = out.getCapacity();
		int in_sizeOfWater = in.takeWater(out_capacity);
		out.flowWater(in_sizeOfWater);
	}

	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}
	//TODO javadoc
}
