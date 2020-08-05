package engine.Board;

public class Board {

    private Square [][] squareBoard = new Square[8][8];

    public Square[][] getSquareBoard() {
        return squareBoard;
    }

    public void setSquareBoard(Square[][] squareBoard) {
        this.squareBoard = squareBoard;
    }

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                squareBoard[i][j] = new Square(j,i);

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
