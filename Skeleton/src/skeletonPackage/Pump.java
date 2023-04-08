package skeletonPackage;

public class Pump extends BreakableField{
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amelyikből folyik be a víz a pumpába
	private Pipe in;
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amerre folyik a víz a pumpából
	private Pipe out;
	
	/*
	 * Megmondja, hogy lehetséges-e a csövek
	 *cseréje. Egy logikai változó a visszatérési értéke. True, ha igen, False, ha nem. Ha
	 *True, akkor az egyik cső csatlakozását átállítja másikra (“from” csőről a “to” csőre),
	 *ha ugye megegyezik a kettő, akkor nem történik semmi
	 */
	public boolean setPump(Pipe from , Pipe to) {
			//TODO
		return true;
	}
	/*
	 * Field metódus felülírása. Egy Field-et hozzá lehet-e
	 *csatlakoztatni a meghívott pumpához
	 */
	public boolean acceptField;
	/*
	 * A pumpa a bemenetéből átpumpálja a megfelelő mennyiségű vizet a kimenetén lévő csőbe
	 */
	public void pumpWater() {}
}
