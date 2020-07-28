package Board;

import Figures.Figure;

public class Square {
    private int x,y;
    private boolean isOccupied;
    private Figure figure;

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

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        isOccupied = false;
        figure = null;
    }
}
