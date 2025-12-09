package Arcade;

import Arcade.Snake.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

    public void start(Stage stage){

        double width = 1200;
        double height = 800;

        int x = 75;
        int y = 150;
        int space = 300;


        Pane layout = new Pane();
        GUI gui = new GUI(layout);
        Snake snake = new Snake(layout);
        Scene scene = new Scene(layout,width,height);

        // Activates CSS file
        try {
            scene.getStylesheets().add(getClass().getResource("/application2.0.css").toExternalForm());
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
        dinoBtn.setLayoutX(x+space);
        dinoBtn.setLayoutY(y);
        dinoBtn.getStyleClass().add("dino-button");
        layout.getChildren().add(dinoBtn);


        Button birdBth = new Button("✨ BARNY THE BIRD! ✨");
        birdBth.setLayoutX((x+space)+space);
        birdBth.setLayoutY(y);
        birdBth.getStyleClass().add("bird-button");
        layout.getChildren().add(birdBth);


        stage.setScene(scene);
        stage.show();

    }
}
