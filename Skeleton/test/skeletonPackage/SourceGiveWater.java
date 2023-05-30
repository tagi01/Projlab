package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class SourceGiveWater {

	@Test
	public void test1() {
		String[] command = new String[] {"load", "C:/Tests", "test1.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getWater(), 10);
		assertSame(Program.getPipes().get("pipe_2").getWater(), 10);
		assertSame(Program.getPipes().get("pipe_3").getWater(), 10);
	}

}
