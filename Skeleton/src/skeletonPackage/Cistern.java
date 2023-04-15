package skeletonPackage;

import java.util.ArrayList;

public class Cistern extends Field {
	//Egesz szam vatozo. Az egy jatekos kore alatt osszegy�jt�tt viz mennyiseget tarolja
	private int collectedWater;
	//Egy logikai valtozo, van-e felveheto pumpa a ciszternan. True, ha igen, False, ha nem
	private boolean hasPump;
	//Egy logikai valtozo, van-e felveheto cso a ciszternan. True, ha igen, False, ha nem
	private boolean hasPipe;
	
	private ArrayList<Pipe> neighbours;
	
	//TODO: Ezt miert vettuk ki?(Pump es Pipe ot?)
	//private Pump pu;
	//private Pipe pi;
	
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
	 * hasPump es hasPipe ertekeket True-ra allitja, vagyis most mar a
	 *ciszternarol fel lehet venni pumpat es csovet egyarant
	*/
	public void resetItems() {
		hasPump = true;
		hasPipe = true;
	}
	
	/*
	 * A meghivoja egy pumpat venne fel a ciszternarol, ekkor, ha
	 *meg van pumpa, akkor atallitja a hasPump erteket False-ra es visszaadja az uj pumpat,
	 *ha nincs, akkor csak visszater a metodus
	*/
	public Pump removePump() {
		if(hasPump == true) {
			hasPump = false;
			Pump pu = new Pump(null, null);
			return pu;
		}
		else
			return null;
	}
	
	/*
	 * Metodus meghivasakor a ciszternarol csovet szeretnenk felvenni,
	 *ha lehetseges (mert a hasPipe True) ekkor ezt a hasPipe erteket False-ra allatja es
	 *visszaadja az uj csovet, ha nem, mert mar felvettek rola csovet, akkor marad False ez az ertek
	*/
	public Pipe removePipe() {
		if(hasPump == true) {
			hasPipe = false;
			//Pipe pi = new Pipe(0, null, null, 9999, true, 0); //size-t allitani kell meg, meg a ciszternahoz hozza van kotve 
			//return pi;
			return null;
		}
		else
			return null;
	}
	
	/*
	 * Egy Pipe-ot hozza lehet-e csatlakoztatni a meghivott
	 *mezohoz. Ha igen True-val ter vissza, egyebkent False-szal
	*/
	public boolean acceptField(Field f) {
		//TODO cistern melle nem tehetunk pumpot.
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		return true;
	}
	
	/*
	 * Metodus meghovasakor a ciszterna elveszi a hozza beerkezo
	 *csovektol az osszes vizet es eltarolja.
	*/
	public void collectWater() {
		for(int i = 0; i < neighbours.size() ; i++) {
			Pipe tmp = (Pipe) neighbours.get(i);
			int mennyit = 0;// = tmp.getWater();
			collectedWater += tmp.takeWater(mennyit);
		}
	}
	@Override
	public ArrayList<? extends Field> getNeighbours() {
		return neighbours;
	}

}
