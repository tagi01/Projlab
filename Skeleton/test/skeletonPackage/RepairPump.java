package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepairPump {

	@Test
	public void test3() {
		String[] command = new String[] {"load", "C:/Tests", "test3.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getBroken() , false );
	}

}
