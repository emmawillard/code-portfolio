// Imported for testing purposes.
//import java.util.Arrays;

/**
 * Emma Willard (x500: willa115, ID: 5040938)
 */

// Part 2
public class Prime {

    public static boolean isPrime(int number){

        // If number is 1, 0, or negative, it is not prime.
        if(number <= 1){
            return false;
        }

        // Loop checks for first/any divisor for number besides
        // itself and 1.
        for(int i = 2; number > i; i++){

            // If loop gets far enough to where number is equal to number,
            // the number is confirmed to be prime.
            if(number == i){
                return true;
                }

            // If there's no remainder when number is divided by i,
            // it is not a prime number.
            if(number%i == 0){
                return false;

                }

            }
        return true;

    }

    public static int getPrime(int n){
        int count = 0;
        int newPrime = 0;

        // First prime number is 2.
        // If 0 or a negative number is entered,
        // defaults to returning first prime.
        if(n <= 1){
            return 2;
        }

        else{

            // Loop increments up until the nth integer.
            // Every prime is counted along the way,
            // and will eventually return the nth prime.
            for(int i = 2; n > count; i++){
                if(isPrime(i) == true){
                    count++;
                    newPrime = i;
                }
            }
            return newPrime;
        }
    }

    public static int[] getPrimeArray(int n){
        int[] primeArray = new int[n];
        for(int i = 0; primeArray.length > i; i++){

            // Assign index to i-th/n-th prime number
            primeArray[i] = getPrime(i+1);
        }
        return primeArray;
    }

    public static void main(String[] args){

        // Array of first 100 prime numbers
        int[] firstHundred = getPrimeArray(100);

        // Print array without use of Arrays.toString
        for (int i = 0; firstHundred.length > i; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(firstHundred[i]);
        }

        // Print array with use of Arrays.toString
        //System.out.println(Arrays.toString(getPrimeArray(100)));

    }

}
