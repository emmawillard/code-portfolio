/**
 * Emma Willard (willa115, 5040938)
 */
public class Test {
    public char[][] makeBoard(){
        sampleBoard = new char[5][5];
        for (int row = 0; row < sampleBoard.length; row ++){
            for (int col = 0; col < sampleBoard[0].length; col++){
                sampleBoard[row][col] = '-';}}
        return sampleBoard;}
    public void display(char[][] array){
        for (int row = 0; row < array.length; row ++){
            System.out.println();
            for (int col = 0; col < array[0].length; col++){
                System.out.print(" " + array[row][col]);}}
    }
    public char[][] sampleBoard;
    public static void main(String[] args){

        //Testing the randomness of initial coords (x and y) and the position (0 -- vertical, 1 -- horizontal)
        char[][] debugArray = {{1,2,3},{4,5,6},{7,8,9}};

        int x = (int) Math.floor((Math.random() * debugArray.length));
        int y = (int) Math.floor((Math.random() * debugArray[0].length));

        // 0 is vertical, 1 is horizontal
        int position = (int) Math.floor((Math.random() * 2));

        System.out.println("x coord: " + x);
        System.out.println("y coord: " + y);
        System.out.println("Vertical vs horizontal: " + position);

        //Test display()
        Test test1 = new Test();
        test1.makeBoard();
        test1.display(test1.sampleBoard);

    }
}
