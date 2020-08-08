package engine.Managment;

import engine.Board.Board;
import engine.Figures.*;
import engine.Player.Player;
import engine.gui.BoardPanel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Scanner;

public class Manager extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1100;
    private static final int CELL = 100;

    private Group root = new Group();
    private Board board = new Board();
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


    private void setFigures(){
        /* nie dziala :/
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Hello World");
                imageView.setY(mouseEvent.getY());
                imageView.setX(mouseEvent.getX());
            }
        };*/

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
        System.out.println("Promote the pawn to: ");
        System.out.println("1 -> ROOK");
        System.out.println("2 -> KNIGHT");
        System.out.println("3 -> BISHOP");
        System.out.println("4 -> QUEEN");
    }

    private void changingFigure(Board board, Player performer, int choice){
        switch (choice) {
            //do zmiany
            case 2 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Rook(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY(),new ImageView(darkPawn)));
            case 3 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Knight(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY(),new ImageView(darkPawn)));
            case 4 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Bishop(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY(),new ImageView(darkPawn)));
            case 5 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Queen(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY(),new ImageView(darkPawn)));

        }
    }

    private void promotePawn(Board board, Player performer){
        presentPromotionOptions();
        Scanner scanner = new Scanner(System.in);
        int answer;
        do{
            answer = scanner.nextInt();
            changingFigure(board, performer, answer + 1);
            if(answer < 1 || answer > 4) System.out.println("Enter a valid value");
        }while(answer < 1 || answer > 4);
    }








    public boolean extortMove(Board board, Player performer){

        System.out.println(performer.getColourName() + "'s move:");
        performer.setOldCords();

        if(board.getSquareBoard()[performer.getOldY()][performer.getOldX()].isOccupied()
                && board.getSquareBoard()[performer.getOldY()][performer.getOldX()].getFigure().isWhite() == performer.isWhite()){
            possibleMovesFinder.setPossibleMove(board, performer.getOldX(), performer.getOldY(),performer.isWhite());
            if(!possibleMovesFinder.checkPossibleMoves()){
                System.out.println("This figure can't move anywhere");
                return false;
            }
            possibleMovesFinder.printPossibleMoves();
            performer.setNewCords();
            if(possibleMovesFinder.checkMove(performer.getNewX(), performer.getNewY())){
                possibleMovesFinder.clearJustMovedTwo(board);
                move.moveFigure(board, performer.getNewX(), performer.getNewY(), performer.getOldX(), performer.getOldY(), true);
                if(board.getSquareBoard()[performer.getNewY()][performer.getNewX()].getFigure().getType() == 1){
                    if((performer.isWhite() && performer.getNewY() == 0) || (!performer.isWhite() && performer.getNewY() == 7)) {
                        promotePawn(board, performer);
                    }
                }
                return true;
            }
        }
        System.out.println("You can't move the figure, try again");
        return false;
    }








    public void run(Player player1, Player player2){

       /* Player performer = new Player();
        performer = player2;
        boolean check = true;

        while(!(mateFinder.isCheckMate(board, !performer.isWhite())
                && !mateFinder.isStaleMate(board, !performer.isWhite(),check))){
            // System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false) );

            if(performer.equals(player1)){ performer = player2; }
            else{ performer = player1; }

            possibleMovesFinder.FindAnyPossibleMoves(board, !performer.isWhite());
            check = possibleMovesFinder.getAreAnyPossibleMoves();
            do {
                board.print(true);
                // System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false) );
            }while(!extortMove(board, performer));
        }
        endingMessage(performer);
    */
    }
    private byte convertPixelsToCells(int pixels){
        return (byte)(pixels/CELL);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BoardPanel boardPanel = new BoardPanel(root,WIDTH,HEIGHT,CELL);
        Player player1 = new Player(true, "engine.Player.engine.Player 1");
        Player player2 = new Player(false, "engine.Player.engine.Player 2");

        setFigures();

        boardPanel.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new MouseMovement(board,CELL));



        stage.setTitle("CHESS GAME");
        stage.setScene(boardPanel.getScene());
        stage.show();


    }
}