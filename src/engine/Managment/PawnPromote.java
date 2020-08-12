package engine.Managment;

import engine.Board.Board;
import engine.Figures.Bishop;
import engine.Figures.Knight;
import engine.Figures.Queen;
import engine.Figures.Rook;
import engine.Player.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PawnPromote {
    private final int cell;


    private final Image whiteRook = new Image("engine/assets/Wieza.png");
    private final Image darkRook = new Image("engine/assets/Wieza_cz.png");
    private final Image whiteBishop = new Image("engine/assets/Goniec.png");
    private final Image darkBishop = new Image("engine/assets/Goniec_cz.png");
    private final Image whiteKnight = new Image("engine/assets/Skoczek.png") ;
    private final Image darkKnight = new Image("engine/assets/Skoczek_cz.png");
    private final Image whiteQueen = new Image("engine/assets/Hetman.png");
    private final Image darkQueen = new Image("engine/assets/Hetman_cz.png");

    private Rectangle[] rectangles = new Rectangle[4];
    private ImageView whiteRookView = new ImageView(whiteRook);
    private ImageView darkRookView= new ImageView(darkRook);
    private ImageView whiteBishopView = new ImageView(whiteBishop);
    private ImageView darkBishopView = new ImageView(darkBishop);
    private ImageView whiteQueenView = new ImageView(whiteQueen);
    private ImageView darkQueenView = new ImageView(darkQueen);
    private ImageView whiteKnightView = new ImageView(whiteKnight);
    private ImageView darkKnightView = new ImageView(darkKnight);

    PawnPromote(int cell) {
        this.cell = cell;
    }


    public void presentPromotionOptions(Group root, Player performer) {
        for (int i = 0; i < 4; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setWidth(2 * cell);
            rectangles[i].setHeight(2 * cell);
            if(i%2 == 0){
                rectangles[i].setFill(Color.rgb(232,232,232,0.8));
            }
            else {
                rectangles[i].setFill(Color.rgb(121,121,121,0.8));
            }
            rectangles[i].setX(i * 2 * cell);
            rectangles[i].setY(3 * cell);
            root.getChildren().add(rectangles[i]);
        }

        if(performer.isWhite()){
            whiteRookView.setFitHeight(2*cell);
            whiteRookView.setFitWidth(2*cell);
            whiteRookView.setY(3 * cell);
            whiteRookView.setX(0);

            root.getChildren().add(whiteRookView);

            whiteBishopView.setFitHeight(2*cell);
            whiteBishopView.setFitWidth(2*cell);
            whiteBishopView.setY(3 * cell);
            whiteBishopView.setX(2* cell);

            root.getChildren().add(whiteBishopView);

            whiteQueenView.setFitHeight(2*cell);
            whiteQueenView.setFitWidth(2*cell);
            whiteQueenView.setY(3 * cell);
            whiteQueenView.setX(4 * cell);

            root.getChildren().add(whiteQueenView);

            whiteKnightView.setFitHeight(2*cell);
            whiteKnightView.setFitWidth(2*cell);
            whiteKnightView.setY(3 * cell);
            whiteKnightView.setX(6* cell);

            root.getChildren().add(whiteKnightView);
        }
        else{
            darkRookView.setFitHeight(2*cell);
            darkRookView.setFitWidth(2*cell);
            darkRookView.setY(3 * cell);
            darkRookView.setX(0);

            root.getChildren().add(darkRookView);

            darkBishopView.setFitHeight(2*cell);
            darkBishopView.setFitWidth(2*cell);
            darkBishopView.setY(3 * cell);
            darkBishopView.setX(2* cell);

            root.getChildren().add(darkBishopView);

            darkQueenView.setFitHeight(2*cell);
            darkQueenView.setFitWidth(2*cell);
            darkQueenView.setY(3 * cell);
            darkQueenView.setX(4 * cell);

            root.getChildren().add(darkQueenView);

            darkKnightView.setFitHeight(2*cell);
            darkKnightView.setFitWidth(2*cell);
            darkKnightView.setY(3 * cell);
            darkKnightView.setX(6* cell);

            root.getChildren().add(darkKnightView);
        }
    }

    public void undoPresenting(Group root, Player performer){
        for (int i = 0; i < 4; i++) {
            root.getChildren().remove(rectangles[i]);
        }

        if(performer.isWhite()){
            root.getChildren().remove(whiteRookView);
            root.getChildren().remove(whiteQueenView);
            root.getChildren().remove(whiteBishopView);
            root.getChildren().remove(whiteKnightView);
        }
        else{
            root.getChildren().remove(darkRookView);
            root.getChildren().remove(darkQueenView);
            root.getChildren().remove(darkBishopView);
            root.getChildren().remove(darkKnightView);
        }
    }

        private int getChoice(int x){
        return x/2 + 1;
        }


        public boolean changingFigure(Group root, Board board, byte x, byte y, byte selectedIconX, int selectedIconY, Player performer) {
            System.out.println(selectedIconX + "  " + selectedIconY);
            if (selectedIconY == 3 || selectedIconY == 4) {
                int choice = getChoice(selectedIconX);
                System.out.println(choice + " CHOICE");
                //delete old imageView from root
                root.getChildren().remove(board.getSquareBoard()[y][x].getFigure().getImageview());
                //add new imageView to new Figure
                ImageView imageView = new ImageView();
                imageView.setY(y * cell);
                imageView.setX(x * cell);
                root.getChildren().add(imageView);
                switch (choice) {
                    case 1 -> { //ROOK
                        if (performer.isWhite()) {
                            imageView.setImage(whiteRook);
                        } else {
                            imageView.setImage(darkRook);
                        }
                        board.getSquareBoard()[y][x].setFigure(new Rook(performer.isWhite(), x, y, imageView));
                        return true;
                    }
                    case 2 -> { //BISHOP
                        if (performer.isWhite()) {
                            imageView.setImage(whiteBishop);
                        } else {
                            imageView.setImage(darkBishop);
                        }
                        board.getSquareBoard()[y][x].setFigure(new Bishop(performer.isWhite(), x, y, imageView));
                        return true;
                    }
                    case 3 -> { // QUEEN
                        if (performer.isWhite()) {
                            imageView.setImage(whiteQueen);
                        } else {
                            imageView.setImage(darkQueen);
                        }
                        board.getSquareBoard()[y][x].setFigure(new Queen(performer.isWhite(), x, y, imageView));
                        return true;
                    }
                    case 4 -> { // Knight
                        if (performer.isWhite()) {
                            imageView.setImage(whiteKnight);
                        } else {
                            imageView.setImage(darkKnight);
                        }
                        board.getSquareBoard()[y][x].setFigure(new Knight(performer.isWhite(), x, y, imageView));
                        return true;
                    }

                }

            }
            return false;
        }
}
