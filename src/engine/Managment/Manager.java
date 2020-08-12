package engine.Managment;

import engine.Board.Board;
import engine.Figures.*;
import engine.Player.Player;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Manager extends Application implements EventHandler<MouseEvent> {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1100;
    private static final int CELL = 100;

    private Group root = new Group();
    private Board board = new Board(root,CELL);
    private MateFinder mateFinder = new MateFinder();
    private PossibleMovesFinder possibleMovesFinder = new PossibleMovesFinder();
    private Move move = new Move();



    private Image whitePawn = new Image("engine/assets/Pionek.png");;
    private Image darkPawn = new Image("engine/assets/Pionek_cz.png");;
    private Image whiteRook = new Image("engine/assets/Wieza.png");
    private Image darkRook = new Image("engine/assets/Wieza_cz.png");
    private Image whiteBishop = new Image("engine/assets/Goniec.png");
    private Image darkBishop = new Image("engine/assets/Goniec_cz.png");
    private Image whiteKnight = new Image("engine/assets/Skoczek.png") ;
    private Image darkKnight = new Image("engine/assets/Skoczek_cz.png");
    private Image whiteKing = new Image("engine/assets/Krol.png");
    private Image darkKing = new Image("engine/assets/Krol_cz.png");
    private Image whiteQueen = new Image("engine/assets/Hetman.png");
    private Image darkQueen = new Image("engine/assets/Hetman_cz.png");

    //for mouse movements
    private Player performer;
    private Player player1,player2;
    private int clickedCordsX,clickedCordsY;
    private boolean isClickedGood;


    private void setFigures(){


        ImageView imageView;

        //setting Pawns(1)
        for (byte i = 0; i < 8; i++) {
            imageView = new ImageView(darkPawn);
            imageView.setY(CELL);
            imageView.setX(i*CELL);
            board.getSquareBoard()[1][i].setFigure(new Pawn(false,i,(byte)1,imageView));
            board.getSquareBoard()[1][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[1][i].getFigure().getImageview());

        }
        for (byte i = 0; i < 8; i++) {
            imageView = new ImageView(whitePawn);
            imageView.setY(6*CELL);
            imageView.setX(i*CELL);
            board.getSquareBoard()[6][i].setFigure(new Pawn(true,i,(byte)6,imageView));
            board.getSquareBoard()[6][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[6][i].getFigure().getImageview());
        }

        //setting Rooks(2)
        for(byte i = 0; i < 8; i = (byte) (i+7)){
            imageView = new ImageView(darkRook);
            imageView.setY(0);
            imageView.setX(i*CELL);
            board.getSquareBoard()[0][i].setFigure(new Rook(false,i,(byte)0,imageView));
            board.getSquareBoard()[0][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[0][i].getFigure().getImageview());
        }
        for(byte i = 0; i < 8; i = (byte) (i+7)){
            imageView = new ImageView(whiteRook);
            imageView.setY(7*CELL);
            imageView.setX(i*CELL);
            board.getSquareBoard()[7][i].setFigure(new Rook(true,i,(byte)7,imageView));
            board.getSquareBoard()[7][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[7][i].getFigure().getImageview());
        }

        //setting Knights(3)
        for(byte i = 1; i < 8; i = (byte) (i+5)){
            imageView = new ImageView(darkKnight);
            imageView.setY(0);
            imageView.setX(i*CELL);
            board.getSquareBoard()[0][i].setFigure(new Knight(false,i,(byte)0,imageView));
            board.getSquareBoard()[0][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[0][i].getFigure().getImageview());
        }
        for(byte i = 1; i < 8; i = (byte) (i+5)){
            imageView = new ImageView(whiteKnight);
            imageView.setY(7*CELL);
            imageView.setX(i*CELL);
            board.getSquareBoard()[7][i].setFigure(new Knight(true,i,(byte)7,imageView));
            board.getSquareBoard()[7][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[7][i].getFigure().getImageview());
        }

        //setting Bishops(4)
        for(byte i = 2; i < 8; i = (byte) (i+3)){
            imageView = new ImageView(darkBishop);
            imageView.setY(0);
            imageView.setX(i*CELL);
            board.getSquareBoard()[0][i].setFigure(new Bishop(false,i,(byte)0,imageView));
            board.getSquareBoard()[0][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[0][i].getFigure().getImageview());
        }
        for(byte i = 2; i < 8; i = (byte) (i+3)){
            imageView = new ImageView(whiteBishop);
            imageView.setY(7*CELL);
            imageView.setX(i*CELL);
            board.getSquareBoard()[7][i].setFigure(new Bishop(true,i,(byte)7,imageView));
            board.getSquareBoard()[7][i].setOccupied(true);
            root.getChildren().add(board.getSquareBoard()[7][i].getFigure().getImageview());
        }

        //setting Queens(5)
        imageView = new ImageView(darkQueen);
        imageView.setY(0);
        imageView.setX(3*CELL);
        board.getSquareBoard()[0][3].setFigure(new Queen(false,(byte)3,(byte)0,imageView));
        board.getSquareBoard()[0][3].setOccupied(true);
        root.getChildren().add(board.getSquareBoard()[0][3].getFigure().getImageview());
        imageView = new ImageView(whiteQueen);
        imageView.setY(7*CELL);
        imageView.setX(3*CELL);
        board.getSquareBoard()[7][3].setFigure(new Queen(true,(byte)3,(byte)7,imageView));
        board.getSquareBoard()[7][3].setOccupied(true);
        root.getChildren().add(board.getSquareBoard()[7][3].getFigure().getImageview());

        //setting Kings(6)
        imageView = new ImageView(darkKing);
        imageView.setY(0);
        imageView.setX(4*CELL);
        board.getSquareBoard()[0][4].setFigure(new King(false,(byte)4,(byte)0,imageView));
        board.getSquareBoard()[0][4].setOccupied(true);
        root.getChildren().add(board.getSquareBoard()[0][4].getFigure().getImageview());
        imageView = new ImageView(whiteKing);
        imageView.setY(7*CELL);
        imageView.setX(4*CELL);
        board.getSquareBoard()[7][4].setFigure(new King(true,(byte)4,(byte)7,imageView));
        board.getSquareBoard()[7][4].setOccupied(true);
        root.getChildren().add(board.getSquareBoard()[7][4].getFigure().getImageview());

        /* for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getSquareBoard()[i][j].isOccupied()){
                    board.getSquareBoard()[i][j].getFigure().getImageview().addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                }
            }
        }*/
    }

    private void presentPromotionOptions(){

        for (int i = 0; i < 4; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(4 * CELL);
            rectangle.setHeight(4 * CELL);
            rectangle.setFill(Color.WHITE);
            rectangle.setX(i * 4 * CELL);
            rectangle.setY(4 * CELL);
            root.getChildren().add(rectangle);
        }

    }

    private void changingFigure(Board board, byte x, byte y, int choice){
        //delete old imageView from root
        root.getChildren().remove(board.getSquareBoard()[y][x].getFigure().getImageview());


        //add new imageView to new Figure
        ImageView imageView = new ImageView();
        imageView.setY(y*CELL);
        imageView.setX(x*CELL);
        root.getChildren().add(imageView);
        switch (choice) {
            case 1 -> { //ROOK
                if(performer.isWhite()){
                    imageView.setImage(whiteRook);
                }
                else{
                    imageView.setImage(darkRook);
                }
                board.getSquareBoard()[y][x].setFigure(new Rook(performer.isWhite(), x, y,imageView));
            }
            case 2 -> { //BISHOP
                if(performer.isWhite()){
                    imageView.setImage(whiteBishop);
                }
                else{
                    imageView.setImage(darkBishop);
                }
                board.getSquareBoard()[y][x].setFigure(new Bishop(performer.isWhite(),x,y,imageView));
            }
            case 3 -> { // QUEEN
                if(performer.isWhite()){
                    imageView.setImage(whiteBishop);
                }
                else{
                    imageView.setImage(darkBishop);
                }
                board.getSquareBoard()[y][x].setFigure(new Queen(performer.isWhite(), x,y ,imageView));
            }
            case 4 -> { // Knight
                if(performer.isWhite()){
                    imageView.setImage(whiteKnight);
                }
                else{
                    imageView.setImage(darkKnight);
                }
                board.getSquareBoard()[y][x].setFigure(new Knight(performer.isWhite(), x,y ,imageView));
            }

        }
    }

    private void promotePawn(Board board, byte x, byte y){
        presentPromotionOptions();
        int answer = 1;
        do{

            changingFigure(board, x , y, 1);
            if(answer < 1 || answer > 4) System.out.println("Enter a valid value");
        }while(answer < 1 || answer > 4);
    }
    private byte convertPixelsToCells(int pixels){
        return (byte)(pixels/CELL);
    }

    private boolean checkStartingPosition(byte x, byte y, Player performer){
        if(x < 0 || x > 7 || y < 0 || y > 7) return false;
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == performer.isWhite()){
            possibleMovesFinder.setPossibleMove(board, x , y ,performer.isWhite());
            if(!possibleMovesFinder.checkPossibleMoves()){
                System.out.println("CHUJA SIE TYM RUSZYSZ");
                return false;
            }
            return true;
        }
        return false;
    }

    private void moveImage(int x, int y){
        int clickedX = convertPixelsToCells(clickedCordsX);
        int clickedY = convertPixelsToCells(clickedCordsY);
        ImageView tmp = board.getSquareBoard()[clickedY][clickedX].getFigure().getImageview();
        tmp.setX(x-CELL/2.5);
        tmp.setY(y-CELL/2.5);
        board.getSquareBoard()[clickedY][clickedX].getFigure().setImageView(tmp);

    }
    private void changePlayer(){
        if(performer == player1) performer = player2;
        else if(performer == player2) performer = player1;
        else System.out.println("CHUJOWO");
    }

    private void checkCondtion(){
        possibleMovesFinder.findAnyPossibleMoves(board,performer.isWhite());
        boolean areAnyMoves = possibleMovesFinder.getAreAnyPossibleMoves();
        System.out.println(areAnyMoves);
        if(mateFinder.isCheckMate(board,performer.isWhite())){
            System.out.println("CHECKMATE");
            //System.exit(0);
        }
        if(mateFinder.isStaleMate(board,performer.isWhite(),areAnyMoves)){
            System.out.println("STALEMATE");
            //System.exit(0);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        player1 = new Player(true, "engine.Player.engine.Player 1");
        player2 = new Player(false, "engine.Player.engine.Player 2");
        performer = player1;

        Scene scene = new Scene(root);
        setFigures();
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, this);
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, this);
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, this);




        stage.setTitle("CHESS GAME");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            clickedCordsX = (int)mouseEvent.getX();
            clickedCordsY = (int)mouseEvent.getY();
            byte clickedX = convertPixelsToCells(clickedCordsX);
            byte clickedY = convertPixelsToCells(clickedCordsY);

            if(checkStartingPosition(clickedX,clickedY,performer)){
                isClickedGood = true;
                possibleMovesFinder.setColorPossibleMoves(board);
            }
            else{
                isClickedGood = false;
            }

        }
        else if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED){
            if(isClickedGood){
                int x = (int)mouseEvent.getX();
                int y = (int)mouseEvent.getY();
                moveImage(x,y);
            }
            else{
                System.out.println("Po chuj ruszasz jak nie mozesz");
            }
        }
        else if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
            if(isClickedGood){
                possibleMovesFinder.undoColorPossibleMoves(board);
                int releasedCordsX = (int) mouseEvent.getX();
                int releasedCordsY = (int) mouseEvent.getY();

                byte releasedX = convertPixelsToCells(releasedCordsX);
                byte releasedY = convertPixelsToCells(releasedCordsY);

                boolean isMoveDone = false;

                if(releasedX >= 0 && releasedX < 8 && releasedY >= 0 && releasedY < 8 ) {
                    if (possibleMovesFinder.checkMove(releasedX, releasedY)) {
                        possibleMovesFinder.clearJustMovedTwo(board);
                        move.moveFigureAndView(board, releasedX , releasedY, convertPixelsToCells(clickedCordsX), convertPixelsToCells(clickedCordsY), CELL, root);
                        isMoveDone = true;
                        if (isMoveDone && board.getSquareBoard()[releasedY][releasedX].getFigure().getType() == 1) {
                            if ((performer.isWhite() && releasedY == 0) || (!performer.isWhite() && releasedY == 7)) {

                                promotePawn(board, releasedX, releasedY);

                            }
                        }

                        changePlayer();
                        checkCondtion();
                    }
                }
                if(!isMoveDone){
                        ImageView imageView = board.getSquareBoard()[convertPixelsToCells(clickedCordsY)][convertPixelsToCells(clickedCordsX)].getFigure().getImageview();
                        imageView.setX(convertPixelsToCells(clickedCordsX) * CELL);
                        imageView.setY(convertPixelsToCells(clickedCordsY) * CELL);
                        board.getSquareBoard()[convertPixelsToCells(clickedCordsY)][convertPixelsToCells(clickedCordsX)].getFigure().setImageView(imageView);
                }
            }
        }
    }
}