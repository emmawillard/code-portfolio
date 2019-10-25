import java.util.Scanner;

/**
 * Emma Willard 
 * This code needs to be cleaned up and optimized!
 */

//Run BattleshipBoard's main method to play
public class BattleshipBoard {
    // When all ships are sunk, finish will turn true
    public boolean finish = false;
    // Record turns
    private int turns = 1;
    public int getTurns(){
        return turns;
    }

    // Board rows
    public int m;
    // Board columns
    public int n;

    // For reference in the arrays:
    // Char 1, 2, 3, 4, 5 are ships
    // 1's are the first (as well as largest) ships placed, 2's are the second (as well as second largest) ships placed, etc
    // This is to differentiate between ships
    // - means blank/untouched area
    public char[][] boardArray;
    public char[][] debugArray;

    public BattleshipBoard(int m, int n){
        this.m = m;
        this.n = n;

        boardArray = new char[m][n];
        for (int row = 0; row < boardArray.length; row ++){
            for (int col = 0; col < boardArray[0].length; col++){
                boardArray[row][col] = '-';}}

        }

    // Check if a specific ship is sunk
    // Ships denoted by 1, 2, 3, 4, 5
    // help for this method inspired from:
    // http://stackoverflow.com/questions/23069740/check-if-value-exists-in-a-multidimensional-array-java
    private boolean checkForShip(char shipType){
        for(int i = 0; i < debugArray.length; i++){
            for(int j = 0; j <debugArray[0].length; j++){
                if(debugArray[i][j] == shipType){
                    return true;}
            }
        }
        return false;
    }

    // M means miss
    // X means hit
    // - has no ship or is blank on non-debug board
    // Method determines what to do with the given coordinates
    public void moves(int coord1, int coord2){
        // Out of bounds
        if(coord1 > m-1 || coord2 > n-1 || coord1 < 0 || coord2 < 0){
            System.out.println("Those coordinates are are out of bounds.");
            System.out.println("Penalty. Lose a turn.");
            turns++;
            System.out.println("Turn " + getTurns() + " skipped.");
            turns++;
        }
        // No ship, misses
        else if(debugArray[coord1][coord2] == '-'){
            System.out.println("No ship here. That's a miss.");
            boardArray[coord1][coord2] = 'M';
            debugArray[coord1][coord2] = 'M';
            turns++;
        }

        // Miss turn, hit same place more than once
        else if(debugArray[coord1][coord2] == 'M' || debugArray[coord1][coord2] == 'X'){
            System.out.println("You've already tried this spot.");
            System.out.println("Penalty. Lose a turn.");
            turns++;
            System.out.println("Turn " + getTurns() + " skipped.");
            turns++;
        }

        // Hit a ship
        else{
            char whichShip = debugArray[coord1][coord2];
            debugArray[coord1][coord2] = 'X';
            boardArray[coord1][coord2] = 'X';
            System.out.println("You hit a ship!");
            turns++;

            // Check if ship sinks, search array
            if(checkForShip(whichShip) == false){
                System.out.println("You've sunk a ship!");
            }
            // If all ships are gone after this hit, the game is over
            if(checkForShip('1') == false && checkForShip('2') == false && checkForShip('3') == false && checkForShip('4') == false && checkForShip('5') == false){
                finish = true;
            }
        }


    }

    //This is where the debug board and ship placement is initialized.
    //This contains a lot of repeated algorithms as more ships need to be added,
    //So the code is a little dense.
    //
    //The main idea of my ship-placing algorithm is picking a random point (x,y).
    //Then, a random position, vertical or horizontal, is chosen.
    //Based on position, it checks first if the next part of the ship can be added to the 'front', otherwise check for back.
    //It will continue to check forward or backward on the board to place the ship until the needed length is achieved.
    //
    //isPlaced, which appears later in the algorithm, double checks that a ship was placed.
    //If a ship isn't placed, a new (x,y) coordinate will be randomized to try to place it.
    public char[][] debugBoard(){
        debugArray = new char[m][n];
        for (int row = 0; row < debugArray.length; row ++){
            for (int col = 0; col < debugArray[0].length; col++){
                debugArray[row][col] = '-';}}


        int indexM = m - 1;
        int indexN = n - 1;

        // "There will be one ship of size 2 to find."
        // 1 ship total
        if(m == 2 || n == 2){
            // Creating ship of size 2
            int x = (int) Math.floor((Math.random() * debugArray.length));
            int y = (int) Math.floor((Math.random() * debugArray[0].length));
            // Position: 0 is vertical, 1 is horizontal
            int position = (int) Math.floor((Math.random() * 2));

            //Vertical case
            if(position == 0 && (x+1) <= indexM){
                debugArray[x][y] = '1';
                debugArray[x+1][y] = '1';
            }
            else if(position == 0 && (x+1) > indexM){
                debugArray[x-1][y] = '1';
                debugArray[x][y] = '1';
            }
            //Horizontal case
            else if(position == 1 && (y+1) <= indexN){
                debugArray[x][y] = '1';
                debugArray[x][y+1] = '1';
            }
            else if(position == 1 && (y+1) > indexN){
                debugArray[x][y-1] = '1';
                debugArray[x][y] = '1';
            }


        }

        // "There will be one ship of size 2 and one ship of size 3 to find."
        // 2 ships total
        else if((2 < m && m <= 4 ) || (2 < n && n <= 4)){
            boolean isPlaced = false;

            // Create ship of size 3
            int x = (int) Math.floor((Math.random() * debugArray.length));
            int y = (int) Math.floor((Math.random() * debugArray[0].length));
            // Position: 0 is vertical, 1 is horizontal
            int position = (int) Math.floor((Math.random() * 2));

            if(position == 0 && (x+1) <= indexM){
                if(x+2 <= indexM){
                    debugArray[x][y] = '1';
                    debugArray[x+1][y] = '1';
                    debugArray[x+2][y] = '1';

                }
                else if(x-1 <= indexM && x-1 >= 0){
                    debugArray[x-1][y] = '1';
                    debugArray[x][y] = '1';
                    debugArray[x+1][y] = '1';
                }
            }
            else if(position == 0 && (x+1) > indexM){
                if(x-2 <= indexM && x-2 >= 0){
                    debugArray[x-2][y] = '1';
                    debugArray[x-1][y] = '1';
                    debugArray[x][y] ='1';
                }

            }
            else if(position == 1 && (y+1) <= indexN){

                if(y+2 <= indexN){
                    debugArray[x][y] ='1';
                    debugArray[x][y+1] = '1';
                    debugArray[x][y+2] = '1';
                }
                else if(y-1 <= indexN && y-1 >= 0){
                    debugArray[x][y-1] = '1';
                    debugArray[x][y] ='1';
                    debugArray[x][y+1] = '1';
                }
            }
            else if(position == 1 && (y+1) > indexN){
                if(y-2 <= n && y-2 >= 0){
                    debugArray[x][y-2] = '1';
                    debugArray[x][y-1] = '1';
                    debugArray[x][y] ='1';
                }

            }


            // Create ship of size 2
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));
            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}

                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));

                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                    debugArray[x][y] = '2';
                    debugArray[x+1][y] = '2';
                    isPlaced = true;
                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                    debugArray[x-1][y] = '2';
                    debugArray[x][y] = '2';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                    debugArray[x][y] = '2';
                    debugArray[x][y+1] = '2';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) > indexN && debugArray[x][y-1] == '-'){
                    debugArray[x][y-1] = '2';
                    debugArray[x][y] = '2';
                    isPlaced = true;
                }}

        }

        //"There will be one ship of size 2 and two ships of size 3 to find."
        else if((4 < m && m <= 6) || (4 < n && n <= 6)){
            boolean isPlaced = false;

            // Create first ship of size 3
            int x = (int) Math.floor((Math.random() * debugArray.length));
            int y = (int) Math.floor((Math.random() * debugArray[0].length));
            // Position: 0 is vertical, 1 is horizontal
            int position = (int) Math.floor((Math.random() * 2));
            if(position == 0 && (x+1) <= indexM){
                if(x+2 <= indexM){
                    debugArray[x][y] ='1';
                    debugArray[x+1][y] = '1';
                    debugArray[x+2][y] = '1';
                }
                else if(x-1 <= indexM && x-1 >= 0){
                    debugArray[x-1][y] = '1';
                    debugArray[x][y] ='1';
                    debugArray[x+1][y] = '1';
                }
            }
            else if(position == 0 && (x+1) > indexM){
                if(x-2 <= indexM && x-2 >= 0){
                    debugArray[x-2][y] = '1';
                    debugArray[x-1][y] = '1';
                    debugArray[x][y] ='1';
                }
                else if(x+1 <= indexM){
                    debugArray[x-1][y] = '1';
                    debugArray[x][y] ='1';
                    debugArray[x+1][y] = '1';
                }
            }
            else if(position == 1 && (y+1) <= indexN){

                if(y+2 <= indexN){
                    debugArray[x][y] ='1';
                    debugArray[x][y+1] = '1';
                    debugArray[x][y+2] = '1';
                }
                else if(y-1 <= indexN && y-1 >= 0){
                    debugArray[x][y-1] = '1';
                    debugArray[x][y] ='1';
                    debugArray[x][y+1] = '1';
                }
            }
            else if(position == 1 && (y+1) > indexN){
                if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                    debugArray[x][y-2] = '1';
                    debugArray[x][y-1] = '1';
                    debugArray[x][y] ='1';
                }
                else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                    debugArray[x][y-1] = '1';
                    debugArray[x][y] ='1';
                    debugArray[x][y+1] = '1';
                }
            }


            // Create second ship of size 3
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));

            position = (int) Math.floor((Math.random() * 2));
            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}

                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){

                    if(x+2 <= indexM && debugArray[x+2][y] == '-' ){
                        debugArray[x][y] = '2';
                        debugArray[x+1][y] = '2';
                        debugArray[x+2][y] = '2';
                        isPlaced = true;
                    }
                    else if(x-1 <= indexM && x-1 >= 0 && debugArray[x-1][y] == '-' ){
                        debugArray[x-1][y] = '2';
                        debugArray[x][y] = '2';
                        debugArray[x+1][y] = '2';
                        isPlaced = true;
                    }
                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                    if(x-2 <= indexM && x-2 >= 0 &&  debugArray[x-2][y] == '-'){
                        debugArray[x-2][y] = '2';
                        debugArray[x-1][y] = '2';
                        debugArray[x][y] = '2';
                        isPlaced = true;
                    }
                    else if(x+1 <= indexM && debugArray[x+1][y] == '-'){
                        debugArray[x-1][y] = '2';
                        debugArray[x][y] = '2';
                        debugArray[x+1][y] = '2';
                        isPlaced = true;
                    }
                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){

                    if(y+2 <= indexN && debugArray[x][y+2] == '-'){
                        debugArray[x][y] = '2';
                        debugArray[x][y+1] = '2';
                        debugArray[x][y+2] = '2';
                        isPlaced = true;
                    }
                    else if(y-1 <= indexN && y-1 <= 0 && debugArray[x][y-1] == '-'){
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] = '2';
                        debugArray[x][y+1] = '2';
                        isPlaced = true;
                    }
                }
                else if(position == 1 && (y+1) > indexN){

                    if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                        debugArray[x][y-2] = '2';
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] = '2';
                        isPlaced = true;
                    }
                    else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] = '2';
                        debugArray[x][y+1] = '2';
                        isPlaced = true;
                    }
                }}
            isPlaced = false;

            // Create ship of size 2
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));

            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}

                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));


                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                    debugArray[x][y] = '3';
                    debugArray[x+1][y] = '3';
                    isPlaced = true;
                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                    debugArray[x-1][y] = '3';
                    debugArray[x][y] = '3';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                    debugArray[x][y] = '3';
                    debugArray[x][y+1] = '3';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) > indexN && debugArray[x][y-1] == '-'){
                    debugArray[x][y-1] = '3';
                    debugArray[x][y] = '3';
                    isPlaced = true;
                }}
        }

        //"There will be one ship of size 2, two ships of size 3, and one ship of size 4 to find."
        //4 ships total
        else if((6 < m && m <= 8) || (6 < n && n <= 8)){
            // Make sure a ship is placed
            boolean isPlaced = false;

            // Create ship of size 4
            int x = (int) Math.floor((Math.random() * debugArray.length));
            int y = (int) Math.floor((Math.random() * debugArray[0].length));
            // Position: 0 is vertical, 1 is horizontal
            int position = (int) Math.floor((Math.random() * 2));


            while(debugArray[x][y] != '-'){
                x = (int) Math.floor((Math.random() * debugArray.length));
                y = (int) Math.floor((Math.random() * debugArray[0].length));}
            if(position == 0 && (x+1) <= indexM){
                if(x+2 <= indexM){
                    if(x+3 <= indexM){
                        debugArray[x][y] ='1';
                        debugArray[x+1][y] = '1';
                        debugArray[x+2][y] = '1';
                        debugArray[x+3][y] = '1';
                    }
                    else if(x-1 <= indexM && x-1 >= 0){
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x+1][y] = '1';
                        debugArray[x+2][y] = '1';
                    }
                }
                else if(x-1 <= indexM && x-1 >= 0){
                    if(x-2 <= indexM && x-2 >= 0){
                        debugArray[x-2][y] = '1';
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] = '1';
                        debugArray[x+1][y] = '1';
                    }
                    else if(x-3 <= indexM && x-3 >= 0){
                        debugArray[x-3][y] = '1';
                        debugArray[x-2][y] = '1';
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] ='1';
                    }
                }
            }
            else if(position == 0 && (x+1) > indexM){
                if(x-2 <= indexM && x-2 >= 0){
                    if(x-3 <= indexM){
                        debugArray[x-3][y] = '1';
                        debugArray[x-2][y] = '1';
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] ='1';}
                    else if(x-1 <= indexM && x-1 >= 0){
                        debugArray[x-2][y] = '1';
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x+1][y] = '1';}
                }
                else if(x+1 <= indexM){
                    if(x+2 <= indexM){
                        debugArray[x-1][y] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x+1][y] = '1';
                        debugArray[x+2][y] = '1';}
                    else if(x+3 <= indexM){
                        debugArray[x][y] = '1';
                        debugArray[x+1][y] ='1';
                        debugArray[x+2][y] = '1';
                        debugArray[x+3][y] = '1';}
                }
            }
            else if(position == 1 && (y+1) <= indexN){
                if(y+2 <= indexN){
                    if(y+3 <= indexM){
                        debugArray[x][y] ='1';
                        debugArray[x][y+1] = '1';
                        debugArray[x][y+2] = '1';
                        debugArray[x][y+3] = '1';}
                    else if(y-1 <= indexM && x-1 >= 0){
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x][y+1] = '1';
                        debugArray[x][y+2] = '1';}
                }
                else if(y-1 <= indexN && y-1 >= 0){
                    if(y-2 <= indexM && y-2 >= 0){
                        debugArray[x][y-2] = '1';
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x][y+1] = '1';}
                    else if(y-3 <= indexM && y-3 >= 0){
                        debugArray[x][y-3] = '1';
                        debugArray[x][y-2] = '1';
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] ='1';}
                }
            }
            else if(position == 1 && y+1 >= indexN){
                if(y-2 <= n && y-2 >= 0 && debugArray[x][y-2] == '-'){
                    if(y-3 <= indexM){
                        debugArray[x][y-3] = '1';
                        debugArray[x][y-2] = '1';
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] = '1';}
                    else if(y-1 <= indexM && y-1 >= 0){
                        debugArray[x][y-2] = '1';
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] = '1';
                        debugArray[x][y+1] = '1';}
                }
                else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                    if(y+2 <= indexM){
                        debugArray[x][y-1] = '1';
                        debugArray[x][y] ='1';
                        debugArray[x][y+1] = '1';
                        debugArray[x][y+2] = '1';}
                    else if(y+3 <= indexM){
                        debugArray[x][y] ='1';
                        debugArray[x][y+1] = '1';
                        debugArray[x][y+2] = '1';
                        debugArray[x][y+3] = '1';}
                }
            }


            // Create first ship of size 3
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));
            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}
                position = (int) Math.floor((Math.random() * 2));
                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                    if(x+2 <= indexM && debugArray[x+2][y] == '-'){
                        debugArray[x][y] ='2';
                        debugArray[x+1][y] = '2';
                        debugArray[x+2][y] = '2';
                        isPlaced = true;
                    }
                    else if(x-1 <= indexM && x-1 >= 0 && debugArray[x-1][y] == '-'){
                        debugArray[x][y] ='2';
                        debugArray[x+1][y] = '2';
                        debugArray[x-1][y] = '2';
                        isPlaced = true;
                    }

                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                    if(x-2 <= indexM && x-2 >= 0 && debugArray[x-2][y] == '-'){
                        debugArray[x-2][y] = '2';
                        debugArray[x-1][y] = '2';
                        debugArray[x][y] ='2';
                        isPlaced = true;
                    }
                    else if(x+1 <= indexM && debugArray[x+1][y] == '-'){
                        debugArray[x-1][y] = '2';
                        debugArray[x][y] ='2';
                        debugArray[x+1][y] = '2';
                        isPlaced = true;
                    }

                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                    if(y+2 <= indexN && debugArray[x][y+2] == '-'){
                        debugArray[x][y] ='2';
                        debugArray[x][y+1] = '2';
                        debugArray[x][y+2] = '2';
                        isPlaced = true;
                    }
                    else if(y-1 <= indexN && y-1 <= 0 && debugArray[x][y-1] == '-'){
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] ='2';
                        debugArray[x][y+1] = '2';
                        isPlaced = true;
                    }

                }
                else if(position == 1 && (y+1) > indexN && debugArray[x][y-1] == '-'){
                    if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                        debugArray[x][y-2] = '2';
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] ='2';
                        isPlaced = true;
                    }
                    else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                        debugArray[x][y-1] = '2';
                        debugArray[x][y] ='2';
                        debugArray[x][y+1] = '2';
                        isPlaced = true;
                    }

                }
                }
            isPlaced = false;

            // Create second ship of size 3
            while(!isPlaced){
                x = (int) Math.floor((Math.random() * debugArray.length));
                y = (int) Math.floor((Math.random() * debugArray[0].length));
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}
                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));
                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                    if(x+2 <= indexM && debugArray[x+2][y] == '-'){
                        debugArray[x][y] ='3';
                        debugArray[x+1][y] = '3';
                        debugArray[x+2][y] = '3';
                        isPlaced = true;
                    }
                    else if(x-1 <= indexM && x-1 >= 0 && debugArray[x-1][y] == '-'){
                        debugArray[x-1][y] = '3';
                        debugArray[x][y] ='3';
                        debugArray[x+1][y] = '3';
                        isPlaced = true;
                    }
                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){

                    if(x-2 <= indexM && x-2 >= 0 && debugArray[x-2][y] == '-'){
                        debugArray[x-2][y] = '3';
                        debugArray[x-1][y] = '3';
                        debugArray[x][y] ='3';
                        isPlaced = true;
                    }
                    else if(x+1 <= indexM && debugArray[x+1][y] == '-'){
                        debugArray[x-1][y] = '3';
                        debugArray[x][y] ='3';
                        debugArray[x+1][y] = '3';
                        isPlaced = true;
                    }
                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                    if(y+2 <= indexN && debugArray[x][y+2] == '-'){
                        debugArray[x][y] ='3';
                        debugArray[x][y+1] = '3';
                        debugArray[x][y+2] = '3';
                        isPlaced = true;
                    }
                    else if(y-1 <= indexN && y-1 <= 0 && debugArray[x][y-1] == '-'){
                        debugArray[x][y-1] = '3';
                        debugArray[x][y] ='3';
                        debugArray[x][y+1] = '3';
                        isPlaced = true;
                    }
                }
                else if(position == 1 && (y+1) > indexN){

                    if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                        debugArray[x][y-2] = '3';
                        debugArray[x][y-1] = '3';
                        debugArray[x][y] ='3';
                        isPlaced = true;
                    }
                    else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                        debugArray[x][y-1] = '3';
                        debugArray[x][y] ='3';
                        debugArray[x][y+1] = '3';
                        isPlaced = true;
                    }
                }}
            isPlaced = false;

            // Create ship of size 2
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));
            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}

                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));


                if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                    debugArray[x][y] = '4';
                    debugArray[x+1][y] = '4';
                    isPlaced = true;
                }
                else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                    debugArray[x-1][y] = '4';
                    debugArray[x][y] = '4';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                    debugArray[x][y] = '4';
                    debugArray[x][y+1] = '4';
                    isPlaced = true;
                }
                else if(position == 1 && (y+1) > indexN && debugArray[x][y-1] == '-'){
                    debugArray[x][y-1] = '4';
                    debugArray[x][y] = '4';
                    isPlaced = true;
                }}


        }

        //"There will be one ship of size 2, two ships of size 3, one ship of size 4, and one ship of size 5 to find."
        // 5 ships total
        else if((8 < m && m <= 10) || (8 < n && n <= 10) || (m == 10 && n == 10)){

            boolean isPlaced = false;

            // Create ship of size 5
            int x;
            int y;
            // Position: 0 is vertical, 1 is horizontal
            int position;
            while(!isPlaced){
                x = (int) Math.floor((Math.random() * debugArray.length));
                y = (int) Math.floor((Math.random() * debugArray[0].length));
                position = (int) Math.floor((Math.random() * 2));
                if(x+2 <= indexM){
                    if(x+3 <= indexM){
                        if(x+4 <= indexM){
                            debugArray[x][y] = '1';
                            debugArray[x+1][y] = '1';
                            debugArray[x+2][y] = '1';
                            debugArray[x+3][y] = '1';
                            debugArray[x+4][y] = '1';
                            isPlaced = true;
                        }
                        else if(x-1 <= indexM && x-1 >= 0){
                            debugArray[x-1][y] = '1';
                            debugArray[x][y] = '1';
                            debugArray[x+1][y] = '1';
                            debugArray[x+2][y] = '1';
                            debugArray[x+3][y] = '1';
                            isPlaced = true;}
                    }
                    else if(x-1 <= indexM && x-1 >= 0){
                        if(x-2 <= indexM && x-2 >= 0){
                            debugArray[x-2][y] = '1';
                            debugArray[x-1][y] = '1';
                            debugArray[x][y] ='1';
                            debugArray[x+1][y] = '1';
                            debugArray[x+2][y] = '1';
                            isPlaced = true;}
                        else if(x-3 <= indexM && x-3 >= 0){
                            debugArray[x-3][y] = '1';
                            debugArray[x-2][y] = '1';
                            debugArray[x-1][y] = '1';
                            debugArray[x][y] ='1';
                            debugArray[x+1][y] = '1';
                            isPlaced = true;}
                   }
                }
            else if(position == 0 && (x+1) > indexM){
                if(x-2 <= indexM && x-2 >= 0){
                    if(x-3 <= indexM){
                        if(x-4 <= indexM){
                            debugArray[x-4][y] = '1';
                            debugArray[x-3][y] = '1';
                            debugArray[x-2][y] = '1';
                            debugArray[x-1][y] = '1';
                            debugArray[x][y] ='1';
                            isPlaced = true;
                        }
                }
            }
            else if(position == 1 && (y+1) <= indexN){
                if(y+2 <= indexM){
                    if(y+3 <= indexM){
                        if(y+4 <= indexM){
                            debugArray[x][y] ='1';
                            debugArray[x][y+1] = '1';
                            debugArray[x][y+2] = '1';
                            debugArray[x][y+3] = '1';
                            debugArray[x][y+4] = '1';
                            isPlaced = true;
                        }
                        else if(y-1 <= indexM && y-1 >= 0){
                            debugArray[x][y-1] = '1';
                            debugArray[x][y] ='1';
                            debugArray[x][y+1] = '1';
                            debugArray[x][y+2] = '1';
                            debugArray[x][y+3] = '1';
                            isPlaced = true;}
                    }
                    else if(y-1 <= indexM && y-1 >= 0){
                        if(y-2 <= indexM && y-2 >= 0){
                            debugArray[x][y-2] = '1';
                            debugArray[x][y-1] = '1';
                            debugArray[x][y] ='1';
                            debugArray[x][y+1] = '1';
                            debugArray[x][y+2] = '1';
                            isPlaced = true;}
                    }
                }
                else if(y-1 <= indexM && y-1 >= 0){
                    if(y-2 <= indexM && y-2 >= 0){
                        if(y-3 <= indexM && y-3 >= 0){
                            debugArray[x][y-3] = '1';
                            debugArray[x][y-2] = '1';
                            debugArray[x][y-1] = '1';
                            debugArray[x][y] ='1';
                            debugArray[x][y+1] = '1';
                            isPlaced = true;}

                    }
                }
            }
            else if(position == 1 && (y+1) > indexM){
                if(y-2 <= indexM && y-2 >= 0){
                    if(y-3 <= indexM){
                        if(y-4 <= indexM){
                            debugArray[x][y-4] = '1';
                            debugArray[x][y-3] = '1';
                            debugArray[x][y-2] = '1';
                            debugArray[x][y-1] = '1';
                            debugArray[x][y] ='1';
                            isPlaced = true;}
                    }}
                }
            }}


            isPlaced = false;
            // Create ship of size 4
            x = (int) Math.floor((Math.random() * debugArray.length));
            y = (int) Math.floor((Math.random() * debugArray[0].length));
            // Position: 0 is vertical, 1 is horizontal
            position = (int) Math.floor((Math.random() * 2));

            while(!isPlaced){
                while(debugArray[x][y] != '-'){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));}
                if(position == 0 && (x+1) <= indexM){
                    if(x+2 <= indexM){
                        if(x+3 <= indexM){
                            debugArray[x][y] = '2';
                            debugArray[x+1][y] = '2';
                            debugArray[x+2][y] = '2';
                            debugArray[x+3][y] = '2';
                            isPlaced = true;
                        }
                        else if(x-1 <= indexM && x-1 >= 0){
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] = '2';
                            debugArray[x+1][y] = '2';
                            debugArray[x+2][y] = '2';
                            isPlaced = true;
                        }
                    }
                    else if(x-1 <= indexM && x-1 >= 0){
                        if(x-2 <= indexM && x-2 >= 0){
                            debugArray[x-2][y] = '2';
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] = '2';
                            debugArray[x+1][y] = '2';
                            isPlaced = true;
                        }
                        else if(x-3 <= indexM && x-3 >= 0){
                            debugArray[x-3][y] = '2';
                            debugArray[x-2][y] = '2';
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] = '2';
                            isPlaced = true;
                        }
                    }
                }
                else if(position == 0 && (x+1) > indexM){
                    if(x-2 <= indexM && x-2 >= 0){
                        if(x-3 <= indexM){
                            debugArray[x-3][y] = '2';
                            debugArray[x-2][y] = '2';
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] ='2';
                            isPlaced = true;}
                        else if(x-1 <= indexM && x-1 >= 0){
                            debugArray[x-2][y] = '2';
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] ='2';
                            debugArray[x+1][y] = '2';
                            isPlaced = true;}
                    }
                    else if(x+1 <= indexM){
                        if(x+2 <= indexM){
                            debugArray[x-1][y] = '2';
                            debugArray[x][y] ='2';
                            debugArray[x+1][y] = '2';
                            debugArray[x+2][y] = '2';
                            isPlaced = true;}
                        else if(x+3 <= indexM){
                            debugArray[x][y] = '2';
                            debugArray[x+1][y] ='2';
                            debugArray[x+2][y] = '2';
                            debugArray[x+3][y] = '2';
                            isPlaced = true;}
                    }
                }
                else if(position == 1 && (y+1) <= indexN){
                    if(y+2 <= indexN){
                        if(y+3 <= indexM){
                            debugArray[x][y] ='2';
                            debugArray[x][y+1] = '2';
                            debugArray[x][y+2] = '2';
                            debugArray[x][y+3] = '2';
                            isPlaced = true;}
                        else if(y-1 <= indexM && x-1 >= 0){
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] ='2';
                            debugArray[x][y+1] = '2';
                            debugArray[x][y+2] = '2';
                            isPlaced = true;}
                    }
                    else if(y-1 <= indexN && y-1 >= 0){
                        if(y-2 <= indexM && y-2 >= 0){
                            debugArray[x][y-2] = '2';
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] ='2';
                            debugArray[x][y+1] = '2';
                            isPlaced = true;}
                        else if(y-3 <= indexM && y-3 >= 0){
                            debugArray[x][y-3] = '2';
                            debugArray[x][y-2] = '2';
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] ='2';
                            isPlaced = true;}
                    }
                }
                else if(position == 1 && y+1 >= indexN){
                    if(y-2 <= n && y-2 >= 0 && debugArray[x][y-2] == '-'){
                        if(y-3 <= indexM){
                            debugArray[x][y-3] = '2';
                            debugArray[x][y-2] = '2';
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] = '2';
                            isPlaced = true;}
                        else if(y-1 <= indexM && y-1 >= 0){
                            debugArray[x][y-2] = '2';
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] = '2';
                            debugArray[x][y+1] = '2';
                            isPlaced = true;}
                    }
                    else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                        if(y+2 <= indexM){
                            debugArray[x][y-1] = '2';
                            debugArray[x][y] ='2';
                            debugArray[x][y+1] = '2';
                            debugArray[x][y+2] = '2';
                            isPlaced = true;}
                        else if(y+3 <= indexM){
                            debugArray[x][y] ='2';
                            debugArray[x][y+1] = '2';
                            debugArray[x][y+2] = '2';
                            debugArray[x][y+3] = '2';
                            isPlaced = true;}
                    }
                }}


                isPlaced = false;
                // Create ship of size 3

                x = (int) Math.floor((Math.random() * debugArray.length));
                y = (int) Math.floor((Math.random() * debugArray[0].length));
                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));
                while(!isPlaced){
                    while(debugArray[x][y] != '-'){
                        x = (int) Math.floor((Math.random() * debugArray.length));
                        y = (int) Math.floor((Math.random() * debugArray[0].length));}
                    if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                        if(x+2 <= indexM && debugArray[x+2][y] == '-'){
                            debugArray[x][y] ='3';
                            debugArray[x+1][y] = '3';
                            debugArray[x+2][y] = '3';
                            isPlaced = true;
                        }
                        else if(x-1 <= indexM && x-1 >= 0 && debugArray[x-1][y] == '-'){
                            debugArray[x-1][y] = '3';
                            debugArray[x][y] ='3';
                            debugArray[x+1][y] = '3';
                            isPlaced = true;
                        }
                    }
                    else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){

                        if(x-2 <= indexM && x-2 >= 0 && debugArray[x-2][y] == '-'){
                            debugArray[x-2][y] = '3';
                            debugArray[x-1][y] = '3';
                            debugArray[x][y] ='3';
                            isPlaced = true;
                        }
                        else if(x+1 <= indexM && debugArray[x+1][y] == '-'){
                            debugArray[x-1][y] = '3';
                            debugArray[x][y] ='3';
                            debugArray[x+1][y] = '3';
                            isPlaced = true;
                        }
                    }
                    else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                        if(y+2 <= indexN && debugArray[x][y+2] == '-'){
                            debugArray[x][y] ='3';
                            debugArray[x][y+1] = '3';
                            debugArray[x][y+2] = '3';
                            isPlaced = true;
                        }
                        else if(y-1 <= indexN && y-1 <= 0 && debugArray[x][y-1] == '-'){
                            debugArray[x][y-1] = '3';
                            debugArray[x][y] ='3';
                            debugArray[x][y+1] = '3';
                            isPlaced = true;
                        }
                    }
                    else if(position == 1 && (y+1) > indexN){

                        if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                            debugArray[x][y-2] = '3';
                            debugArray[x][y-1] = '3';
                            debugArray[x][y] ='3';
                            isPlaced = true;
                        }
                        else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                            debugArray[x][y-1] = '3';
                            debugArray[x][y] ='3';
                            debugArray[x][y+1] = '3';
                            isPlaced = true;
                        }
                    }}
                isPlaced = false;

                // Create second ship of size 3
                while(!isPlaced){
                    x = (int) Math.floor((Math.random() * debugArray.length));
                    y = (int) Math.floor((Math.random() * debugArray[0].length));
                    while(debugArray[x][y] != '-'){
                        x = (int) Math.floor((Math.random() * debugArray.length));
                        y = (int) Math.floor((Math.random() * debugArray[0].length));}
                    // Position: 0 is vertical, 1 is horizontal
                    position = (int) Math.floor((Math.random() * 2));
                    if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                        if(x+2 <= indexM && debugArray[x+2][y] == '-'){
                            debugArray[x][y] ='4';
                            debugArray[x+1][y] = '4';
                            debugArray[x+2][y] = '4';
                            isPlaced = true;
                        }
                        else if(x-1 <= indexM && x-1 >= 0 && debugArray[x-1][y] == '-'){
                            debugArray[x-1][y] = '4';
                            debugArray[x][y] ='4';
                            debugArray[x+1][y] = '4';
                            isPlaced = true;
                        }
                    }
                    else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){

                        if(x-2 <= indexM && x-2 >= 0 && debugArray[x-2][y] == '-'){
                            debugArray[x-2][y] = '4';
                            debugArray[x-1][y] = '4';
                            debugArray[x][y] ='4';
                            isPlaced = true;
                        }
                        else if(x+1 <= indexM && debugArray[x+1][y] == '-'){
                            debugArray[x-1][y] = '4';
                            debugArray[x][y] ='4';
                            debugArray[x+1][y] = '4';
                            isPlaced = true;
                        }
                    }
                    else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                        if(y+2 <= indexN && debugArray[x][y+2] == '-'){
                            debugArray[x][y] ='4';
                            debugArray[x][y+1] = '4';
                            debugArray[x][y+2] = '4';
                            isPlaced = true;
                        }
                        else if(y-1 <= indexN && y-1 <= 0 && debugArray[x][y-1] == '-'){
                            debugArray[x][y-1] = '4';
                            debugArray[x][y] ='4';
                            debugArray[x][y+1] = '4';
                            isPlaced = true;
                        }
                    }
                    else if(position == 1 && (y+1) > indexN){

                        if(y-2 <= n && y-2 <= 0 && debugArray[x][y-2] == '-'){
                            debugArray[x][y-2] = '4';
                            debugArray[x][y-1] = '4';
                            debugArray[x][y] ='4';
                            isPlaced = true;
                        }
                        else if(y+1 <= n && y+1 <= 0 && debugArray[x][y+1] == '-'){
                            debugArray[x][y-1] = '4';
                            debugArray[x][y] ='4';
                            debugArray[x][y+1] = '4';
                            isPlaced = true;
                        }
                    }}
                isPlaced = false;
                // Create ship of size 2
                x = (int) Math.floor((Math.random() * debugArray.length));
                y = (int) Math.floor((Math.random() * debugArray[0].length));
                // Position: 0 is vertical, 1 is horizontal
                position = (int) Math.floor((Math.random() * 2));
                while(!isPlaced){
                    while(debugArray[x][y] != '-'){
                        x = (int) Math.floor((Math.random() * debugArray.length));
                        y = (int) Math.floor((Math.random() * debugArray[0].length));}

                    if(position == 0 && (x+1) <= indexM && debugArray[x+1][y] == '-'){
                        debugArray[x][y] = '5';
                        debugArray[x+1][y] = '5';
                        isPlaced = true;
                    }
                    else if(position == 0 && (x+1) > indexM && debugArray[x-1][y] == '-'){
                        debugArray[x-1][y] = '5';
                        debugArray[x][y] = '5';
                        isPlaced = true;
                    }
                    else if(position == 1 && (y+1) <= indexN && debugArray[x][y+1] == '-'){
                        debugArray[x][y] = '5';
                        debugArray[x][y+1] = '5';
                        isPlaced = true;
                    }
                    else if(position == 1 && (y+1) > indexN && debugArray[x][y-1] == '-'){
                        debugArray[x][y-1] = '5';
                        debugArray[x][y] = '5';
                        isPlaced = true;
                    }}}
        return debugArray;
    }



    // Checking for valid numerical input inspired from
    // http://javaconceptoftheday.com/java-program-to-check-user-input-is-number-or-not/
    public static boolean validInt(String input){
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }


    //Print/display debugArray or boardArray
    public void display(char[][] array){
        for (int row = 0; row < array.length; row ++){
            System.out.println();
            for (int col = 0; col < array[0].length; col++){
                System.out.print(" " + array[row][col]);}}
    }


    //Run this method to play
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        // Board size from user input
        //init row/height size
        int x = 2;
        //init column/width size
        int y = 2;

        //BattleshipBoard battle = new BattleshipBoard();
        System.out.println("Welcome to Battleship!" + "\n" + "Enter desired board dimensions between 2 by 2 and 10 by 10 (Example: 4 by 5 entered as '4 5'): ");
        Scanner inputString = new Scanner(input.nextLine());
        while(inputString.hasNext()){
            String current = inputString.next();
            if(validInt(current) && Integer.parseInt(current) >=2 && Integer.parseInt(current) <= 10){
                x = Integer.parseInt(current);
                current = inputString.next();
                if(validInt(current) && Integer.parseInt(current) >=2 && Integer.parseInt(current) <= 10){
                    y = Integer.parseInt(current);

                }
                else{
                    System.out.println(current + " isn't valid. Try different coordinates.");
                    inputString = new Scanner(input.nextLine());
                }
            }
            else{
                System.out.println(current + " isn't valid. Try different coordinates.");
                inputString = new Scanner(input.nextLine());
            }
        }

        BattleshipBoard battle = new BattleshipBoard(x, y);
        battle.debugBoard();
        System.out.println("Board of " + x + " by " + y + " created.");

        //Tell user how many ships will be on the board
        if(y == 2 || x == 2){
            System.out.println("There will be one ship of size 2 to find. One ship total.");
        }
        else if((2 < y && y <= 4 ) || (2 < x && x <= 4)){
            System.out.println("There will be one ship of size 2 and one ship of size 3 to find. Two ships total");
        }
        else if((4 < y && y <= 6) || (4 < x && x <= 6)){
            System.out.println("There will be one ship of size 2 and two ships of size 3 to find. Three ships total.");
        }
        else if((6 < y && y <= 8) || (6 < x && x <= 8)){
            System.out.println("There will be one ship of size 2, two ships of size 3, and one ship of size 4 to find. Four ships total.");
        }
        else if((8 < y && y <= 10) || (8 < x && x <= 10)){
            System.out.println("There will be one ship of size 2, two ships of size 3, one ship of size 4, and one ship of size 5 to find. Five ships total.");
        }
        System.out.println("Let's play the game!");
        System.out.println();
        System.out.println("Once the game gets started and you start trying to sink ships," +"\n"+"note that 'M' means a missed spot and 'X' means a hit.");
        System.out.println("Do you want to play with debug mode on? 1 for yes, 2 for no.");
        inputString = new Scanner(input.nextLine());
        int playDebug = inputString.nextInt();
        if(playDebug == 1){
            System.out.println("Debug mode is on. On the debug board, numerical characters denote ships." + "\n" + "1 will be the longest ship, 2 the second longest, and so on.");
        }

        while(!battle.finish){
            System.out.println();
            System.out.println("********************************************");
            System.out.println("Turn " + battle.getTurns());
            if(playDebug == 1) {
                System.out.println();
                System.out.print("~~~Debug board:~~~");
                battle.display(battle.debugArray);
                System.out.println();
                System.out.println("~~~~~~~~~~~~~~~~~~~~");
            }
            battle.display(battle.boardArray);
            System.out.println();
            System.out.println("The top left corner is 0 0. Enter coordinates between" + " 0 0 " + "and " + (x-1) + " " + (y-1) + ".");
            inputString = new Scanner(input.nextLine());
            int coord1 = inputString.nextInt();
            int coord2 = inputString.nextInt();
            battle.moves(coord1, coord2);
        }
        //Testing without playing:
        //battle.display(battle.boardArray);
        //System.out.println();
        //battle.display(battle.debugBoard());
        battle.display(battle.boardArray);
        System.out.println();
        System.out.println("Congratulations, you won! It took " + (battle.getTurns() - 1) + " tries to win this game.");



    }

}
