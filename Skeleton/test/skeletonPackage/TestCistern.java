package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCistern {
	@Test
	public void test20() {
		String[] command = new String[] {"load", "C:/Projlab/8", "test20.txt"};
		Program.load(command);
		assertEquals(Program.getPipes().get("pipe_1").getWater(),0);
		assertEquals(Program.getPipes().get("pipe_2").getWater(),0);
		assertEquals(Program.getPipes().get("pipe_3").getWater(),0);
	}
}
