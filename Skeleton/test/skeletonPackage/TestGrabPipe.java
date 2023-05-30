package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGrabPipe {

	@Test
	public void test21() {
		String[] command = new String[] {"load", "C:/Tests", "test21.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_3"), Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}
	
	@Test
	public void test22() {
		String[] command = new String[] {"load", "C:/Tests", "test22.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}
	
	@Test
	public void test23() {
		String[] command = new String[] {"load", "C:/Tests", "test23.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}

	@Test
	public void test24() {
		String[] command = new String[] {"load", "C:/Tests", "test24.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}

	@Test
	public void test25() {
		String[] command = new String[] {"load", "C:/Tests", "test25.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_3"), Program.getPlumbers().get("Plumber_1").getInventoryPipe());
		assertEquals(2, Program.getPlumbers().get("Plumber_1").getPipeEnds());
	}

	@Test
	public void test26() {
		String[] command = new String[] {"load", "C:/Tests", "test26.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}

}
