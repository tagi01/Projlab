package skeletonPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveToSlipperyPipe {

    @Test
    public void moveToSlipperyPipe() {
        String[] command = new String[] {"load", "C:/Tests", "test9.txt"};
        Program.load(command);
        assertNotSame(Program.getSaboteurs().get("Saboteur_1").getField(), Program.getPipes().get("pipe_1"));
    }

}
