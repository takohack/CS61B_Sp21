public class Dog {

    public static void mult(int[] A){
        for(int i = 0;i<A.length;i++){
            A[i] *= 3;
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2,3,4,5};
        mult(arr);

        for(int x : arr){
            System.out.println(x);
        }
    }
}