package skeletonPackage;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestGrabPipeFromCistern {

	@Test
	public void test12() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test12.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_2"), Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}
	
	@Test
	public void test13() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test13.txt"};
		Program.load(command);
		assertNull(Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}
}
