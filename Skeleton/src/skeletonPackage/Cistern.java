package skeletonPackage;

public class Cistern extends Field {
	//Egész szám változó. Az egy játékos köre alatt összegyűjtött víz mennyiségét tárolja
	private int collectedWater;
	//Egy logikai változó, van-e felvehető pumpa a ciszternán. True, ha igen, False, ha nem
	private boolean hasPump;
	//Egy logikai változó, van-e felvehető cső a ciszternán. True, ha igen, False, ha nem
	private boolean hasPipe;
	
	private Pump pu;
	private Pipe pi;
	
	/*
	 * hasPump és hasPipe értékeket True-ra állítja, vagyis most már a
	 *ciszternáról fel lehet venni pumpát és csövet egyaránt
	 */
	public void resetItems() {}
	/*
	 * A meghívója egy pumpát venne fel a ciszternáról, ekkor, ha
	 *még van pumpa, akkor átállítja a hasPump értéket False-ra és visszaadja az új pumpát,
	 *ha nincs, akkor csak visszatér a metódus
	 */
	public Pump removePump() {
		//TODO
		return pu;
		}
	/*
	 * Metódus meghívásakor a ciszternáról csövet szeretnénk felvenni,
	 *ha lehetséges (mert a hasPipe True) ekkor ezt a hasPipe értéket False-ra állítja és
	 *visszaadja az új csövet, ha nem, mert már felvettek róla csövet, akkor marad False ez
	 *az érték
	 */
	public Pipe removePipe() {
		//TODO
		return pi;
	}
	/*
	 * Egy Pipe-ot hozzá lehet-e csatlakoztatni a meghívott
	 *mezőhöz. Ha igen True-val tér vissza, egyébként False-szal
	 */
	public boolean acceptField() {
		//TODO
		return true;
	}
	/*
	 * Metódus meghívásakor a ciszterna elveszi a hozzá beérkező
	 *csövektől az összes vizet és eltárolja.
	 */
	public void collectWater() {}
}
