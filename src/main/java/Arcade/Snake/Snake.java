package Arcade.Snake;
import Arcade.Dir;
import Arcade.GUI;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends GUI {

    private static Image slimeGREEN = null;
    private static Image slimeRED = null;
    private static Image slimeBLUE = null;private static int speed = 5; // Sets start speed of the snake
    private static Image slimeYELLOW = null;private static Label scoreLabel;
    private static Image bobTheSnake = null;private static boolean gameOver = false;
    private static Image bobTheSnakeBody = null;private static List <Corner> snake = new ArrayList<>();
    private static Image mainMap = null;private static int width = 20;

    private static int relations = 25; // How many pixels each object is
    private static int height = 20;
    private static Dir direction = Dir.left; // The direction of the snake - starts going left
    private static int foodX = 0;
    private static int foodY = 0;
    private static Random random = new Random();
    private static int foodColor = 0; // Set foodColor to black - it needs to be random


    //Pane layout;
    public Snake(Pane layout){
        super(layout);
    }

    public Scene createGame (){

        try {
            slimeGREEN = new Image(getClass().getResource("/SnakePictures/slimeGREEN.png").toExternalForm());
            slimeRED = new Image(getClass().getResource("/SnakePictures/slimeRED.png").toExternalForm());
            slimeBLUE = new Image(getClass().getResource("/SnakePictures/slimeBLUE.png").toExternalForm());
            slimeYELLOW = new Image(getClass().getResource("/SnakePictures/slimeYELLOW.png").toExternalForm());

            bobTheSnake = new Image(getClass().getResource("/SnakePictures/bobHead.png").toExternalForm());
            bobTheSnakeBody = new Image(getClass().getResource("/SnakePictures/bobBody.png").toExternalForm());

            mainMap = new Image(getClass().getResource("/SnakePictures/MainMap.png").toExternalForm());

            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            root.setSpacing(15);

            Label titleLabel = new Label("✨ BOB THE SNAKE! ✨");
            titleLabel.getStyleClass().add("title-label");

            scoreLabel = new Label("Score: 0");
            scoreLabel.getStyleClass().add("score-label");
            scoreLabel.setFont(new Font(30));
            scoreLabel.setTextFill(Color.CORAL);

            Canvas c = new Canvas(500, 500);

            GraphicsContext gc = c.getGraphicsContext2D();

            Button restartBtn = new Button("RESTART");
            restartBtn.getStyleClass().add("button");
            restartBtn.setOnAction(e -> {
                gameOver = false;
                speed = 5;
                snake.clear();
                snake.add(new Corner(width/2, height/2));
                snake.add(new Corner(width/2, height/2));
                snake.add(new Corner(width/2, height/2));
                direction = Dir.left;
                newFood();
            });

            // Instructions
            Label instructionsLabel = new Label("Use W/A/S/D to control");
            instructionsLabel.getStyleClass().add("label");

            // Adds all to the VBOX
            root.getChildren().addAll(titleLabel, scoreLabel, c, restartBtn, instructionsLabel);

            // This is our play loop
            new AnimationTimer(){
                long lastTick = 0; // lastTick = the time for last update

                // Handle method checks if there has been enough time since last update
                // Makes sure that the update time becomes shorter, if the speed increases -
                // This makes the snake go faster!
                public void handle (long now){ // now = this exact moment in time in nanoseconds
                    if(lastTick == 0){
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    // 1000000000 nanoseconds = 1 second
                    if (now - lastTick > 1000000000 / speed){
                        lastTick = now;
                        tick(gc);
                    }

                    // If lastTick is greater than now = update lastTick!
                    // If not = skip the update
                }
            }.start();

            Scene scene = new Scene(root, width * relations + 50, height * relations + 280);

            //Control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key->{
                if (key.getCode() == KeyCode.W){
                    direction = Dir.up;
                }
                if (key.getCode() == KeyCode.S){
                    direction = Dir.down;
                }
                if (key.getCode() == KeyCode.A){
                    direction = Dir.left;
                }
                if (key.getCode() == KeyCode.D){
                    direction = Dir.right;
                }
                // Tilføj R for restart
                if (key.getCode() == KeyCode.R && gameOver){
                    gameOver = false;
                    speed = 5;
                    snake.clear();
                    snake.add(new Corner(width/2, height/2));
                    snake.add(new Corner(width/2, height/2));
                    snake.add(new Corner(width/2, height/2));
                    direction = Dir.left;
                    newFood();
                }
            });

            // Add start snake parts
            snake.clear();
            snake.add(new Corner(width/2, height/2));
            snake.add(new Corner(width/2, height/2));
            snake.add(new Corner(width/2, height/2));

            newFood();


            // Activates CSS file
            try {
                scene.getStylesheets().add(getClass().getResource("/application2.0.css").toExternalForm());
            } catch (Exception e) {
                System.out.println("CSS file not found - runs without styling");
            }

            return scene;



        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }






    public static void newFood () {
        start: while (true) {

            foodX = random.nextInt(width);
            foodY = random.nextInt(height);

            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY){
                    continue start;
                }
            }

            foodColor = random.nextInt(4);
            speed++;
            break;
        }

    }




    // Tick
    public static void tick (GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.CHOCOLATE);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }


        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;

            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;

            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;

            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
                break;
        }

        // eat food
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        // self destroy
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        // fill
        // Background
        if (mainMap != null){
            gc.drawImage(mainMap, 0, 0, width * relations, height * relations);
        } else{
            gc.setFill(Color.SEAGREEN);
            gc.fillRect(0, 0, width * relations, height * relations);
        }


        // Score
        if (scoreLabel != null){
            scoreLabel.setText("Score: " + (speed - 5));
        }

        // Random foodColor
        Color cc = Color.WHITE;

        double billedeStorrelse = relations * 2;
        double offsetX = (relations - billedeStorrelse) / 2;
        double offsetY = (relations - billedeStorrelse) / 2;

        Image aktuelSlime = null;
        switch (foodColor) {
            case 0: aktuelSlime = slimeGREEN; break;
            case 1: aktuelSlime = slimeRED; break;
            case 2: aktuelSlime = slimeBLUE; break;
            case 3: aktuelSlime = slimeYELLOW; break;
        }

        if (aktuelSlime != null) {
            gc.drawImage(aktuelSlime,
                    foodX * relations + offsetX,
                    foodY * relations + offsetY,
                    billedeStorrelse,
                    billedeStorrelse);
        }
        ////////////////////////////////////////////////////////////////////

        // TODO ændre navne!
        // Snake

        double bobSize = relations * 2;
        double bobOffsetX = (relations - bobSize) / 2;
        double bobOffsetY = (relations - bobSize) / 2;

        for (int i = 0; i < snake.size(); i++) {
            Corner c = snake.get(i);
            Image bob = (i == 0) ? bobTheSnake : bobTheSnakeBody; // Første segment = hoved

            if (bob != null) {
                gc.drawImage(bob, (c.x * relations) + bobOffsetX, (c.y * relations) + bobOffsetY, bobSize, bobSize);
            }
        }
    }
}

