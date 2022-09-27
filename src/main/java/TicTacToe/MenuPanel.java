package TicTacToe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuPanel {
    private final StackPane pane;
    private final Label message;
    private Label messageXPoints;
    private Label message0Points;
    private final Button startGameButton;

    public MenuPanel(){
        pane = new StackPane();
        pane.setMaxSize(AppConstants.APP_WIDTH, AppConstants.MENU_PANEL_HEIGHT);
        pane.setTranslateX(AppConstants.APP_WIDTH/2);
        pane.setTranslateY(AppConstants.MENU_PANEL_HEIGHT/2);
        message = new Label("Tic Tac Toe");
        message.setMinSize(AppConstants.APP_WIDTH, AppConstants.MENU_PANEL_HEIGHT);
        message.setFont(Font.font(26));
        message.setTextFill(Color.WHITE);
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-40);
        pane.getChildren().add(message);
        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(40);
        pane.getChildren().add(startGameButton);
        startGameButton.setVisible(true);


        messageXPoints = new Label("X points: 0");
        messageXPoints.setMinSize(120, 30);
        messageXPoints.setFont(Font.font(16));
        messageXPoints.setTextFill(Color.WHITE);
        messageXPoints.setTranslateX(-200);
        messageXPoints.setTranslateY(90);
        pane.getChildren().add(messageXPoints);
        messageXPoints.setVisible(false);

        message0Points = new Label("0 points: 0");
        message0Points.setMinSize(120, 30);
        message0Points.setFont(Font.font(16));
        message0Points.setTextFill(Color.WHITE);
        message0Points.setTranslateX(220);
        message0Points.setTranslateY(90);
        pane.getChildren().add(message0Points);
        message0Points.setVisible(false);


    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message) {
        this.message.setText(message);
    }

    public void updateXScore(String message) {
        this.messageXPoints.setText(message);
    }

    public void update0Score(String message) {
        this.message0Points.setText(message);
    }

    public void showScoreLabel(){
        messageXPoints.setVisible(true);
        message0Points.setVisible(true);
    }

    public void hideScoreLabel(){
        messageXPoints.setVisible(false);
        message0Points.setVisible(false);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction){
        startGameButton.setOnAction(onAction);
    }
}
