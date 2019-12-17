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

    public static void main(String[] args) {
        int[][] game = {
                {-1, -1, -1},
                {1, 1, -1},
                {1, 1, 1}
        };

        TicTac currentGame = new TicTac(game, 0, 1);

        System.out.println(currentGame.checkState());
    }

    public String checkState(){
        if (this.game[this.origMoveX][this.origMoveY] == 0){
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

        if (this.game[i][j] == this.origMoveVal){
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
        if (this.game[i][j] == this.origMoveVal){
            if (i == j){
                out += this.game[0][0];
                out += this.game[1][1];
                out += this.game[2][2];
            } else if(j > i || i > j){
                out += this.game[0][2];
                out += this.game[1][1];
                out += this.game[2][0];
            }
        }

        return out;
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
