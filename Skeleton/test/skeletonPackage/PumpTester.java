package skeletonPackage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// 16 - 18
public class PumpTester {

    public void loadfile(String text) {
        String[] fileload = new String[] { "load", "C:/tests/6.2.16", "" };
        fileload[2] = text;
        Program.load(fileload);
    }

    public void loadbroken(String text){
        String[] fileload = new String[] { "load", "C:/tests/6.2.17", "" };
        fileload[2] = text;
        Program.load(fileload);
    }

    /** 8.2.16 Ép pumpában víz mozgatása */
    @Test
    public void testPumpOK() {
        //giveAllWater pipe_1 10/10 -> pipe_2 0/10 -> 10
        loadfile("giveAllWater.txt");
        assertEquals(0, Program.getPipes().get("pipe_1").getWater());

        //giveHalfWater pipe_1 10/10 -> pipe_2 5/10 -> 5
        loadfile("giveHalfWater.txt");
        assertEquals(5, Program.getPipes().get("pipe_1").getWater());

        //giveLittleWater pipe_1 20/20 -> pipe_2 5/10 -> 5
        loadfile("giveLittleWater.txt");
        assertEquals(15, Program.getPipes().get("pipe_1").getWater());

        //giveNoWater pipe_1 10/10 -> pipe_2 10/10 -> 0
        loadfile("giveNoWater.txt");
        assertEquals(10, Program.getPipes().get("pipe_1").getWater());
        assertEquals(10, Program.getPipes().get("pipe_2").getWater());

        //noWater pipe_1 0/10 -> pipe_2 5/10 -> 0
        loadfile("noWater.txt");
        assertEquals(0, Program.getPipes().get("pipe_1").getWater());
        assertEquals(5, Program.getPipes().get("pipe_2").getWater());
    }

    /** 8.2.17 Törött pumpában víz (nem) mozgatása */
    @Test
    public void testPumpBroken() {
        //giveAllWater pipe_1 10/10 -> pipe_2 0/10 -> 10
        loadbroken("giveAllWater.txt");
        assertEquals(10, Program.getPipes().get("pipe_1").getWater());
        assertEquals(0, Program.getPipes().get("pipe_2").getWater());

        //giveHalfWater pipe_1 10/10 -> pipe_2 5/10 -> 5
        loadbroken("giveHalfWater.txt");
        assertEquals(5, Program.getPipes().get("pipe_2").getWater());

        //giveLittleWater pipe_1 20/20 -> pipe_2 5/10 -> 5
        loadbroken("giveLittleWater.txt");
        assertEquals(5, Program.getPipes().get("pipe_2").getWater());

        //giveNoWater pipe_1 10/10 -> pipe_2 10/10 -> 0
        loadbroken("giveNoWater.txt");
        assertEquals(10, Program.getPipes().get("pipe_2").getWater());

        //noWater pipe_1 0/10 -> pipe_2 5/10 -> 0
        loadbroken("noWater.txt");
        assertEquals(5, Program.getPipes().get("pipe_2").getWater());
    }

    /** 8.2.18 Pumpa eltörik */
    @Test
    public void testPumpBreak() {
        String[] fileload = new String[] { "load", "C:/tests", "6.2.18.txt" };
        Program.load(fileload);
        assertEquals(false,Program.getPumps().get("pump_1").getBroken());
    }
}
