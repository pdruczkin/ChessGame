import Board.Board;
import Figures.*;

public class Manager {
    private Board board = new Board();
    private boolean [][] possibleMoves = new boolean[8][8];



    private void setFigures(){
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[1][i].setFigure(new Pawn(false,i,1));
            board.getSquareBoard()[1][i].setOccupied(true);
        }
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[6][i].setFigure(new Pawn(true,i,6));
            board.getSquareBoard()[6][i].setOccupied(true);
        }
    }
    private void clearPossibleMoves(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = false;
            }
        }
    }
    private void generetePossibleMoves(int x, int y){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = board.getSquareBoard()[y][x].getFigure().isGoodToGo(board,j,i);
            }
        }
    }

    private void setPossibleMoves(int x, int y){
        clearPossibleMoves();
        if(board.getSquareBoard()[y][x].isOccupied()){
            generetePossibleMoves(x,y);
        }
    }

    private void printPossibleMoves(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(possibleMoves[i][j]) System.out.print(1);
                else System.out.print("0");

            }
            System.out.println();
        }
        System.out.println();
    }

    public void run(){
        setFigures();
        setPossibleMoves(3,1);

        printPossibleMoves();


        board.print();
    }




}
