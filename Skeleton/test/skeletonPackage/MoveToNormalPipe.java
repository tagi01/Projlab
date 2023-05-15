package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveToNormalPipe {

	@Test
	public void test9() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "test7.1.txt"};
		Program.load(command);
		assertSame(Program.getSaboteurs().get("Saboteur_1").getField(), Program.getPipes().get("pipe_1"));
		System.out.println("");
	}
	
	@Test
	public void test10() {
		String[] command = new String[] {"load", "C:\\Users\\Soma\\Desktop\\projlab", "test7.txt"};
		Program.load(command);
		assertSame(Program.getPlumbers().get("Plumber_1").getField(), Program.getPipes().get("pipe_1"));
		System.out.println("");
	}

}
