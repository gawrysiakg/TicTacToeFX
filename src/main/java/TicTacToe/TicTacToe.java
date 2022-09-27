package TicTacToe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static TicTacToe.TileBoard.withComputer;


public class TicTacToe extends Application {
    private MenuPanel menuPanel;
    private MenuSettings menuSettings;
    private Score menuScore;
    private TileBoard tileBoard;
    private final Image image=new Image("file:src/main/resources/wood.jpg");

    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
            BackgroundSize backgroundSize = new BackgroundSize(0, 0, true, true, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImage);

            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, AppConstants.APP_WIDTH, AppConstants.APP_HEIGHT);

            root.setBackground(background);
            initLayout(root);
            primaryStage.setTitle(" TicTacToe");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }




    private void initLayout(BorderPane root) {
        initMenu(root);
        initMenuSettings(root);
        initScore(root);
    }

    private void initTileBoard(BorderPane root) {
        tileBoard=new TileBoard(menuPanel, menuSettings, menuScore);
        root.getChildren().add(tileBoard.getStackPane());
        tileBoard.getStackPane().setVisible(false);
    }

    private void playButton(BorderPane root){
        menuSettings.showPlayButton();
        menuSettings.setPlayButtonOnAction(createNewGame(root));
        menuSettings.setBackToMenu(back());
        menuSettings.setExitButtonOnAction(exit());
    }

    private EventHandler<ActionEvent> createNewGame(BorderPane root){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               menuSettings.hidePlayButton();
               menuSettings.hideChoosePlayerMode();
               menuSettings.hideChooseBoardOptions();
               menuSettings.hideChooseDifficulty();
                initTileBoard(root);
                tileBoard.getStackPane().setVisible(true);
                menuSettings.showBackToMenu();
                menuSettings.hideExitButton();
                menuPanel.showScoreLabel();


                if (withComputer){
                    menuPanel.updateMessage("Player - X, Computer - 0, Player turn:");
                } else {
                    menuPanel.updateMessage("Player X move");
                }

                System.out.println("Starting Game, board size "+TileBoard.tilesSize);
                tileBoard.startNewGame();
            }
        };
    }

            //if press Start New Game Button, hide button, show menuSettings
    private EventHandler<ActionEvent> startNewGame(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuPanel.hideStartButton();
                menuSettings.getStackPane().setVisible(true);
            }
        };
    }

    private EventHandler<ActionEvent> back(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuSettings.getStackPane().setVisible(true);
                tileBoard.getStackPane().setVisible(false);
                menuSettings.showPlayButton();
                menuSettings.showChoosePlayerMode();
                menuSettings.showChooseBoardOptions();
                menuSettings.hideBackToMenu();
                menuSettings.showExitButton();
                menuPanel.hideScoreLabel();

                TileBoard.setxPoints(0);
                menuPanel.updateXScore("X points: "+TileBoard.getxPoints());
                TileBoard.setoPoints(0);
                menuPanel.update0Score("O points: "+TileBoard.getoPoints());
                menuPanel.updateMessage("Tic Tac Toe");
                menuScore.getStackPane().setVisible(false);
            }
        };
    }

    private EventHandler<ActionEvent> exit(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        };
    }

    private void initMenu(BorderPane root) {
        menuPanel = new MenuPanel();
        menuPanel.setStartButtonOnAction(startNewGame() );
        root.getChildren().add(menuPanel.getStackPane());
    }

    private void initScore(BorderPane root) {
        menuScore = new Score();
        menuPanel.setStartButtonOnAction(startNewGame() );
        root.getChildren().add(menuScore.getStackPane());
    }

    private void initMenuSettings(BorderPane root){
        menuSettings=new MenuSettings(tileBoard);
        playButton(root);
        root.getChildren().add(menuSettings.getStackPane());
    }




    public static void main(String[] args) {
            launch(args);
    }
}
