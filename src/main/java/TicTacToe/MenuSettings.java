package TicTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuSettings {

    private StackPane pane;
    private Label message;
    private Label message2;
    private Label message3;
    private Button playButton;
    private Button backToMenu;
    private Button exitButton;
    ToggleGroup tg;
    ToggleGroup tg2;
    ToggleGroup tg3;
    RadioButton chooseModeComputer;
    RadioButton chooseModePlayer;
    RadioButton chooseBoard3x3;
    RadioButton chooseBoard10x10;
    RadioButton chooseEasyButton;
    RadioButton chooseDiffButton;


    public MenuSettings(TileBoard tileBoard){
        pane = new StackPane();
        pane.setVisible(false);
        pane.setMinSize(AppConstants.APP_WIDTH, AppConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(AppConstants.APP_WIDTH/2);
        pane.setTranslateY(AppConstants.TILE_BOARD_HEIGHT/2);

        playButton = new Button("Play");
        playButton.setMinSize(155, 40);
        playButton.setTranslateY(360);
        playButton.setStyle("-fx-background-color: #2dd545");
        pane.getChildren().add(playButton);
        playButton.setVisible(false);
        playButton.setDisable(true);

        backToMenu = new Button("Back to menu");
        backToMenu.setMinSize(155, 40);
        backToMenu.setTranslateY(475);
        backToMenu.setTranslateX(-190);
        backToMenu.setStyle("-fx-background-color: #2dd545");
        pane.getChildren().add(backToMenu);
        backToMenu.setVisible(false);

        exitButton = new Button("Exit");
        exitButton.setMinSize(155, 40);
        exitButton.setTranslateY(475);
        exitButton.setTranslateX(190);
        exitButton.setStyle("-fx-background-color: #2dd545");
        pane.getChildren().add(exitButton);
        exitButton.setVisible(false);

// Player 1 - Player 2/Computer:
        message = new Label("Choose game mode: ");
        message.setTextFill(Color.WHITE);
        message.setMinSize(300, 50);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-100);
        pane.getChildren().add(message);

        chooseModeComputer=new RadioButton();
        chooseModePlayer=new RadioButton();
        chooseModeComputer.setTextFill(Color.WHITE);
        chooseModePlayer.setTextFill(Color.WHITE);
        chooseModeComputer.setMinSize(200, 35);
        chooseModePlayer.setMinSize(200, 35);
        chooseModeComputer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TileBoard.withComputer=true;
                showChooseDifficulty();
                playButton.setVisible(true);
            }
        });
        chooseModeComputer.setFont(Font.font(16));
        chooseModeComputer.setText(" Player vs Computer");
        chooseModePlayer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TileBoard.withComputer=false;
                hideChooseDifficulty();
                playButton.setVisible(true);
            }
        });
        chooseModePlayer.setFont(Font.font(16));
        chooseModePlayer.setText(" Player vs Player2");
        tg = new ToggleGroup();
        chooseModeComputer.setToggleGroup(tg);
        chooseModePlayer.setToggleGroup(tg);
        chooseModeComputer.setAlignment(Pos.CENTER_LEFT);
        chooseModeComputer.setTranslateY(-55);
        chooseModePlayer.setAlignment(Pos.CENTER_LEFT);
        chooseModePlayer.setTranslateY(-15);
        pane.getChildren().add(chooseModeComputer);
        pane.getChildren().add(chooseModePlayer);

 // Board size:
        message2 = new Label("Choose board size: ");
        message2.setTextFill(Color.WHITE);
        message2.setMinSize(300, 50);
        message2.setFont(Font.font(24));
        message2.setAlignment(Pos.CENTER);
        message2.setTranslateX(0);
        message2.setTranslateY(50);
        pane.getChildren().add(message2);

        chooseBoard3x3=new RadioButton();
        chooseBoard10x10=new RadioButton();
        chooseBoard3x3.setTextFill(Color.WHITE);
        chooseBoard10x10.setTextFill(Color.WHITE);
        chooseBoard3x3.setMinSize(200, 35);
        chooseBoard10x10.setMinSize(200, 35);
        chooseBoard3x3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TileBoard.tilesSize=3;
                playButton.setDisable(false);
            }
        });
        chooseBoard3x3.setFont(Font.font(16));
        chooseBoard3x3.setText(" Board  3x3");
        chooseBoard10x10.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TileBoard.tilesSize=10;
                playButton.setDisable(false);
           }
        });
        chooseBoard10x10.setFont(Font.font(16));
        chooseBoard10x10.setText(" Board 10x10");
        tg2 = new ToggleGroup();
        chooseBoard3x3.setToggleGroup(tg2);
        chooseBoard10x10.setToggleGroup(tg2);
        chooseBoard3x3.setAlignment(Pos.CENTER_LEFT);
        chooseBoard3x3.setTranslateY(95);
        chooseBoard10x10.setAlignment(Pos.CENTER_LEFT);
        chooseBoard10x10.setTranslateY(135);
        pane.getChildren().add(chooseBoard3x3);
        pane.getChildren().add(chooseBoard10x10);

// Choose difficulty:
        message3 = new Label("Choose difficulty: ");
        message3.setTextFill(Color.WHITE);
        message3.setMinSize(300, 50);
        message3.setFont(Font.font(24));
        message3.setAlignment(Pos.CENTER);
        message3.setTranslateX(0);
        message3.setTranslateY(200);
        pane.getChildren().add(message3);
        chooseEasyButton=new RadioButton();
        chooseDiffButton=new RadioButton();
        chooseEasyButton.setTextFill(Color.WHITE);
        chooseDiffButton.setTextFill(Color.WHITE);
        chooseEasyButton.setMinSize(200, 35);
        chooseDiffButton.setMinSize(200, 35);
        chooseEasyButton.setOnAction( event -> TileBoard.difficulty=false);
        chooseEasyButton.setFont(Font.font(16));
        chooseEasyButton.setText("Normal");
        chooseDiffButton.setOnAction( event -> TileBoard.difficulty=true );
        chooseDiffButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TileBoard.difficulty=true;
            }
        });
        chooseDiffButton.setFont(Font.font(16));
        chooseDiffButton.setText("Difficult");
        tg3 = new ToggleGroup();
        chooseEasyButton.setToggleGroup(tg3);
        chooseDiffButton.setToggleGroup(tg3);
        chooseEasyButton.setAlignment(Pos.CENTER_LEFT);
        chooseEasyButton.setTranslateY(245);
        chooseDiffButton.setAlignment(Pos.CENTER_LEFT);
        chooseDiffButton.setTranslateY(285);
        pane.getChildren().add( chooseEasyButton);
        pane.getChildren().add( chooseDiffButton);
    }


    public StackPane getStackPane() {
        return pane;
    }

    public void showChooseBoardOptions() {
        message2.setVisible(true);
        chooseBoard3x3.setVisible(true);
        chooseBoard10x10.setVisible(true);
    }

    public void showChoosePlayerMode() {
        message.setVisible(true);
        chooseModeComputer.setVisible(true);
        chooseModePlayer.setVisible(true);
    }

    public void showChooseDifficulty() {
        message3.setVisible(true);
        chooseEasyButton.setVisible(true);
        chooseDiffButton.setVisible(true);
    }

    public void hideChooseBoardOptions() {
        message2.setVisible(false);
        chooseBoard3x3.setVisible(false);
        chooseBoard10x10.setVisible(false);
    }

    public void hideChoosePlayerMode() {
        message.setVisible(false);
        chooseModeComputer.setVisible(false);
        chooseModePlayer.setVisible(false);
    }

    public void hideChooseDifficulty() {
        message3.setVisible(false);
        chooseEasyButton.setVisible(false);
        chooseDiffButton.setVisible(false);
    }

    public void hideExitButton() {
        exitButton.setVisible(false);
    }

    public void showExitButton() {
        exitButton.setVisible(true);
    }


    public void setExitButtonOnAction(EventHandler<ActionEvent> onAction){
        exitButton.setOnAction(onAction);
    }


    public void showPlayButton() {
        playButton.setVisible(true);
    }

    public void hidePlayButton() {
        playButton.setVisible(false);
    }

    public void setPlayButtonOnAction(EventHandler<ActionEvent> onAction){
        playButton.setOnAction(onAction);
    }

    public void setBackToMenu(EventHandler<ActionEvent> onAction){
        backToMenu.setOnAction(onAction);
    }

    public void hideBackToMenu() {
        backToMenu.setVisible(false);
    }

    public void showBackToMenu() {
        backToMenu.setVisible(true);
    }



}
