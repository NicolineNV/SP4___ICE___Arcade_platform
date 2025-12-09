package Arcade;

import Arcade.Snake.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

    public void start(Stage stage){

        Pane layout = new Pane();
        GUI gui = new GUI(layout);
        Snake snake = new Snake(layout);
        Scene scene = new Scene(layout,1200,800);

        // Activates CSS file
        try {
            scene.getStylesheets().add(getClass().getResource("/application2.0.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS file not found - runs without styling");
        }

        Button snakeBtn = new Button("SNAKE");
        snakeBtn.getStyleClass().add("snake-button");
        layout.getChildren().add(snakeBtn);

        snakeBtn.setOnAction(e -> {
            Scene gameScene = snake.createGame();
            if (gameScene != null){
                stage.setScene(gameScene);
            }
        });

        stage.setScene(scene);
        stage.show();

    }
}
