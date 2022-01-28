public class DogLoop {
    public static void main(String[] args) {
        Dog smallDog = new Dog(5);
        Dog mediumDog = new Dog(25);
        Dog hugeDog = new Dog(150);

        Dog[] manyDogs = new Dog[4];
        manyDogs[0] = smallDog;
        manyDogs[1] = hugeDog;
        manyDogs[2] = new Dog(130);


    }

    public static class Dog {
        /* Size of the dog in standard dog size units. */
        public int size;

        /* This is a constructor. It tells us how to construct
         * dogs from our ideal notion of dogness. */

        public Dog(int s) {
            size = s;
        }

        public void makeNoise() {
            if (size < 10) {
                System.out.println("hideous yapping");
            } else if (size < 30) {
                System.out.println("bark!");
            } else {
                System.out.println("woof!");
            }
        }

    }
}
