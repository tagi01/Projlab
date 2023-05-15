package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMoveFromStickyPipe {

	@Test
	public void test() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test8.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1"), Program.getSaboteurs().get("Saboteur_2").getField());
	}

}
