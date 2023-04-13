package skeletonPackage;

public class Pump extends BreakableField {
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amelyikből folyik be a víz a pumpába
	private Pipe in;
	//Egy Pipe típusú változó. Cső referenciáját tárolja, amerre folyik a víz a pumpából
	private Pipe out;
	
	public Pump() {
		super();
		in = null;
		out = null;
	}
	
	public Pump(Pipe i, Pipe o) {
		super();
		in = i;
		out = o;
	}
	
	public void setIn(Pipe new_p) { in = new_p; }
	
	public Pipe getIn() { return in; }
	
	public Pipe getOut() { return out; }
	
	public void setOut(Pipe new_p) { out = new_p; }
	
	// TODO ez nem kell ide, a Field-beli jo neki
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
	public boolean setPump(Pipe from , Pipe to) {		
		if(!neighbours.contains(to)) {
			System.out.println("A beallitando cso nem a pumpa szomszedja");
			return false;
		}
		if(!neighbours.contains(from)) {
			System.out.println("A lecserelendo cso nem a pumpa szomszedja");
			return false;
		}
		
		if(from.equals(in)) {
			from.removeOut(this);	// kene!!!!
			to.setOut(this);
			in = to;
			return true;
		} else if(from.equals(out)) {
			from.removeIn(this);	// kene!!!!!
			to.setIn(this);
			out = to;
			return true;
		} else {
			return false;	// from nem aktiv csove a pumpanak
		}
	}
	
	/*
	 * Field metódus felülírása. Egy Field-et hozzá lehet-e
	 *csatlakoztatni a meghívott pumpához
	 */
	public boolean acceptField(Field f) {
		if(neighbours.size() < 8) {		//TODO mennyi a max, vagy mi a feltetel??
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * A pumpa a bemenetéből átpumpálja a megfelelő mennyiségű vizet a kimenetén lévő csőbe
	 */
	public void pumpWater() {
		int out_capacity = out.getCapacity();
		int in_sizeOfWater = in.takeWater(out_capacity);
		out.flowWater(in_sizeOfWater);
	}
}
