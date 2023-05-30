package skeletonPackage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// 19 Csövet kilyukasztják
public class TestPuncturePipe {

    @Test
    public void testBreakPump() {
        String[] fileload = new String[] { "load", "C:/Tests", "test19.txt" };
        Program.load(fileload);
        assertEquals(true,Program.getPipes().get("pipe_1").getBroken());
    }

}
