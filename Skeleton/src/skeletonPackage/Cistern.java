package skeletonPackage;

import java.util.ArrayList;

/** Cistern oszt�ly */
public class Cistern extends Field {

	/**
	 * Priv�t integer, amely egy j�t�kos k�re alatt �sszegy?jt�tt v�z mennyis�g�t t�rolja
	 */
	private int collectedWater;

	/**
	 * Priv�t boolean, amely megadja, hogy van-e felvehet? pumpa a cisztern�n. True ha igen, false ha nincs.
	 */
	private boolean hasPump;

	/**
	 * Private boolean, amely megadja, hogy van-e felvehet? cs? a cisztern�n. True ha igen, false ha nincs.
	 */
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

	/**
	 * Publikus met�dus, megh�v�sakor a cisztern�r�l pump�t szeretn�nk felvenni, ekkor, ha m�g van pumpa, akkor visszaadja az �j pump�t, ha nincs, akkor egy null-lal visszat�r a met�dus.
	 * @return Pump a felvett pumpa referenci�ja (null, ha nincs felvehet? pumpa)
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

	/**
	 * Publikus met�dus, megh�v�sakor a cisztern�r�l cs�vet szeretn�nk felvenni, ha lehets�ges, ekkor visszaadja az �j cs�vet, ha nincs, akkor egy null-lal visszat�r a met�dus.
	 * @return Pipe, a felvett cs? referenci�ja (null, ha nincs felvehet? cs?)
	 */
	public Pipe removePipe() {
		if(hasPipe == true) {
			hasPipe = false;
			//Pipe pi = new Pipe(0, null, null, 9999, true, 0); //size-t allitani kell meg, meg a ciszternahoz hozza van kotve 
			//return pi;
			return null;
		}
		else
			return null;
	}

	/**
	 * Publikus met�dus, Field-b?l �r�k�lt f�ggv�ny fel�l�r�sa. Megh�v�sakor megadja, hogy a karakter r�l�phet-e a cisztern�ra.
	 * @return boolean, true, ha r�l�phet a cisztern�ra, false ha nem
	 */
	@Override
	public boolean acceptCharacter() {
		return false;
	}

	/**
	 * Publikus met�dus, Field-b?l �r�k�lt f�ggv�ny fel�l�r�sa. Megh�v�sakor megadja, hogy a param�terben kapott mez? hozz�adat�-e szomsz�dnak.
	 * @param f, Field-b?l lesz�rmaz� t�pus� v�ltoz�, amelyet hozz�csatlakoztatn�nk a megh�vott mez?h�z
	 * @return boolean, true ha a param�ter hozz�csatlakoztathat�, false ha nem
	 */
	public boolean acceptField(Field f) {
		//TODO cistern melle nem tehetunk pumpot.
		//egy megoldas, hogyha a networkben a Field, az benne van e pump arraylistben
		return true;
	}

	/**
	 * Publikus met�dus, megh�v�sakor a ciszterna elveszi a hozz� be�rkez? cs�vekt?l az �sszes vizet �s elt�rolja.
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
