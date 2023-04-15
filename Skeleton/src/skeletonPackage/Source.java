package skeletonPackage;

import java.util.*;

public class Source extends Field{
	
	private ArrayList<Pipe> neighbours;

	public Source() {
		neighbours=new ArrayList<Pipe>();
	}
	
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
	
	/*
	 * Field metódus felülírása. Logikai változó a visszatérési
	 *értéke. True, ha hozzá lehet csatlakoztatni a mezőt, False, ha nem
	 */
	public boolean acceptField(Field f) {
		//TODO source mellé nem tehetunkk pumppot
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		return true;
	}
	
	/*
	 * A metódus meghívásakor a hozzá csatlakoztatott csöveknek
	 *maximális mennyiségű vizet ad (a csövek pedig beállítják maguknak, hogy a water a
	 *kapacitásuk szerint a legtöbb legyen)
	 */
	public void getWater() {
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
}
