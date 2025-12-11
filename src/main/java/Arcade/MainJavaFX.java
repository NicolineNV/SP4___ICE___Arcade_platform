package Arcade;

import Arcade.Asteroids.Asteroids;
import Arcade.Snake.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Arcade.Login;

public class MainJavaFX extends Application {

    public void start(Stage stage){

        Pane layout = new Pane();

        double width = 1200;
        double height = 800;

        int x = 150;
        int y = 150;
        int spaceX = 300;
        int spaceY = 250;

        GUI gui = new GUI(layout);
        Snake snake = new Snake(layout);
        Scene scene = new Scene(layout,width,height);
        Asteroids asteroids = new Asteroids(layout);

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


        Button snakeBtn = new Button("✨ SAM THE SNAKE! ✨");
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


        Button dinoBtn = new Button("✨ DAVID THE DINO! ✨");
        dinoBtn.setLayoutX(x+spaceX);
        dinoBtn.setLayoutY(y);
        dinoBtn.getStyleClass().add("dino-button");
        layout.getChildren().add(dinoBtn);


        Button birdBth = new Button("✨ BARNY THE BIRD! ✨");
        birdBth.setLayoutX((x+spaceX)+spaceX);
        birdBth.setLayoutY(y);
        birdBth.getStyleClass().add("bird-button");
        layout.getChildren().add(birdBth);


        Button astroidsBtn = new Button("✨ ASTROIDS GAME! ✨");
        astroidsBtn.setLayoutX(x);
        astroidsBtn.setLayoutY(y+spaceY);
        astroidsBtn.getStyleClass().add("astroids-button");
        layout.getChildren().add(astroidsBtn);

        astroidsBtn.setOnAction(e -> {
            Scene gameScene = asteroids.createGame();
            if (gameScene != null){
                stage.setScene(gameScene);
            }
        });


        stage.setScene(scene);
        stage.show();

    }
}
