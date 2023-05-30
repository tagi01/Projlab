package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSetPump {
	@Test
	public void test27() {
		String[] command = new String[] {"load", "C:/Tests", "test27.txt"};
		Program.load(command);
		assertEquals(Program.getPumps().get("pump_1").getOut(), Program.getPipes().get("pipe_3"));
		assertEquals(Program.getPumps().get("pump_1").getIn(), Program.getPipes().get("pipe_1"));
		assertEquals(Program.getPumps().get("pump_2").getOut(), Program.getPipes().get("pipe_1"));
		assertEquals(Program.getPumps().get("pump_2").getIn(), Program.getPipes().get("pipe_3"));
	}
}
