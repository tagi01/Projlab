package skeletonPackage;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPlacePump {

	@Test
	public void test14() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test14.txt"};
		Program.load(command);
		assertTrue(Program.getPumps().get("pump_3").getNeighbours().contains(Program.getPipes().get("pipe_1")));
		//assertTrue(Program.getPumps().get("pump_3").getNeighbours().contains(Program.getPipes().get("pipe_2")));
		assertEquals(Program.getPumps().get("pump_3").getNeighbours().size(), 2);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPump());

	}
	@Test
	public void test15() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test15.txt"};
		Program.load(command);
		assertTrue(Program.getPumps().get("pump_3").getNeighbours().contains(Program.getPipes().get("pipe_1")));
		//assertTrue(Program.getPumps().get("pump_3").getNeighbours().contains(Program.getPipes().get("pipe_2")));
		assertEquals(Program.getPumps().get("pump_3").getNeighbours().size(), 2);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPump());
	}
}
