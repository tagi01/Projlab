package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyAfterSlippery {

	@Test
	public void test6() {
		String[] command = new String[] {"load", "C:/Tests", "test5.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SLIPPERY);
		System.out.println("");
	}

}
