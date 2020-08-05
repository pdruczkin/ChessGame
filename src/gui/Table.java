package gui;

import engine.Board.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Table extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1100;
    private static final int CELL = 100;

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        BoardPanel boardPanel = new BoardPanel(root,WIDTH,HEIGHT,CELL);
        stage.setTitle("CHESS GAME");
        stage.setScene(boardPanel.getScene());
        stage.show();
    }



    public Table(){

    }

}
