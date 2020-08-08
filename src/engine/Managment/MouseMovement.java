package engine.Managment;

import engine.Board.Board;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMovement implements EventHandler<MouseEvent> {
    private Board board;
    int cell;

    public MouseMovement(Board board, int cell) {
        this.board = board;
        this.cell = cell;
    }

    private byte convertPixelsToCells(int pixels){
        return (byte)(pixels/cell);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + "  " +  mouseEvent.getY());
        byte x = convertPixelsToCells((int) mouseEvent.getX());
        byte y =  convertPixelsToCells((int) mouseEvent.getY());
        System.out.println(x + "  " + y );
        if(board.getSquareBoard()[y][x].isOccupied()){
            board.getSquareBoard()[y][x].getFigure().getImageview().setY(board.getSquareBoard()[y][x].getFigure().getImageview().getY() + cell);//takie zwykle gowienko do testow
        }
    }
}
