package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class Repair_Pipe {

	@Test
	public void test2() {
		String[] command = new String[] {"load", "C:/Tests", "test2.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getBroken() , false );
	}

}
