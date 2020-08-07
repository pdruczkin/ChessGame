package engine.Figures;

import engine.Board.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Figure {
    public Knight(boolean isWhite, byte x, byte y, ImageView imageView) {
        super(isWhite, x, y, (byte) 3,imageView);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {
        if(!board.getSquareBoard()[y][x].isOccupied() || board.getSquareBoard()[y][x].getFigure().isWhite() == !isWhite()){
            if(Math.abs(y - this.y) == 2 && Math.abs(x - this.x) == 1){
                return true;
            }
            if(Math.abs(x - this.x) == 2 && Math.abs(y - this.y) == 1){
                return true;
            }
        }
        return false;
    }
}
