package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> right= new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        for(int i = 4;i<7;i++){
            right.addLast(i);
            buggy.addLast(i);
        }
        for(int i = 6;i>=4;i--){
            int p = right.removeLast();
            int q = buggy.removeLast();

            assertEquals(i,p);
            assertEquals(i,q);
        }
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correct_size = correct.size();
                int broken_size = broken.size();
                System.out.println("correct_size: " + correct_size);
                System.out.println("broken_size: " + broken_size);
                assertEquals(correct_size,broken_size);
            } else if (operationNumber == 2){
                if (broken.size() == 0 || correct.size() == 0)
                    continue;
                int correct_val = correct.getLast();
                int broken_val = broken.getLast();
                assertEquals(correct_val,broken_val);
                System.out.println("getlast(" + correct_val + ")");
            } else if( operationNumber == 3){
                if (broken.size() == 0 || correct.size() == 0)
                    continue;
                int correct_val = correct.removeLast();
                int broken_val = broken.removeLast();
                assertEquals(correct_val,broken_val);
                System.out.println("Removelast(" + correct_val + ")");
            }
        }
    }

}
