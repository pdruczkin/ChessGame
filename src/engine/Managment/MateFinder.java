package engine.Managment;

import engine.Board.Board;
import engine.Figures.King;

public class MateFinder {

    private final Move move = new Move();

    private byte kingXCords, kingYCords;

    public void setKingXCords(byte kingXCords) {
        this.kingXCords = kingXCords;
    }

    public void setKingYCords(byte kingYCords) {
        this.kingYCords = kingYCords;
    }

    private void findKing(Board board, boolean isWhite) {
        //It makes possible to break multiple loops
        bigBreak:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSquareBoard()[i][j].isOccupied()) {
                    if (board.getSquareBoard()[i][j].getFigure().getType() == 6 && board.getSquareBoard()[i][j].getFigure().isWhite() == isWhite) {
                        kingXCords = (byte) j;
                        kingYCords = (byte) i;
                        break bigBreak;
                    }
                }
            }
        }
    }

    private boolean isAttackingKing(Board board, byte x, byte y, boolean isKingWhite) {
        if (board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() != isKingWhite) { //diffrent colour than occupied King
            if(board.getSquareBoard()[y][x].getFigure().getType() != 6){
                return board.getSquareBoard()[y][x].getFigure().isGoodToGo(board, kingXCords, kingYCords);
            }
            else{
                King tmpKing = (King) board.getSquareBoard()[y][x].getFigure();

                return tmpKing.isGoodToGoNoCastlingCheck(board, kingXCords, kingYCords);
            }
        }
        return false;
    }

    public boolean isMate(Board board, boolean isKingWhite, boolean findingKingNeeded) {
        if(findingKingNeeded) findKing(board, isKingWhite);
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                if (isAttackingKing(board, j, i, isKingWhite)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fakeMove(Board board, int newX, int newY, int oldX, int oldY, boolean isKingWhite){
        move.moveFigure(board, newX, newY, oldX, oldY);
        if(isMate(board, isKingWhite, true)){
            move.removeFigure(board, newX, newY, oldX, oldY);
            return false;
        }
        else{
            move.removeFigure(board, newX, newY, oldX, oldY);
            return true;
        }
    }

    public boolean isAbleToCoverKing(Board board, boolean isKingWhite, byte x, byte y){
        if (board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == isKingWhite){ // same colour as king

            for (byte i = 0; i < 8; i++) {
                for (byte j = 0; j < 8; j++) {
                    if(board.getSquareBoard()[y][x].getFigure().isGoodToGo(board,j,i))
                    {
                        if(fakeMove(board, j, i, x, y, isKingWhite)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isKingProtectable(Board board, boolean isKingWhite){
        for (byte i = 0; i <8; i++) {
            for (byte j = 0; j < 8; j++) {
                if(isAbleToCoverKing(board, isKingWhite, j, i)) return true;
            }
        }
        return false;
    }

    private boolean isThereOnlyKing(Board board, boolean isKingWhite){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquareBoard()[i][j].isOccupied() && board.getSquareBoard()[i][j].getFigure().isWhite() == isKingWhite
                        && board.getSquareBoard()[i][j].getFigure().getType() != 6) return false;
            }
        }
        return true;
    }

    public boolean isCheckMate(Board board, boolean isKingWhite) {
        return isMate(board, isKingWhite, true) && !isKingProtectable(board, isKingWhite);
    }
    public boolean isStaleMate(Board board, boolean isKingWhite, boolean areAnyMoves) {
        if(isThereOnlyKing(board, isKingWhite) && isThereOnlyKing(board, !isKingWhite)) return true;
        return !isMate(board, isKingWhite, true) && !areAnyMoves;
    }
}