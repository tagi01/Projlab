package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyAfterSticky {

	@Test
	public void test7() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "k_sticky_after_sticky.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
		System.out.println("");
	}
	
	@Test
	public void test8() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "s_sticky_after_sticky.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
		System.out.println("");
	}

}
