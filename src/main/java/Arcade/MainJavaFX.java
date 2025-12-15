package Arcade;

import Arcade.Asteroids.Asteroids;
import Arcade.SlaggyBird.SlaggyBirdGame;
import Arcade.Snake.Snake;
import Arcade.CowGame.Cow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

    public void start(Stage stage){

        Pane layout = new Pane();

        double width = 1200;
        double height = 800;

        int x = 150;
        int y = 150;
        int spaceX = 300;
        int spaceY = 250;

        //GUI gui = new GUI(layout);
        Snake snake = new Snake();
        Scene scene = new Scene(layout,width,height);
        Asteroids asteroids = new Asteroids();
        Cow cowGame = new Cow();
        SlaggyBirdGame slaggyBird = new SlaggyBirdGame();

        // Activates CSS file
        try {
            scene.getStylesheets().add(getClass().getResource("/applicationNEW.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS file not found - runs without styling");
        }


        Text title = new Text("CHOOSE YOUR GAME!");
        title.setLayoutX((width/2)-200);
        title.setLayoutY(70);
        title.getStyleClass().add("title-label");
        layout.getChildren().add(title);


        Button snakeBtn = new Button("");
        snakeBtn.setLayoutX(x);
        snakeBtn.setLayoutY(y);
        snakeBtn.getStyleClass().add("snake-button");
        layout.getChildren().add(snakeBtn);

        snakeBtn.setOnAction(e -> {
            Scene gameScene = snake.createGame();
            if (gameScene != null){
                stage.setScene(gameScene);
            }
        });


        Button cowBtn = new Button("✨ DAVID THE DINO! ✨");
        cowBtn.setLayoutX(x+spaceX);
        cowBtn.setLayoutY(y);
        cowBtn.getStyleClass().add("dino-button");
        layout.getChildren().add(cowBtn);

        cowBtn.setOnAction(e -> {
            Scene gameScene = cowGame.createGame(stage);
            if (gameScene != null){
                stage.setScene(gameScene);
            }
        });


        Button birdBth = new Button("✨ BARNY THE BIRD! ✨");
        birdBth.setLayoutX((x+spaceX)+spaceX);
        birdBth.setLayoutY(y);
        birdBth.getStyleClass().add("bird-button");
        layout.getChildren().add(birdBth);

        birdBth.setOnAction(e -> {
            Scene gameScene = slaggyBird.createGame(stage);
            if (gameScene != null){
                stage.setScene(gameScene);
            }
        });


        Button astroidsBtn = new Button("");
        astroidsBtn.setLayoutX(x);
        astroidsBtn.setLayoutY(y+spaceY);
        astroidsBtn.getStyleClass().add("astroids-button");
        layout.getChildren().add(astroidsBtn);

        astroidsBtn.setOnAction(e -> {
            Scene gameScene = asteroids.createGame();
            if (gameScene != null){
                stage.setScene(gameScene);
                stage.centerOnScreen();
            }
        });

        stage.setScene(scene);
        stage.show();

    }
}
