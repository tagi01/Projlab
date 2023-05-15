package skeletonPackage;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestGrabPumpFromCistern {
	
	@Test
	public void test10() {
		String[] command = new String[] {"load", "C:/Tests", "test10.txt"};
		Program.load(command);
		assertSame(Program.getPumps().get("pump_2"), Program.getPlumbers().get("Plumber_1").getInventoryPump());
	}
	
	@Test
	public void test11() {
		String[] command = new String[] {"load", "C:/Tests", "test11.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPump());
	}
}
