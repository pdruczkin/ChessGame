package engine.Board;

import engine.Figures.Figure;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
    private int x,y;
    private boolean isOccupied;
    private Figure figure;
    private Rectangle square;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Rectangle getSquare() {
        return square;
    }

    public void setSquare(Rectangle square) {
        this.square = square;
    }

    public Square(int x, int y, Group root, int cell) {
        this.x = x;
        this.y = y;
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(cell);
        rectangle.setHeight(cell);
        if((x+y)%2 == 0) rectangle.setFill(Color.STEELBLUE);
        else rectangle.setFill(Color.PALETURQUOISE);
        rectangle.setX(x*cell);
        rectangle.setY(y*cell);
        this.square = rectangle;
        root.getChildren().add(rectangle);
        isOccupied = false;
        figure = null;
    }
}
