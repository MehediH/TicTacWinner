import javafx.util.Pair;
import java.util.HashMap;

public class TicTac {
    private int[][] game; // array to store the game's state
    private int origMoveX; // the row position of last made move
    private int origMoveY; // the column position of the last made mode
    private int origMoveVal; // the value of the last made move
    private HashMap<Pair, Boolean> memo = new HashMap<Pair, Boolean>(); // hashmap to keep track of the values we have checked

    // Initialies the game, and the last made move values.
    public TicTac(int[][] game, int i, int j) {
        this.game = game;
        this.origMoveX = i;
        this.origMoveY = j;

        this.origMoveVal = this.game[i][j];
    }

    // Only for testing purposes.
    public static void main(String[] args){
        //  1 represents X
        // -1 represents O
        //  0 represents empty space (move yet to be made)

        int[][] game = {
                {1, 1, -1},
                {-1, 1, 0},
                {0, 1, -1}
        };

        TicTac testGame = new TicTac(game, 0, 1);

        System.out.println(testGame.verifyGame());

    }

    // Main method for verifying the state of a game.
    public String verifyGame(){
        // if the last made move is 0 (move is yet to be made), or the last moade mode isn't within the valid range, return early
        if (this.origMoveVal == 0 || checkWithinRange(this.origMoveX, this.origMoveY) == false){
            return "Yet to be decided";
        }

        // check the corresponding column values to see if we have a winner
        int out = crDFS(this.origMoveX, this.origMoveY, 0, true);

        // if the column output adds up to 3 or -3, it means either X or O has won, so we print the result
        if(out == 3 || out == -3){
            return printResult(out);
        }

        // else, we keep going to look on the row values
        this.memo = new HashMap<Pair, Boolean>(); // we reset the values we have checked

        out = crDFS(this.origMoveX, this.origMoveY, 0, false);

        // once again, if we have a winner, print the results
        if(out == 3 || out == -3){
            return printResult(out);
        }

        // else, look diagonally
        out = diagonalDFS(this.origMoveX, this.origMoveY, 0);

        // finally, print the results
        return printResult(out);
    }

    // Checks the column/row values recursively for a potential winner.
    public int crDFS(int i, int j, int out, Boolean isHoriz){
        // if the row/column (i/j) are out of bound or if we have already checked a coordinate, return early
        if (i < 0 || i > this.game.length-1 || j < 0 || j > this.game[i].length-1 || this.memo.containsKey(new Pair<Integer, Integer>(i, j))){
            return out;
        }

        // else, if the current value matches the value of the last made move and it's within range, we carry out the recursion
        if (checkWithinRange(i, j)){

            out += this.game[i][j]; // add the value of the current position to the output

            this.memo.put(new Pair<Integer, Integer>(i, j), true); // mark it as checked

            // if we are checking on the columns
            if(isHoriz){
                out = crDFS(i, j+1, out, isHoriz);
                out = crDFS(i, j-1, out, isHoriz);
            } else{ // if we are checking the rows
                out = crDFS(i+1, j, out, isHoriz);
                out = crDFS(i-1, j, out, isHoriz);
            }

        }

        return out;
    }

    // checks diagonally for a potential winner
    public int diagonalDFS(int i, int j, int out){
        // checks the current value matches original value and i's within range
        if (checkWithinRange(i, j)){
            if (i == j){ // when the row number == column number
                out += (checkWithinRange(0, 0)) ? this.game[0][0] : 0;
                out += (checkWithinRange(1, 1)) ? this.game[1][1] : 0;
                out += (checkWithinRange(2, 2)) ? this.game[2][2] : 0;
            } else if(j > i || i > j){ // everywhere else, we check the same pairs if j>i or i<j
                out += (checkWithinRange(0, 2)) ? this.game[0][2] : 0;
                out += (checkWithinRange(1 ,1)) ? this.game[1][1] : 0;
                out += (checkWithinRange(2, 0)) ? this.game[2][0] : 0;
            }
        }

        return out;
    }

    // checks if the value for a given position is between -1 and 1.
    public Boolean checkWithinRange(int i, int j){
        if(this.game[i][j]  >= -1 && this.game[i][j]  <= 1 && this.game[i][j] == this.origMoveVal){
            return true;
        }

        return false;
    }

    // prints the results of the game (3 == X wins), (-3 == O wins), (else: yet to be decided)
    public String printResult(int out){
        if (out == 3){
            return "X has won";
        } else if (out == -3){
            return "O has won";
        } else{
            return "Yet to be decided";
        }
    }
}
