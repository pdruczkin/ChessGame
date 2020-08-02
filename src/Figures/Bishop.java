package Figures;

import Board.Board;

public class Bishop extends Figure{
    public Bishop(boolean isWhite, byte x, byte y) {
        super(isWhite, x, y, (byte) 4);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y, boolean b) {
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == this.isWhite){
            return false;
        }
        else{
            //check if it's a bishop move
            if(Math.abs(this.x - x) == Math.abs(this.y - y)){
                int iterator = 1;

                //right up
                if(this.x - x < 0 &&  this.y - y > 0){
                    while(this.x + iterator != x){
                        //check if there is a piece blocking way
                        if(board.getSquareBoard()[this.y - iterator][this.x + iterator].isOccupied()){
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }

                //right down
                if(this.x - x < 0 &&  this.y - y < 0){
                    while(this.x + iterator != x){
                        //check if there is a piece blocking way
                        if(board.getSquareBoard()[this.y + iterator][this.x + iterator].isOccupied()){
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }

                //left down
                if(this.x - x > 0 &&  this.y - y < 0){
                    while(this.x - iterator != x){
                        //check if there is a piece blocking way
                        if(board.getSquareBoard()[this.y + iterator][this.x - iterator].isOccupied()){
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }

                //left up
                if(this.x - x > 0 &&  this.y - y > 0){
                    while(this.x - iterator != x){
                        //check if there is a piece blocking way
                        if(board.getSquareBoard()[this.y - iterator][this.x - iterator].isOccupied()){
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
