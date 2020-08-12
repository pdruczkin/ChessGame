package engine.Figures;


import engine.Board.Board;
import engine.Managment.MateFinder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Figure{

    MateFinder matefinder = new MateFinder();

    public King(boolean isWhite, byte x, byte y, ImageView imageView) {
        super(isWhite, x, y, (byte) 6,imageView);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {
        if (!matefinder.isMate(board, this.isWhite, true) && isCastlingAvailable(board, x, y))
            return true;

        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == this.isWhite){
            return false;
        }
        else{
            return Math.abs(this.x - x) <= 1 && Math.abs(this.y - y) <= 1;
        }

    }

    private boolean isCastlingAvailable(Board board, byte x, byte y){
        if(isMoved) return false;
        else{
            if(this.isWhite){
                if(y == 7 && x == 1){
                    if(board.getSquareBoard()[7][0].isOccupied() && !board.getSquareBoard()[7][0].getFigure().isMoved()){
                        matefinder.setKingYCords((byte) 7);
                        matefinder.setKingXCords((byte) 3);
                        if(matefinder.isMate(board, this.isWhite, false)) return false;
                        matefinder.setKingXCords((byte) 2);
                        if(matefinder.isMate(board, this.isWhite, false)) return false;
                        return !board.getSquareBoard()[7][1].isOccupied() && !board.getSquareBoard()[7][2].isOccupied() && !board.getSquareBoard()[7][3].isOccupied();
                    }
                }
                else if(y == 7 && x == 6){
                    if(board.getSquareBoard()[7][7].isOccupied() && !board.getSquareBoard()[7][7].getFigure().isMoved()){
                        matefinder.setKingYCords((byte) 7);
                        matefinder.setKingXCords((byte) 5);
                        if(matefinder.isMate(board, this.isWhite, false)){
                            return false;
                        }
                        return !board.getSquareBoard()[7][6].isOccupied() && !board.getSquareBoard()[7][5].isOccupied();
                    }
                }
            }
            else{
                if(y == 0 && x == 1){
                    if(board.getSquareBoard()[0][0].isOccupied() && !board.getSquareBoard()[0][0].getFigure().isMoved()){
                        matefinder.setKingYCords((byte) 0);
                        matefinder.setKingXCords((byte) 3);
                        if(matefinder.isMate(board, this.isWhite, false)) return false;
                        matefinder.setKingXCords((byte) 2);
                        if(matefinder.isMate(board, this.isWhite, false)) return false;
                        return !board.getSquareBoard()[0][1].isOccupied() && !board.getSquareBoard()[0][2].isOccupied() && !board.getSquareBoard()[0][3].isOccupied();
                    }
                }
                else if(y == 0 && x == 6){
                    if(board.getSquareBoard()[0][7].isOccupied() && !board.getSquareBoard()[0][7].getFigure().isMoved()){
                        matefinder.setKingYCords((byte) 0);
                        matefinder.setKingXCords((byte) 5);
                        if(matefinder.isMate(board, this.isWhite, false)) return false;
                        return !board.getSquareBoard()[0][6].isOccupied() && !board.getSquareBoard()[0][5].isOccupied();
                    }
                }
            }
        }
        return false;
    }
}
