package skeletonPackage;

import java.util.*;

import javax.swing.JPanel;

/** Source osztály */
public class Source extends Field {

	/**
	 * Privát, a forrás szomszédos csöveit tároló lista.
	 */
	private ArrayList<Pipe> neighbours;
	private SourceView sourceView;

	/**
	 * Publikus metódus, Source paraméter nélküli konstruktora.
	 */
	public Source(GamePanel jp) {
		super();
		neighbours=new ArrayList<Pipe>();
		sourceView = new SourceView(this, jp);
	}
	
	/*Getterek*/
	public View getView() {return sourceView;}


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
			return false;
		}
	}

	/**
	 * Publikus metódus, meghívásakor a hozzá csatlakoztatott csöveknek maximális mennyiségű vizet ad.
	 */
	@Override
	public void flowWater() {
		for(int i=0 ; i < neighbours.size() ; i++) {
			int j = neighbours.get(i).getCapacity();
			neighbours.get(i).addWater(j);
		}
	}
	/**
	 *Lekérdezi a Source mező szomszédsági listájáts
	 * @return a Source mező szomszédsági listájával té vissza
	 */
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}

	public void interactPlumber(Plumber p, Pipe pipe) {
		if(neighbours.contains(pipe)) {
			boolean removed = removeNeighbour(pipe);
			if(removed) {
				pipe.removeNeighbour(this);
				p.setInventoryPipe(pipe);
				game.removeActionPoints();
			}
			
		}
		else {
			boolean added = addNeighbour(pipe);
			if(added) {
				pipe.addNeighbour(this);
				p.setInventoryPipe(null);
				game.removeActionPoints();
			}
	
		}
	}

	/**
	 * Ad egy új fieldet a neighbours-höz, de mivel a Sourcenak szomszédai csak Pipe ezért mindig false-al tér vissza
	 * @param f Field típist ad
	 * @retun False értékkel tér vissza mindíg
	 */
	@Override
	public boolean addNeighbour(Field f) {
		return false;
	}
	/**
	 * Töröl egy új fieldet a neighbours-ből, de mivel a Sourcenak szomszédai csak Pipe ezért mindig false-al tér vissza
	 * @param f Field típist ad
	 * @retun False értékkel tér vissza mindíg
	 */
	@Override
	public boolean removeNeighbour(Field f) {
		return false;
	}

}
