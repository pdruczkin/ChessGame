package gui;

import engine.Figures.*;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardPanel{
    /*public static final Image whitePawn = new Image("gui/assets/Pionek.png");
    public static final Image darkPawn = new Image("gui/assets/Pionek_cz.png");
    public static final Image whiteRook = new Image("gui/assets/Wieza.png");
    public static final Image darkRook = new Image("gui/assets/Wieza_cz.png");
    public static final Image whiteBishop = new Image("gui/assets/Goniec.png");
    public static final Image darkBishop = new Image("gui/assets/Goniec_cz.png");
    public static final Image whiteKnight = new Image("gui/assets/Skoczek.png");
    public static final Image darkKnight = new Image("gui/assets/Skoczek_cz.png");
    public static final Image whiteKing = new Image("gui/assets/Krol.png");
    public static final Image darkKing = new Image("gui/assets/Krol_cz.png");
    public static final Image whiteQueen = new Image("gui/assets/Hetman.png");
    public static final Image darkQueen = new Image("gui/assets/Hetman_cz.png");*/

    private Scene scene;
    public BoardPanel(Group root,int width,int height,int cell){
        scene = new Scene(root,width,height);
        setTitles(root,cell);
    }

    public Scene getScene() {
        return scene;
    }

    private void setTitles(Group root, int cell){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(cell);
                rectangle.setHeight(cell);
                if((i+j)%2 == 0) rectangle.setFill(Color.LIGHTBLUE);
                else rectangle.setFill(Color.BROWN);
                rectangle.setX(j*cell);
                rectangle.setY(i*cell);
                root.getChildren().add(rectangle);
            }
        }
    }

    /*private void setFigures(Group root, int cell){
        ImageView [][] views = new ImageView[4][8];
        for (int i = 0; i < 8; i++) {
            views[1][i] = new ImageView(darkPawn);
            views[1][i].setX(i*cell);
            views[1][i].setY(cell);

            views[3][i] = new ImageView(whitePawn);
            views[3][i].setX(i*cell);
            views[3][i].setY(6 * cell);

            root.getChildren().add(views[1][i]);
            root.getChildren().add(views[3][i]);
        }*/


}
