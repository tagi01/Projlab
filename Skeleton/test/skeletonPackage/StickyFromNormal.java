package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyFromNormal {

	@Test
	public void test4() {
		String[] command = new String[] {"load", "C:/Tests", "test4.1.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
		System.out.println("");
	}
	
	@Test
	public void test5() {
		String[] command = new String[] {"load", "C:/Tests", "test4.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
	}

}
