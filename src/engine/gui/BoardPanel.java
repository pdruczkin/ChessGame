package engine.gui;

import engine.Figures.*;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardPanel{

    private Scene scene;
    public BoardPanel(Group root,int width,int height,int cell){
        scene = new Scene(root,width,height);
        setTiles(root,cell);
    }

    public Scene getScene() {
        return scene;
    }

    private void setTiles(Group root, int cell){
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
}
