package Arcade.SlaggyBird;

import Arcade.MainJavaFX;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SlaggyBirdGame{

    public Scene createGame(Stage stage) {
        Pane layout = new Pane();
        SlaggyBird game = new SlaggyBird(layout);

        Scene scene = new Scene(layout, 1300, 800);
        //Methods-calls to send information to the methods.
        keyListenener(scene);
        timeCounter(game);

        //Display.
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE){
                stage.close();
                MainJavaFX menu = new MainJavaFX();
                menu.start(stage);
            }
            if (e.getCode() == KeyCode.R){
                stage.close();
                createGame(stage);
            }
        });
        return scene;
    }

    public void timeCounter(SlaggyBird game) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.isGameOver()) {
                    this.stop();
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
                    Movements.clickW(Movements.keepsAddingW, Movements.stopsAtW);
                    Movements.clickW(Movements.airTimeW, Movements.turningPointw, Movements.coolDownW);
                    Movements.clicked = "false";
                    break;
                default:
                    break;
            }
        });
    }
}
