package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGrabPipe {

	@Test
	public void test21() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test21.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_3"), Program.getPlumbers().get("Plumber_1").getInventoryPipe());
	}

}
