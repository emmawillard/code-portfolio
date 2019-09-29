import java.util.Arrays;
import java.util.Scanner;

/**
 * Emma Willard (x500: willa115, ID: 5040938)
 */

// RNG: randomnext = ((p1 * randomprevious) + p2) % M

public class Random {

    private int initSeed = 1;
    private int randomNext = 1;
    private int randomPrev = 1;
    private int initP1;
    private int initP2;
    private int initM;
    private int intSeed = 7;
    private int n = 1;

    // Check to see if seed is reset
    private boolean newSeed = false;


    public Random(int p1, int p2, int m){
        this.initP1 = p1;
        this.initP2 = p2;
        this.initM = m;

    }
    public void setSeed(int seed){
        randomPrev = seed;
        newSeed = true;
    }

    public int getMaximum(){
        return initM;
    }

    public int getRandom(){
        if(newSeed || randomPrev == 1){
            randomPrev = (initSeed*randomPrev)%initM;
            randomNext = ((initP1 * randomPrev) + initP2) % initM;
            randomPrev = randomNext;
            return randomNext;
        }

        else{
            randomNext = ((initP1 * randomPrev) + initP2) % initM;
            randomPrev = randomNext;
            newSeed = false;
            return randomNext;
        }

    }

    public int getRandomInteger(int lower, int upper){
        int temp;
        int temp2;

        if(lower > upper){
            temp = upper;
            temp2 = lower;
            lower = temp;
            upper = temp2;
        }

        if(lower<0){
            if(getRandomBoolean() == true){
                lower = lower * -1;
            }
            else{
                lower = (lower+(2*lower));
            }
    }



        initM = upper;
        int range = upper - lower + 1;

        int ratio = ((initP1*intSeed) + initP2)%initM+1;

        intSeed = (((getRandom()*(initP1*intSeed) + initP2) % initM) + (lower*intSeed) + n++ - 1)%initM;
        return ratio;
    }

    public boolean getRandomBoolean(){
        int value = getRandomInteger(1,100);
        if(value < 50){
            return true;
        }
        else{
            return false;}}

    public int getRandomItem(int[] array){
        int idx = getRandomInteger(1, array.length +1);
        return array[idx - 1];
    }

    public int[] getRandomIntegerArray(int n, int lower, int upper){
        int[] randIntArray = new int[n];
        int temp;
        int temp2;

        if(lower > upper){
            temp = upper;
            temp2 = lower;
            lower = temp;
            upper = temp2;
        }

        for(int i = 0; i < randIntArray.length; i++){
            randIntArray[i] = getRandomInteger(lower, upper);
        }
        return randIntArray;
    }

    public double getRandomDouble(double lower, double upper){
        double temp;
        double temp2;

        // If upper is smaller than lower, they switch.
        if(lower > upper){
            temp = upper;
            temp2 = lower;
            lower = temp;
            upper = temp2;
        }
        double upperDeci = upper - (int)upper;

        int ones = getRandomInteger((int)lower, (int)upper);
        String decimal = ".";
        for(int i = 0; i < 10; i++){
            int newRandDeci = getRandomInteger(0, 9);
            double deciPlace = 1;

            for(int j = 0; j <= i; j++){
                deciPlace = deciPlace*10;
            }

            upperDeci = upperDeci*deciPlace;

           // if((newRandDeci*deciPlace) <= (upperDeci)){
                decimal = decimal + newRandDeci;//}

            //if((newRandDeci*deciPlace) > (upperDeci)){
                //decimal = decimal + "0";
            //}
        }

        double randDoub = Double.parseDouble(ones + decimal);
        return randDoub;
    }

    public static void main(String[] arg){
        Scanner input = new Scanner(System.in);
        Prime p = new Prime();
        Random r = new Random(p.getPrime(300), p.getPrime(320), p.getPrime(330));
        System.out.println("Enter an integer for the seed value.");
        int n = input.nextInt();
        r.setSeed(n);

        //for(int i = 0; i < 50;i++) System.out.println(r.getRandomInteger(0,5));

        System.out.println(Arrays.toString(r.getRandomIntegerArray(100, -5, 5)));

    }
}
