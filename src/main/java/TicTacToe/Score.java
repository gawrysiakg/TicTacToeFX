package TicTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
    private StackPane pane;
    private Label message;
    private Button playButton;

    public Score(){
        pane = new StackPane();
        pane.setVisible(false);
        pane.setMinSize(AppConstants.APP_WIDTH, AppConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(AppConstants.APP_WIDTH/2);
        pane.setTranslateY(AppConstants.TILE_BOARD_HEIGHT/2);

//        startGameButton = new Button("Play");
//        startGameButton.setMinSize(155, 40);
//        startGameButton.setTranslateY(360);
//        startGameButton.setStyle("-fx-background-color: #2dd545");
//        pane.getChildren().add(startGameButton);
//


        message = new Label("Congratulations ");
        message.setMinSize(AppConstants.APP_WIDTH, AppConstants.APP_HEIGHT);
        message.setFont(Font.font(26));
        message.setTextFill(Color.WHITE);
        message.setAlignment(Pos.CENTER);
        //message.setTranslateX(-150);
        message.setTranslateY(0);
        pane.getChildren().add(message);

//        playButton = new Button("Play");
//        playButton.setMinSize(155, 40);
//        playButton.setTranslateY(360);
//        playButton.setStyle("-fx-background-color: #2dd545");
//        pane.getChildren().add(playButton);

    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message) {
        this.message.setText(message);
    }

    public void showStartButton() {
        playButton.setVisible(true);
    }

    public void hideStartButton() {

        playButton.setVisible(false);
    }


    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction){

        playButton.setOnAction(onAction);

    }
}
