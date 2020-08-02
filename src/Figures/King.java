package Figures;


import Board.Board;

public class King extends Figure{
    public King(boolean isWhite, byte x, byte y) {
        super(isWhite, x, y, (byte) 6);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y, boolean isKingInCheck) {
        if(!isKingInCheck && isCastlingAvailable(board, x, y)) return true;
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == this.isWhite){
            return false;
        }
        else{
            if(Math.abs(this.x - x) <= 1 && Math.abs(this.y - y) <= 1){
                return true;
            }
        }
        return false;
    }

    private boolean isCastlingAvailable(Board board, byte x, byte y){
        if(isMoved) return false;
        else{
            if(this.isWhite){
                if(y == 7 && x == 1){
                    if(!board.getSquareBoard()[7][0].getFigure().isMoved()){
                        return !board.getSquareBoard()[7][1].isOccupied() && !board.getSquareBoard()[7][2].isOccupied() && !board.getSquareBoard()[7][3].isOccupied();
                    }
                }
                else if(y == 7 && x == 6){
                    if(!board.getSquareBoard()[7][7].getFigure().isMoved()){
                        return !board.getSquareBoard()[7][6].isOccupied() && !board.getSquareBoard()[7][5].isOccupied();
                    }
                }
            }
            else{
                if(y == 0 && x == 1){
                    if(!board.getSquareBoard()[0][0].getFigure().isMoved()){
                        return !board.getSquareBoard()[0][1].isOccupied() && !board.getSquareBoard()[0][2].isOccupied() && !board.getSquareBoard()[0][3].isOccupied();
                    }
                }
                else if(y == 0 && x == 6){
                    if(!board.getSquareBoard()[0][7].getFigure().isMoved()){
                        return !board.getSquareBoard()[0][6].isOccupied() && !board.getSquareBoard()[0][5].isOccupied();
                    }
                }
            }
        }
        return false;
    }
}
