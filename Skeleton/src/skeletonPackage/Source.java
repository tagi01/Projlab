package skeletonPackage;

import java.util.*;

/** Source osztály */
public class Source extends Field {

	/**
	 * Privát, a forrás szomszédos csöveit tároló lista.
	 */
	private ArrayList<Pipe> neighbours;

	/**
	 * Publikus metódus, Source paraméter nélküli konstruktora.
	 */
	public Source() {
		neighbours=new ArrayList<Pipe>();
	}

	/**
	 * Publikus metódus, meghívásakor a paraméterben kapott csövet a szomszédsági listához adja.
	 * @param p, Pipe amit hozzáadnánk a forrás szomszédaihoz
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean addNeighbour(Pipe p) {
		if(neighbours.contains(p) || p == null) { return false; }
		else {
			neighbours.add(p);
			return true;
		}
	}

	/**
	 * Publikus metódus,  meghívásakor a paraméterben kapott csövet kitöröljük a szomszédsági listából.
	 * @param p, Pipe amit törölnénk a forrás szomszédaiból
	 * @return boolean, sikerült-e a művelet, true ha igen, false ha nem
	 */
	public boolean removeNeighbour(Pipe p) {
		if(neighbours.contains(p) && p!=null) {
			neighbours.remove(p);
			return true;
		} else {
			return true;
			}
	}

	/**
	 * Publikus metódus, meghívásakor megadja, hogy hozzá lehet-e csatlakoztatni mezőt vagy nem. Field metódus felülírása.
	 * @return boolean, true, ha hozzá lehet csatlakoztatni a mezőt, false, ha nem.
	 */
	public boolean acceptField(Field f) {
		//TODO source mellé nem tehetunkk pumppot
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		return true;
	}

	/**
	 * Publikus metódus, meghívásakor a hozzá csatlakoztatott csöveknek maximális mennyiségű vizet ad.
	 */
	public void giveWater() {
		for(int i=0 ; i < neighbours.size() ; i++) {
			int j = neighbours.get(i).getCapacity();
			neighbours.get(i).flowWater(j);
		}
	}

	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}
	//TODO: javadoc

	
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
		}
		else {
			boolean added = addNeighbour(pipe);
			if(added) {
				pipe.addNeighbour(this);
				p.setInventoryPipe(null);
				pipe.setTaken(false);
			}
			return added;
		}
	}


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
}
