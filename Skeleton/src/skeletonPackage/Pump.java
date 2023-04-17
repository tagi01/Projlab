package skeletonPackage;

import java.util.ArrayList;

/**
 * Pump osztály
 */
public class Pump extends BreakableField {
	/** Privát, Pipe típusú cső referenciáját tárolja, amelyikből folyik be a víz a pumpába. */
	private Pipe in;
	/** Privát, Pipe típusú cső referenciáját tárolja, amerre folyik a víz a pumpából*/
	private Pipe out;
	
	/**
	 * A pumpa szomszédait tárolja
	 */
	private ArrayList<Pipe> neighbours;
	
	/**
	 * Konstruktor
	 */
	public Pump() {
		super();
		in = null;
		out = null;
		neighbours = new ArrayList<Pipe>();
	}

	/**
	 * Konstruktor, Pump kétparaméteres konstruktora, beállítja a pumpa be- és kimeneteit.
	 * @param i, Pipe cső, amelyik a pumpa bemenete, innen jön a víz a pumpába
	 * @param o, Pipe cső, amelyik a pumpa kimenete, ide megy a víz a pumpából
	 */
	public Pump(Pipe i, Pipe o) {
		super();
		in = i;
		out = o;
		neighbours = new ArrayList<Pipe>();
	}

	/**
	 * Beállítja a pumpa bemenetét
	 * @param new_p Az új bemeneti cső
	 */
	public void setIn(Pipe new_p) {
		Skeleton.printMethod(this, "setIn");
		if(in == null)		// cserelni nem ezzel kell
			in = new_p; 
	}

	/**
	 * A bemenet gettere
	 * @return A pumpa bemenete
	 */
	public Pipe getIn() { return in; }

	/** A kimenet gettere
	 * @return A pumpa kimenete
	 */
	public Pipe getOut() { return out; }
	
	/**
	 * Beállítja a pumpa kimenetét
	 * @param new_p Az új kimenet
	 */
	public void setOut(Pipe new_p) {
		Skeleton.printMethod(this, "setOut");
		if(out == null)		// cserelni nem ezzel kell
			out = new_p;
	}

	/** Hozzáad egy csövet a szomszédaihoz
	 * @param p A hozzáadandó cső
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean addNeighbour(Pipe p) {
		Skeleton.printMethod(this, "addNeighbour");
		if(neighbours.contains(p) || p == null || !acceptField(p)) { return false; }
		else {
			neighbours.add(p);
			return true;
		}
	}

	/** Elvesz egy csövet a szomszédaiból
	 * @param p Az eltávolítandó cső
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Pipe p) {
		Skeleton.printMethod(this, "removeNeighbour");
		if(p == null || !neighbours.contains(p)) {
			return false;
		} else if(p == in || p == out/* || neighbours.size() <= 2*/) {
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
		Skeleton.printMethod(this, "setPump");
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

	/**
	 * Field metódus felülírása. Egy Field-et hozzá lehet-e csatlakoztatni a meghívott pumpához
	 * @param f a kérdéses mező
	 * @return true ha igen, false ha nem
	 */
	@Override
	public boolean acceptField(Field f) {
		Skeleton.printMethod(this, "acceptField");
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
		Skeleton.printMethod(this, "pumpWater");
		int out_capacity = out.getCapacity();
		int in_sizeOfWater = in.takeWater(out_capacity);
		out.flowWater(in_sizeOfWater);
	}

	/** Visszaadja a szomszédokat
	 * @return a pumpa szomszédai
	 */
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}
	
	/** Hozzáad egy szomszédot a neighbours listához
	 * @param f Az új szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean addNeighbour(Field f) {
		Skeleton.printMethod(this, "addNeighbour");
		// TODO Auto-generated method stub
		return false;
	}

	/** Kivesz egy szomszédot a neighbours listából
	 * @param f Az eltávolítandó szomszéd
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean removeNeighbour(Field f) {
		Skeleton.printMethod(this, "removeNeighbour");
		// TODO Auto-generated method stub
		return false;
	}

	/** A karakter használja a képességét, átállít egy pumpát
	 * @param from Amiről átállítja
	 * @param to Amire átállítja
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean interact(Pipe from, Pipe to) {
		Skeleton.printMethod(this, "interact");
		return setPump(from, to);
	}

	/** A szerelő használja a képességét, lerak egy csövet
	 * @param p a szerelő
	 * @param pipe a lerakni való pumpa
	 * @return true ha sikerült, false ha nem
	 */
	@Override
	public boolean interactPlumber(Plumber p, Pipe pipe) {
		Skeleton.printMethod(this, "interactPlumber");
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
