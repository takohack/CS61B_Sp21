package game2048;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestFourNeibour {
    /** The Board that we'll be testing on. */
    static Board b;


    @Test
    /** Tests a board where a tilt in any direction would cause a change. */
    public void testFourNieghbor() {
        //坐标轴 在左下角
        int[][] rawVals = new int[][] {
                {2, 4, 2, 2},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
        };

        b = new Board(rawVals, 0);
//        int[] ans = {4,4,4,4};
//        assertArrayEquals(ans,Model.four_neighbor(b,2,1));

//        int[] ans2 = {2,0,2,0};
//        assertArrayEquals(ans2,Model.four_neighbor(b,0,0));

        int[] ans3 = {0,2,2,2};
        assertArrayEquals(ans3,Model.four_neighbor(b,3,1));
    }
}
