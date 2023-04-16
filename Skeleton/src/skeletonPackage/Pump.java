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
		neighbours = new ArrayList<Pipe>();
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
		neighbours = new ArrayList<Pipe>();
	}

	public void setIn(Pipe new_p) {
		if(in == null)		// cserelni nem ezzel kell
			in = new_p; 
	}

	public Pipe getIn() { return in; }

	public Pipe getOut() { return out; }
	
	public void setOut(Pipe new_p) {
		if(out == null)		// cserelni nem ezzel kell
			out = new_p;
	}

	//TODO javaodc
	@Override
	public boolean addNeighbour(Pipe p) {
		if(neighbours.contains(p) || p == null || !acceptField(p)) { return false; }
		else {
			neighbours.add(p);
			return true;
		}
	}
	
	//TODO javadoc
	@Override
	public boolean removeNeighbour(Pipe p) {
		if(p == null || !neighbours.contains(p)) {
			return false;
		} else if(p == in || p == out || neighbours.size() <= 2) {
			System.out.println("Nem tavolithato el a cso, mert aktiv");
			return false;
		} else {
			neighbours.remove(p);
			return true;
		}
	}

	/**
	 * Publikus metódus, Field-ből örökölt függvény felülírása. Meghívásakor megadja, hogy a karakter ráléphet-e a ciszternára.
	 * @return boolean, true, ha ráléphet a ciszternára, false ha nem
	 */
	private boolean setPump(Pipe from , Pipe to) {
		if(!neighbours.contains(to)) {
			System.out.println("A beallitando cso nem a pumpa szomszedja");
			return false;
		}
		if(!neighbours.contains(from)) {
			System.out.println("A lecserelendo cso nem a pumpa szomszedja");
			return false;
		}
		
		if(neighbours.size() == 2) {	// ha csak ket cso van
			if(from.equals(in) && to.equals(out)) {
				in = to;
				out = from;
			} else if(from.equals(out) && to.equals(in)) {
				out = to;
				in = from;
			}
		}
		
		if(to.equals(in) || to.equals(out)) {
			return false; // to aktiv csove a pumpanak
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
	@Override
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

	@Override
	public boolean addNeighbour(Field f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeNeighbour(Field f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interact(Pipe from, Pipe to) {
		return setPump(from, to);
	}

	@Override
	public boolean interactPlumber(Plumber p, Pipe pipe) {
		if(neighbours.contains(pipe)) {
			boolean removed = removeNeighbour(pipe);
			if(removed) {
				pipe.removeNeighbour(this);
				p.setInventoryPipe(pipe);
				pipe.setTaken(true);
			}
			return removed;
		} else {
			boolean added = addNeighbour(pipe);
			if(added) {
				pipe.addNeighbour(this);
				p.setInventoryPipe(null);
				pipe.setTaken(false);
			}
			return added;
		}
	}
}
