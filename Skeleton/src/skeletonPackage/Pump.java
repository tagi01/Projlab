package skeletonPackage;

public class Pump extends BreakableField{
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amelyikből folyik be a víz a pumpába
	private Pipe in;
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amerre folyik a víz a pumpából
	private Pipe out;
	
	public Pump() {
		in=null;
		out=null;
	}
	
	public void setIn(Pipe new_p) {in=new_p;}
	@Override
	public Pipe getIn() {return in;}
	@Override
	public Pipe getOut() {return out;}
	
	public void setOut(Pipe new_p) {out=new_p;}

	
	public Pump(Pipe i, Pipe o) {
		super();
		in=i;
		out=o;
	}
	
	@Override
	public boolean acceptCharacter() {
		return true;
	}
	/*
	 * Megmondja, hogy lehetséges-e a csövek
	 *cseréje. Egy logikai változó a visszatérési értéke. True, ha igen, False, ha nem. Ha
	 *True, akkor az egyik cső csatlakozását átállítja másikra (“from” csőről a “to” csőre),
	 *ha ugye megegyezik a kettő, akkor nem történik semmi
	 */
	@Override
	public boolean setPump(Pipe from , Pipe to) {
		if(from != null && to != null) {
		in.setOut(null);
		out.setIn(null);
		in=from;
		out=to;
		in.setOut(this);
		out.setIn(this);
		return true;
		}else {
			return false;
		}
	}
	/*
	 * Field metódus felülírása. Egy Field-et hozzá lehet-e
	 *csatlakoztatni a meghívott pumpához
	 */
	public boolean acceptField(Field f) {
		//TODO
		return true;
	}
	/*
	 * A pumpa a bemenetéből átpumpálja a megfelelő mennyiségű vizet a kimenetén lévő csőbe
	 */
	public void pumpWater() {
		int out_capacity = out.getCapacity();
		int in_sizeOfWater= in.takeWater(out_capacity);
		out.flowWater(in_sizeOfWater);
	}
}
