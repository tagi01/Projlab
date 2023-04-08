package skeletonPackage;

public class Source extends Field{
	/*
	 * Field metódus felülírása. Logikai változó a visszatérési
	 *értéke. True, ha hozzá lehet csatlakoztatni a mezőt, False, ha nem
	 */
	public boolean acceptField(Field f) {
			//TODO
		return true;
	}
	/*
	 * A metódus meghívásakor a hozzá csatlakoztatott csöveknek
	 *maximális mennyiségű vizet ad (a csövek pedig beállítják maguknak, hogy a water a
	 *kapacitásuk szerint a legtöbb legyen)
	 */
	public void getWater() {}
}
