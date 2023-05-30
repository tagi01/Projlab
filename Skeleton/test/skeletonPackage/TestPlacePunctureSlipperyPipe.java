package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlacePunctureSlipperyPipe {
	@Test
	public void test28() {
		String[] command = new String[] {"load", "C:/Tests", "test28.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
		assertTrue(Program.getPumps().get("pump_1").getNeighbours().contains(Program.getPipes().get("pipe_1")));
	}
	
	@Test
	public void test29() {
		String[] command = new String[] {"load", "C:/Tests", "test29.txt"};
		Program.load(command);
		assertNotNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
		assertFalse(Program.getPumps().get("pump_1").getNeighbours().contains(Program.getPipes().get("pipe_1")));
	}
	
	@Test
	public void test30() {
		String[] command = new String[] {"load", "C:/Tests", "test30.txt"};
		Program.load(command);
		assertNotEquals(Program.getPipes().get("pipe_1").getCantPuncture(), 0);
	}
	
	@Test
	public void test31() {
		String[] command = new String[] {"load", "C:/Tests", "test31.txt"};
		Program.load(command);
		assertEquals(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SLIPPERY);
	}
	
	@Test
	public void test32() {
		String[] command = new String[] {"load", "C:/Tests", "test32.txt"};
		Program.load(command);
		assertEquals(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SLIPPERY);
		assertEquals(Program.getPipes().get("pipe_2").getState(), StateOfPipe.STICKY);
	}
}
