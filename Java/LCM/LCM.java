import java.util.Scanner;

/**
 * Emma Willard (x500: willa115, ID: 5040938).
 */

//Part 1

public class LCM {

    // Method that computes least common multiple.
    // Use of iteration
    public static int getLCM(int a, int b){

        int bigger = 0;
        int smaller = 0;
        int n;

        // Check if a or b are negative or zero.
        if(a <= 0 || b <= 0){
            return -1;
        }

        else {

            if(a > b){
                bigger = a;
                smaller = b;
            }

            if(a < b){
                bigger = b;
                smaller = a;
            }

            // If a and b are equal, their LCM is their equal value.
            if(a == b){
                return a;
            }

            // Calculating LCM in a for-loop/iteratively.
            for (n = 1; n <= bigger; n++) {
                    if ((n * bigger) % smaller == 0) {
                        return (n * bigger);
                    }

                }
            return n * bigger;
        }
    }

    // Method that computes greatest common divisor.
    // Use of recursion.
    public static int getGCD(int a, int b){
        int common;

        // Check for negative a or b.
        if(a < 0 || b < 0){
            return -1;
        }

        // Base case for when a%b is 0 in last return.
        if(b == 0){
            return a;
        }

        return getGCD(b, a%b);
    }

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to my LCM and GCD calculator.");
        System.out.println("Please enter the first number and second number separated with a space: ");
        String inputString = input.nextLine();
        Scanner scanner = new Scanner(inputString);
        int first = scanner.nextInt();
        int second = scanner.nextInt();

        // Error message. Check for "bad" values entered.
        if(getLCM(first, second) == -1 || getGCD(first, second) == -1){
            System.out.println("Error: one or both of the numbers entered are negative or equal to zero.");
        }
        if(first == 0 || second == 0){
            System.out.println("Error: entered a zero. Cannot compute LCM or GCD");

        }

        // If no error message, then LCM and GCD print to user.
        else {
            System.out.println("For the values " + first + " and " + second);
            System.out.println("The LCM is: " + getLCM(first, second));
            System.out.println("The GCD is: " + getGCD(first, second));
        }



    }

}
