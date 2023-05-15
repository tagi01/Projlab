package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyAfterSlippery {

	@Test
	public void test6() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "S_sticky_after_slippery.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SLIPPERY);
		System.out.println("");
	}

}
