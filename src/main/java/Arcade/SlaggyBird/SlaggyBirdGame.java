package Arcade.SlaggyBird;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SlaggyBirdGame {

    public Scene createGame(Stage stage) {
        display(stage);
        return stage.getScene();
    }

    public void display(Stage stage) {
        Pane layout = new Pane();

        SlaggyBird game = new SlaggyBird(layout);
        Scene scene = new Scene(layout, 1300, 800);

        //Methods-calls to send information to the methods.
        keyListenener(scene);
        timeCounter(game);

        //Display.
        stage.setScene(scene);
        stage.show();
    }


    public void timeCounter(SlaggyBird game) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.isGameOver()) {
                    this.stop(); // stops the animation
                    return;
                }
                game.runGame();
            }
        };
        timer.start();

    }

    public void keyListenener(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    Movements.clicked = "W";
                    Movements.clickW(Movements.keepsAddingW,Movements.stopsAtW);
                    Movements.clickW(Movements.airTimeW,Movements.turningPointw,Movements.coolDownW);
                    Movements.clicked = "false";
                    break;
                default:
                    break;
            }
        });
    }
}
