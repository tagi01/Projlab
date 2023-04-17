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
		Skeleton.printMethod(this, "addNeighbour");
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
		Skeleton.printMethod(this, "removeNeighbour");
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
		Skeleton.printMethod(this, "acceptField");
		return true;
	}

	/**
	 * Publikus metódus, meghívásakor a hozzá csatlakoztatott csöveknek maximális mennyiségű vizet ad.
	 */
	public void giveWater() {
		Skeleton.printMethod(this, "giveWater");
		for(int i=0 ; i < neighbours.size() ; i++) {
			int j = neighbours.get(i).getCapacity();
			neighbours.get(i).flowWater(j);
		}
	}
	/**
	 *Lekérdezi a Source mező szomszédsági listájáts
	 * @return a Source mező szomszédsági listájával té vissza
	 */
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		Skeleton.printMethod(this, "getNeighbours");
		return neighbours;
	}

	public boolean interactPlumber(Plumber p, Pipe pipe) {
		Skeleton.printMethod(this, "addNeighbour");
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

	/**
	 * Ad egy új fieldet a neighbours-höz, de mivel a Sourcenak szomszédai csak Pipe ezért mindig false-al tér vissza
	 * @param f Field típist ad
	 * @retun False értékkel tér vissza mindíg
	 */
	@Override
	public boolean addNeighbour(Field f) {
		Skeleton.printMethod(this, "addNeighbour");
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Töröl egy új fieldet a neighbours-ből, de mivel a Sourcenak szomszédai csak Pipe ezért mindig false-al tér vissza
	 * @param f Field típist ad
	 * @retun False értékkel tér vissza mindíg
	 */
	@Override
	public boolean removeNeighbour(Field f) {
		Skeleton.printMethod(this, "removeNeighbour");
		// TODO Auto-generated method stub
		return false;
	}
}
