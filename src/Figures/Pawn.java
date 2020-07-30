package Figures;

import Board.Board;

public class Pawn extends Figure{
    public Pawn(boolean isWhite, int x, int y) {
        super(isWhite, x, y, (byte) 1);
    }


    @Override
    public boolean isGoodToGo(Board board, int x, int y) {

        //move forward
        if(x == this.x && !board.getSquareBoard()[y][x].isOccupied()){

            if(this.isWhite && y == this.y - 1 && y >= 0) { return true; }
            else if(!this.isWhite && y == this.y + 1 && y < 8){ return true; }

            if(this.isWhite && y == this.y - 2 && !isMoved()) { return true; }
            else if(!this.isWhite && y == this.y + 2 && !isMoved()) { return true; }
        }

        //attack
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() != this.isWhite){
            if((this.x == x + 1 || this.x == x - 1 )&& x >= 0 && x < 8){

                if(this.isWhite && y == this.y - 1 && y >= 0){
                    return true;
                }
                else if(!this.isWhite && y == this.y + 1 && y < 8){
                    return true;
                }
            }
        }
        return false;
    }
}