package engine.Managment;

import engine.Board.Board;
import engine.Player.Player;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMovement implements EventHandler<MouseEvent> {
    private Board board;
    int cell;
    byte clickedX;
    byte clickedY;
    byte realesedX;
    byte realesedY;

    public MouseMovement(Board board, int cell) {
        this.board = board;
        this.cell = cell;
    }

    private byte convertPixelsToCells(int pixels){
        return (byte)(pixels/cell);
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            clickedX = convertPixelsToCells((int) mouseEvent.getX());
            clickedY =  convertPixelsToCells((int) mouseEvent.getY());
        }
        else if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            realesedX = convertPixelsToCells((int) mouseEvent.getX());
            realesedY =  convertPixelsToCells((int) mouseEvent.getY());
        }
    }


}
