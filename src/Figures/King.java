package Figures;


import Board.Board;

public class King extends Figure{
    public King(boolean isWhite, int x, int y) {
        super(isWhite, x, y, (byte) 6);
    }

    @Override
    public boolean isGoodToGo(Board board, int x, int y) {
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
}
