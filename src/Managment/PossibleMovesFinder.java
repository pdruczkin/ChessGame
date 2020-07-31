package Managment;

import Board.Board;

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
                        && !mateFinder.fakeMove(board,j,i,x,y,board.getSquareBoard()[y][x].getFigure().isWhite());
            }
        }
    }

    public void setPossibleMove(Board board, byte x, byte y){
        clearPossibleMoves();
        if(board.getSquareBoard()[y][x].isOccupied()){
            generetePossibleMoves(board, x,y);
        }
    }

    public void printPossibleMoves(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(possibleMoves[i][j]) System.out.print(1);
                else System.out.print("0");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void clearPossibleMoves(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = false;
            }
        }
    }

    public void FindAnyPossibleMoves(Board board){
        areAnyPossibleMoves = false;
        for (byte i = 0; i < 8; i++) {
            for(byte j = 0; j < 8;j++){
                setPossibleMove(board,j,i);
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

}
