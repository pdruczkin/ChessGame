package engine.Board;

import javafx.scene.Group;

public class Board {

    private final Square [][] squareBoard = new Square[8][8];

    public final Square[][] getSquareBoard() {
        return squareBoard;
    }


    public Board(Group root, int cell) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squareBoard[i][j] = new Square(j,i,root,cell);
            }
        }
    }
}
