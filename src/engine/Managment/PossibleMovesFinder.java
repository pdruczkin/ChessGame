package engine.Managment;

import engine.Board.Board;
import engine.Board.Square;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PossibleMovesFinder {
    private boolean [][] possibleMoves = new boolean[8][8];
    private MateFinder mateFinder = new MateFinder();
    private boolean areAnyPossibleMoves;

    public boolean getAreAnyPossibleMoves() {
        return areAnyPossibleMoves;
    }

    private void generetePossibleMoves(Board board, byte x, byte y){
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                possibleMoves[i][j] = board.getSquareBoard()[y][x].getFigure().isGoodToGo(board,j,i)
                        && mateFinder.fakeMove(board,j,i,x,y,board.getSquareBoard()[y][x].getFigure().isWhite());
            }
        }
    }

    public void setPossibleMove(Board board, byte x, byte y, boolean isWhite){
        clearPossibleMoves();
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == isWhite){
            generetePossibleMoves(board, x,y);
        }
    }

    public void setColorPossibleMoves(Board board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(possibleMoves[i][j]){
                    Rectangle shape = board.getSquareBoard()[i][j].getSquare();
                    if((i+j)%2 == 0) {
                        shape.setFill(Color.BLUE);
                        board.getSquareBoard()[i][j].setSquare(shape);
                    }
                    else{
                        shape.setFill(Color.CYAN);
                        board.getSquareBoard()[i][j].setSquare(shape);
                    }
                }
            }
        }
    }

    public void undoColorPossibleMoves(Board board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle shape = board.getSquareBoard()[i][j].getSquare();
                if((i+j)%2 == 0){
                    shape.setFill(Color.STEELBLUE);
                    board.getSquareBoard()[i][j].setSquare(shape);
                }
                else{
                    shape.setFill(Color.PALETURQUOISE);
                    board.getSquareBoard()[i][j].setSquare(shape);
                }
            }
        }

    }

    private void clearPossibleMoves(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = false;
            }
        }
    }

    public void findAnyPossibleMoves(Board board, boolean isWhite){
        areAnyPossibleMoves = false;
        for (byte i = 0; i < 8; i++) {
            for(byte j = 0; j < 8;j++){
                setPossibleMove(board,j,i,isWhite);
                if(checkPossibleMoves()) areAnyPossibleMoves = true;
            }
        }
    }

    public boolean checkMove(int x, int y){
        return possibleMoves[y][x];
    }
    public boolean checkPossibleMoves(){
        for (byte i = 0; i < 8 ; i++) {
            for (byte j = 0; j < 8; j++) {
                if(checkMove(j,i)) return true;
            }
        }
        return false;
    }

    public void clearJustMovedTwo(Board board){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquareBoard()[i][j].isOccupied()){
                    board.getSquareBoard()[i][j].getFigure().setJustMovedTwo(false);
                }
            }
        }
    }


}
