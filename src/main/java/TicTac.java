import javafx.util.Pair;
import java.util.HashMap;

public class TicTac {
    private int[][] game;

    private int origMoveX;
    private int origMoveY;
    private int origMoveVal;

    private HashMap<Pair, Boolean> memo = new HashMap<Pair, Boolean>();

    public TicTac(int[][] game, int i, int j) {
        this.game = game;
        this.origMoveX = i;
        this.origMoveY = j;

        this.origMoveVal = this.game[i][j];

    }

    public String checkState(){
        if (this.origMoveVal == 0 || checkWithinRange(this.origMoveVal) == false){
            return "Yet to be decided";
        }

        int out = crDFS(this.origMoveX, this.origMoveY, 0, true);

        if(out == 3 || out == -3){
            return printResult(out);
        }

        memo = new HashMap<Pair, Boolean>();

        out = crDFS(this.origMoveX, this.origMoveY, 0, false);

        if(out == 3 || out == -3){
            return printResult(out);
        }

        out = diagonalDFS(this.origMoveX, this.origMoveY, 0);

        return printResult(out);
    }

    public int crDFS(int i, int j, int out, Boolean isHoriz){
        if (i < 0 || i > this.game.length-1 || j < 0 || j > this.game[i].length-1 || this.memo.containsKey(new Pair<Integer, Integer>(i, j))){
            return out;
        }

        if (this.game[i][j] == this.origMoveVal && checkWithinRange(this.game[i][j])){

            out += this.game[i][j];

            this.memo.put(new Pair<Integer, Integer>(i, j), true);

            if(isHoriz){
                out = crDFS(i, j+1, out, isHoriz);
                out = crDFS(i, j-1, out, isHoriz);
            } else{
                out = crDFS(i+1, j, out, isHoriz);
                out = crDFS(i-1, j, out, isHoriz);
            }

        }

        return out;
    }


    public int diagonalDFS(int i, int j, int out){

        if (this.game[i][j] == this.origMoveVal && checkWithinRange(this.game[i][j])){
            if (i == j){
                out += (checkWithinRange(this.game[0][0])) ? this.game[0][0] : 0;
                out += (checkWithinRange(this.game[1][1])) ? this.game[1][1] : 0;
                out += (checkWithinRange(this.game[2][2])) ? this.game[2][2] : 0;
            } else if(j > i || i > j){
                out += (checkWithinRange(this.game[0][2])) ? this.game[0][2] : 0;
                out += (checkWithinRange(this.game[1][1])) ? this.game[1][1] : 0;
                out += (checkWithinRange(this.game[2][0])) ? this.game[2][0] : 0;
            }
        }

        return out;
    }

    public Boolean checkWithinRange(int val){
        if(val >= -1 && val <= 1){
            return true;
        }

        return false;
    }

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
