package skeletonPackage;

import java.util.*;

public class Source extends Field{

	public Source(ArrayList<Field> n, Character c) {
		super(n,c);
	}
	
	/*
	 * Field metódus felülírása. Logikai változó a visszatérési
	 *értéke. True, ha hozzá lehet csatlakoztatni a mezőt, False, ha nem
	 */
	public boolean acceptField(Field f) {
		//tipus elleneorzes nem szep, de kell
		if(f instanceof Pipe) {
			if( f.getNeighbours().get(0) instanceof Cistern) {
				return false;
			}
		}else if(f instanceof Pump) {
			return false;
		}
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
}
