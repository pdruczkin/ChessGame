package engine.Board;

import javafx.scene.Group;

public class Board {

    private Square [][] squareBoard = new Square[8][8];

    public Square[][] getSquareBoard() {
        return squareBoard;
    }


    public Board(Group root, int cell) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squareBoard[i][j] = new Square(j,i,root,cell);
            }
        }
    }

    public void print(boolean b){ // boosted printing -> b = true
        if(b) System.out.println("    0   1   2   3   4   5   6   7  ");
        for (int i = 0; i < 8; i++) {
            if(b) System.out.println("    -   -   -   -   -   -   -   -  ");
            for (int j = 0; j < 8; j++) {
                if(b && j == 0) System.out.print(i);

                if(b) System.out.print(" | ");
                if(squareBoard[i][j].isOccupied()){
                    System.out.print(squareBoard[i][j].getFigure().getType());
                }
                else{
                    System.out.print(" ");
                }
            }
            if(b) System.out.print(" |");
            System.out.println();
        }
        if(b) System.out.println("    -   -   -   -   -   -   -   -  ");
    }
}
