/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    static int num = 6;

    /** Buggy implementation of nextNumber! */
    public static void nextNumber(int n) {
        if(n == Collatz.num)
            return;
        for(int i =0;i<n;i++){
            System.out.print('*');
        }
        System.out.println();
        nextNumber(n+1);
        return;
    }

    public static void main(String[] args) {
       nextNumber(1);
    }
}

