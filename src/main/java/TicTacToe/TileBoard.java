package TicTacToe;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.Random;


public class TileBoard {
    private final StackPane pane;
    private final MenuPanel menuPanel;
    private final MenuSettings menuSettings;
    private final Score menuScore;
    public static int tilesSize;
    public static boolean withComputer;
    public static boolean difficulty=false;
    private Tile [][] tiles = new Tile[tilesSize][tilesSize];
    private String activePlayer="X";
    private boolean endGame=false;
    static int borderTileSize=50;
    private static int xPoints=0;
    private static int oPoints=0;


    String winnerGame;
    public TileBoard(MenuPanel menuPanel, MenuSettings menuSettings, Score menuScore){
        this.menuPanel=menuPanel;
        this.menuSettings=menuSettings;
        this.menuScore=menuScore;
        pane=new StackPane();
        pane.setVisible(false);
        pane.setMinSize(AppConstants.APP_WIDTH, AppConstants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(AppConstants.APP_WIDTH/2);
        pane.setTranslateY((AppConstants.TILE_BOARD_HEIGHT/2)+AppConstants.MENU_PANEL_HEIGHT);

        addAllTiles();
    }

    public void addAllTiles() {
        for (int row=0; row<tiles.length; row++){
            for (int col=0; col<tiles.length; col++){
                Tile tile=new Tile();
        if(tiles.length==10) {
            tile.getStackPane().setTranslateX((col * 50) - 225);
            tile.getStackPane().setTranslateY((row * 50) - 230);
            pane.getChildren().add(tile.getStackPane());

            tiles[row][col] = tile;
        } else {
            tile.getStackPane().setTranslateX((col * 50) - 50);
            tile.getStackPane().setTranslateY((row * 50) - 200);
            pane.getChildren().add(tile.getStackPane());

            tiles[row][col] = tile;
        }
            }
        }
    }

    public void startNewGame(){
        endGame=false;
        activePlayer="X";


        for (int row=0; row<tiles.length; row++){
            for (int col=0; col<tiles.length; col++){
                tiles[row][col].setValue("");
            }
        }
    }

    public void startNewRound(){
        endGame=false;
        activePlayer="X";
        pane.setVisible(true);

        for (int row=0; row<tiles.length; row++){
            for (int col=0; col<tiles.length; col++){
                tiles[row][col].setValue("");
            }
        }
    }

    public void changeActivePlayer(){
        if(activePlayer.equals("X")){
            activePlayer="0";
        } else {
            activePlayer="X";
        }
        if (withComputer){
            menuPanel.updateMessage("Player - X, Computer - 0, Player turn:");
        } else {
            menuPanel.updateMessage("Player "+activePlayer+" move:");
        }

    }

    public static void setxPoints(int xPoints) {
        TileBoard.xPoints = xPoints;
    }

    public static void setoPoints(int oPoints) {
        TileBoard.oPoints = oPoints;
    }

    public static int getxPoints() {
        return xPoints;
    }

    public static int getoPoints() {
        return oPoints;
    }

    public String getActivePlayer(){
        return String.valueOf(activePlayer);
    }

    public StackPane getStackPane(){
        return pane;
    }

    public void checkWinner(){
        checkWinnerInColumns();
        checkWinnerInRows();
        checkWinnerDiagonally();
        checkDraw();
    }

    public void checkWinnerInColumns(){
        if (tiles.length == 3) {

            for (int col = 0; col < tiles.length; col++) {
                boolean win=true;
                for (int row=0; row< tiles.length; row++) {
                    if (!tiles[row][col].getValue().equals(getActivePlayer()) ) {
                        win = false;
                        break;
                    }
                }
                if (win){
                    addPoint();
                    menuPanel.updateMessage("Player "+ getActivePlayer()+" wins in column!");
                    System.out.println("\u001B[34m"+getActivePlayer() + " win in column!"+"\u001B[0m");
                    pane.setVisible(false);
                    endGame();
                    break;
                }
            }
        } else {
            for (int col=0; col< tiles.length; col++) {
                boolean win=false;
                for (int row=0; row< tiles.length-4; row++) {
                    if((tiles[row][col]).getValue().equals(getActivePlayer())&&
                            (tiles[row+1][col]).getValue().equals(getActivePlayer())&&
                            (tiles[row+2][col]).getValue().equals(getActivePlayer())&&
                            (tiles[row+3][col]).getValue().equals(getActivePlayer())&&
                            (tiles[row+4][col]).getValue().equals(getActivePlayer())&&
                            !(tiles[row][col]).getValue().isEmpty()) {
                        win=true;
                        break;
                    }
                }
                if (win) {
                    addPoint();
                    menuPanel.updateMessage("Player "+ getActivePlayer()+" wins in column!");
                    System.out.println("\u001B[34m"+getActivePlayer() + " win in column!"+"\u001B[0m");
                    endGame();
                    break;
                }
            }
        }
    }

    public void checkWinnerInRows(){
        if(!endGame) {
            if (tiles.length == 3) {

                for (int row = 0; row < tiles.length; row++) {
                    boolean win = true;
                    for (int col = 0; col < tiles.length; col++) {

                        if (!tiles[row][col].getValue().equals(getActivePlayer())) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        addPoint();
                        menuPanel.updateMessage("Player "+ getActivePlayer()+" win in rows!");
                        System.out.println("\u001B[34m" + getActivePlayer() + " win in rows!" + "\u001B[0m");
                        endGame();
                        break;
                    }
                }
            } else {
                boolean win = false;
                for (int row = 0; row < tiles.length; row++) {
                    for (int col = 0; col < tiles.length - 4; col++) {
                        if ((tiles[row][col]).getValue().equals(getActivePlayer()) &&
                                (tiles[row][col + 1]).getValue().equals(getActivePlayer()) &&
                                (tiles[row][col + 2]).getValue().equals(getActivePlayer()) &&
                                (tiles[row][col + 3]).getValue().equals(getActivePlayer()) &&
                                (tiles[row][col + 4]).getValue().equals(getActivePlayer())) {
                            win = true;
                            break;
                        }
                    }
                    if (win) {
                        addPoint();
                        menuPanel.updateMessage("Player "+ getActivePlayer()+" win in rows!");
                        System.out.println("\u001B[34m" + getActivePlayer() + " win in rows!" + "\u001B[0m");
                        endGame();
                        break;
                    }
                }
            }
        }
    }

    public void checkWinnerDiagonally() {
        if(!endGame) {

            if (tiles.length == 3) {

                boolean win = false;
                if (tiles[0][0].getValue().equals(getActivePlayer()) && tiles[1][1].getValue().equals(getActivePlayer()) && tiles[2][2].getValue().equals(getActivePlayer())
                        || tiles[0][2].getValue().equals(getActivePlayer()) && tiles[1][1].getValue().equals(getActivePlayer()) && tiles[2][0].getValue().equals(getActivePlayer())) {
                    win = true;
                }
                if (win) {
                    menuPanel.updateMessage("Player "+ getActivePlayer()+" win diagonally!");
                    System.out.println("\u001B[34m" + getActivePlayer() + " win diagonally!" + "\u001B[0m");
                    addPoint();
                    endGame();
                }

            } else if (tiles.length == 10) {

                boolean win = false;
                for (int i = 0; i < tiles.length - 4; i++) {
                    for (int j = 0; j < tiles.length - 4; j++) {

                        if (tiles[i][j].getValue().equals(getActivePlayer()) &&
                                tiles[i + 1][j + 1].getValue().equals(getActivePlayer()) &&
                                tiles[i + 2][j + 2].getValue().equals(getActivePlayer()) &&
                                tiles[i + 3][j + 3].getValue().equals(getActivePlayer()) &&
                                tiles[i + 4][j + 4].getValue().equals(getActivePlayer())) {
                            win = true;
                            break;
                        }
                    }
                    if (win) {
                        menuPanel.updateMessage("Player "+ getActivePlayer()+" win diagonally!");
                        System.out.println("\u001B[34m" + getActivePlayer() + " win diagonally!" + "\u001B[0m");
                        endGame();
                        break;
                    }
                }

                for (int i = 0; i < tiles.length - 4; i++) {
                    for (int j = tiles.length - 1; j > 3; j--) {

                        if (tiles[i][j].getValue().equals(getActivePlayer()) &&
                                tiles[i + 1][j - 1].getValue().equals(getActivePlayer()) &&
                                tiles[i + 2][j - 2].getValue().equals(getActivePlayer()) &&
                                tiles[i + 3][j - 3].getValue().equals(getActivePlayer()) &&
                                tiles[i + 4][j - 4].getValue().equals(getActivePlayer())) {
                            win = true;
                            break;
                        }
                    }
                    if (win) {
                        menuPanel.updateMessage("Player "+ getActivePlayer()+" win diagonally!");
                        System.out.println("\u001B[34m" + getActivePlayer() + " win diagonally!" + "\u001B[0m");
                        addPoint();
                        endGame();
                        break;
                    }
                }
            }
        }
    }


    public void checkDraw() {
        if (!endGame) {
            for (int row = 0; row < tiles.length; row++) {
                for (int col = 0; col < tiles.length; col++) {
                    if (tiles[row][col].getValue().isEmpty()){
                        return;
                    }
                }
            }
            endGame=true;
            menuPanel.updateMessage("It's a Draw!" );
            endGame();
            }
        }

public void endGame(){
   menuSettings.showPlayButton();
    endGame=true;
    pane.setVisible(false);
}


private void score(){
        menuScore.getStackPane().setVisible(true);
       //menuSettings.showPlayButton();
        menuSettings.showBackToMenu();
        menuPanel.updateMessage("Player "+getActivePlayer()+" win the Game!");
        menuSettings.hidePlayButton();
        menuScore.updateMessage("Congratulations "+getActivePlayer());
       // menuSettings.showExitButton();
}


    public void getComputerInput(Tile[][] tiles, String activePlayer) throws BusyFieldException {

        if (tiles.length == 3) {

            if (difficulty){
                if(tiles[0][0].getValue().equals("X")&&tiles[2][2].getValue().equals("X")&&tiles[1][1].getValue().equals("")) {
                    tiles[1][1].setValue("0");
                } else if (tiles[0][2].getValue().equals("X")&&tiles[2][0].getValue().equals("X")&&tiles[1][1].getValue().equals("")) {
                    tiles[1][1].setValue("0");
                } else if (tiles[0][0].getValue().equals("X")&&tiles[0][2].getValue().equals("X")&&tiles[0][1].getValue().equals("")) {
                    tiles[0][1].setValue("0");
                } else if (tiles[1][0].getValue().equals("X")&&tiles[1][2].getValue().equals("X")&&tiles[1][1].getValue().equals("")) {
                    tiles[1][1].setValue("0");
                } else if (tiles[2][0].getValue().equals("X")&&tiles[2][2].getValue().equals("X")&&tiles[2][1].getValue().equals("")) {
                    tiles[2][1].setValue("0");
                } else if (tiles[0][2].getValue().equals("X")&&tiles[1][1].getValue().equals("X")&&tiles[2][0].getValue().equals("X")) {
                    tiles[2][0].setValue("0");
                } else if (tiles[2][2].getValue().equals("X")&&tiles[1][1].getValue().equals("X")&&tiles[0][0].getValue().equals("")) {
                    tiles[0][0].setValue("0");
                } else if (tiles[0][2].getValue().equals("X")&&tiles[1][1].getValue().equals("X")&&tiles[2][2].getValue().equals("")) {
                    tiles[2][2].setValue("0");
                } else {
                    randomComputerMove(tiles, activePlayer);
                }
            } else {
                randomComputerMove(tiles, activePlayer);
            }


        } else {
            // board 10x10
            //if difficult version
            if (difficulty){
                int found=0;

                //find block in columns
                for (int column = 0; column < tiles.length; column++) {
                    for (int row = 0; row < tiles.length - 4; row++) {

                        if ((tiles[row][column]).getValue().equals("X")&&
                                (tiles[row + 1][column]).getValue().equals("X") &&
                                (tiles[row + 2][column]).getValue().equals("X") &&
                                (tiles[row + 3][column]).getValue().equals("")) {
                            tiles[row + 3][column].setValue("0");
                            found=1;
                            break;
                        }
                    } if (found==1){
                        break;
                    }
                }
                //if no block in columns, find block in rows
                if (found==0) {
                    for (int row = 0; row < tiles.length; row++) {
                        for (int column = 0; column < tiles.length - 4; column++) {

                            if ((tiles[row][column]).getValue().equals("X") &&
                                    (tiles[row][column + 1]).getValue().equals("X") &&
                                    (tiles[row][column + 2]).getValue().equals("X") &&
                                    (tiles[row][column + 3]).getValue().equals("")) {
                                tiles[row][column + 3].setValue("0");
                                found = 1;
                                break;
                            }
                        }
                        if (found == 1) {
                            break;
                        }
                    }
                }
                // if no blocks in columns and rows, do random move
                if (found==0) {
                    randomComputerMove(tiles, activePlayer);
                }
                // if normal version
            } else {
                randomComputerMove(tiles, activePlayer);
            }
        }
    }

    private void randomComputerMove(Tile[][] tiles, String activePlayer) {
        boolean correctMove;
        Random random = new Random();
        do {
            int row = random.nextInt(tiles.length);
            int column = random.nextInt(tiles.length);
            if (tiles[row][column].getValue().equals("")) {
                tiles[row][column].setValue(activePlayer);
                correctMove = false;
            } else {
                correctMove = true;
            }
        }while (correctMove);
    }

    public void hideBoard() {
        for (int row=0; row<tiles.length; row++){
            for (int col=0; col<tiles.length; col++){

                tiles[row][col].getStackPane().setVisible(false);
            }
        }
    }

    public void addPoint(){
        if (getActivePlayer().equals("X")) {
            xPoints++;
            menuPanel.updateXScore("X points: "+xPoints);
        } else if (activePlayer.equals("0")) {
            oPoints++;
            menuPanel.update0Score("0 points: "+oPoints);
        }
    }


    class Tile{
        private StackPane pane;
        private Label label;

        public Tile(){
            pane=new StackPane();
            pane.setMinSize(borderTileSize, borderTileSize);

            Rectangle border = new Rectangle();
            border.setWidth(borderTileSize);
            border.setHeight(borderTileSize);
            border.setFill(Color.LIGHTGRAY);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);
            label = new Label("");
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if(label.getText().isEmpty()&&!endGame){
                    label.setText(getActivePlayer());
                    checkWinner();

                    if(!endGame) {
                       if (withComputer){
                           activePlayer="0";
                           try {
                               getComputerInput(tiles, activePlayer);
                               checkWinner();
                           }catch (BusyFieldException b){
                                      b.printStackTrace();
                                   }
                            activePlayer="X";
                       } else {
                           changeActivePlayer();
                       }
                   } else {
                      if(xPoints ==5||oPoints==5){
                          score();
                      }
                       hideBoard();
                       startNewRound();
                   }
                }
            });
        }

        public StackPane getStackPane(){
            return pane;
        }

        public String getValue(){
            return label.getText();
        }

        public void setValue(String value){
            label.setText(value);
        }
    }


}
