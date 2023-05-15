package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyFromNormal {

	@Test
	public void test4() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "sticky_from_normal_characters.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
		System.out.println("");
	}
	
	@Test
	public void test5() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "sticky_from_normal_p.txt"};
		Program.load(command);
		assertSame(Program.getPipes().get("pipe_1").getState(), StateOfPipe.SETSTICKY);
	}

}
