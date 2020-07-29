import Board.Board;
import Figures.*;

public class Manager {
    private Board board = new Board();
    private boolean [][] possibleMoves = new boolean[8][8];

    private void setFigures(){
        //setting Pawns(1)
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[1][i].setFigure(new Pawn(false,i,1));
            board.getSquareBoard()[1][i].setOccupied(true);
        }
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[6][i].setFigure(new Pawn(true,i,6));
            board.getSquareBoard()[6][i].setOccupied(true);
        }

        //setting Rooks(2)
        for(int i = 0; i < 8; i = i+7){
            board.getSquareBoard()[0][i].setFigure(new Rook(false,i,0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(int i = 0; i < 8; i = i+7){
            board.getSquareBoard()[7][i].setFigure(new Rook(true,i,7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }
        //setting Bishops(4)
        for(int i = 2; i < 8; i = i+3){
            board.getSquareBoard()[0][i].setFigure(new Bishop(false,i,0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(int i = 2; i < 8; i = i+3){
            board.getSquareBoard()[7][i].setFigure(new Bishop(true,i,7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }


        //setting Kings(6)
        board.getSquareBoard()[0][4].setFigure(new King(false,4,0));
        board.getSquareBoard()[0][4].setOccupied(true);
        board.getSquareBoard()[7][4].setFigure(new King(true,4,7));
        board.getSquareBoard()[7][4].setOccupied(true);

        board.getSquareBoard()[4][4].setFigure(new Bishop(true,4,4));
        board.getSquareBoard()[4][4].setOccupied(true);



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

    private void moveFigure( int newX, int newY, int oldX, int oldY) {
        if (possibleMoves[newY][newX]){
            board.getSquareBoard()[oldY][oldX].getFigure().setX(newX);
            board.getSquareBoard()[oldY][oldX].getFigure().setY(newY);
            board.getSquareBoard()[oldY][oldX].setOccupied(false);
            board.getSquareBoard()[newY][newX].setOccupied(true);
            board.getSquareBoard()[newY][newX].setFigure(board.getSquareBoard()[oldY][oldX].getFigure());
            board.getSquareBoard()[oldY][oldX].setFigure(null);
            System.out.println();
        }
    }

    public void run(){
        setFigures();
        setPossibleMoves(4,4);

        printPossibleMoves();

        //moveFigure(3,2,3,1);


        board.print();
    }




}
