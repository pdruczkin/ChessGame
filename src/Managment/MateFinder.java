package Managment;

import Board.Board;

public class MateFinder {

    private byte kingXCords, kingYCords;

    public byte getKingXCords() {
        return kingXCords;
    }

    public byte getKingYCords() {
        return kingYCords;
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

    private boolean isAttackingKing(Board board, int x, int y, boolean isKingWhite) {
        if (board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() != isKingWhite) { //diffrent colour than occupied King
            return board.getSquareBoard()[y][x].getFigure().isGoodToGo(board, kingXCords, kingYCords);
        }
        return false;
    }

    public boolean isMate(Board board, boolean isKingWhite) {
        findKing(board, isKingWhite);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isAttackingKing(board, j, i, isKingWhite)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Board board, boolean isKingWhite) {
        findKing(board, isKingWhite);
        //Przydal by sie ruch i sprawdzanie czy moze sie ruszyc w innej klasie niz Manager
        return true;
    }
}


