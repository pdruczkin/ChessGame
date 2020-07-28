package Board;

public class Board {

    private Square [][] squareBoard = new Square[8][8];

    public Square[][] getSquareBoard() {
        return squareBoard;
    }

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                squareBoard[i][j] = new Square(j,i);

            }
        }
    }

    public void print(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(squareBoard[i][j].isOccupied()){
                    System.out.print(squareBoard[i][j].getFigure().getType());
                }
                else{
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }


}
