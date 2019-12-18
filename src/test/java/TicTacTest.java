import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacTest {
    TicTac currentGame;

//     1 represents X
//     -1 represents O
//     0 represents empty space (move yet to be made)

    @Test
    public void testCheckVert() throws Exception {
        int[][] game = {
                {1, 1, -1},
                {-1, 1, 0},
                {0, 1, -1}
        };

        this.currentGame = new TicTac(game, 0, 1);

        System.out.println("Game 1: X has won");

        assertEquals("X has won", currentGame.checkState()); // X has won (middle column, vertically)
    }

    @Test
    public void testCheckHoriz() throws Exception{
        int[][] game = {
                {1, 0, 1},
                {1, -1, 1},
                {-1, -1, -1}
        };

        System.out.println("Game 2: O has won");

        this.currentGame = new TicTac(game, 2, 0);
        assertEquals("O has won", currentGame.checkState()); // O has won (bottom row, horizontally)
    }

    @Test
    public void testCheckDiag() throws Exception{
        int[][] game = {
                {-1, 1, -1},
                {1, -1, 1},
                {-1, 1, -1}
        };

        System.out.println("Game 3: O has won");

        this.currentGame = new TicTac(game, 2, 2);
        assertEquals("O has won", currentGame.checkState()); // O has won (diagonally)
    }

    @Test
    public void testCheckAntiDiag() throws Exception{
        int[][] game = {
                {-1, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };

        System.out.println("Game 4: X has won");

        this.currentGame = new TicTac(game, 0, 2);
        assertEquals("X has won", currentGame.checkState()); // X has won (anti-diagonally)
    }

    @Test
    public void testCheckPendingOrDraw() throws Exception{
        int[][] game = {
                {1, 1, -1},
                {0, 1, 1},
                {1, -1, -1}
        };

        System.out.println("Game 5: Yet to be decided");

        this.currentGame = new TicTac(game, 1, 1);
        assertEquals("Yet to be decided", currentGame.checkState()); // No one wins
    }

    @Test
    public void testCheckNoEntries() throws Exception{
        int[][] game = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        System.out.println("Game 6: Yet to be decided");

        this.currentGame = new TicTac(game, 1, 0);
        assertEquals("Yet to be decided", currentGame.checkState()); // Game yet to be played
    }

    @Test
    public void testCheckInvalidValues() throws Exception{
        int[][] game = {
                {5, 23, 12},
                {3, -2, 21},
                {2, -3, 20}
        };

        System.out.println("Game 7: Yet to be decided");

        this.currentGame = new TicTac(game, 2, 1);
        assertEquals("Yet to be decided", currentGame.checkState()); // Invalid value, so yet to be decided
    }

    @Test
    public void testCheckFixSum() throws Exception{
        int[][] game = {
                {-1, 1, 3},
                {1, -1, 1},
                {1, 1, -1}
        };

        System.out.println("Game 8: Yet to be decided");

        this.currentGame = new TicTac(game, 0, 2);
        assertEquals("Yet to be decided", currentGame.checkState()); // Invalid value, so yet to be decided
    }

}