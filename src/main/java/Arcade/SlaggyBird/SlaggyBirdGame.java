package Arcade.SlaggyBird;

import Arcade.MainJavaFX;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SlaggyBirdGame {



    public Scene createGame(Stage stage) {

        resetMovements();

        Pane layout = new Pane();
        SlaggyBird game = new SlaggyBird(layout);

        Scene scene = new Scene(layout, 1300, 800);
        scene.getStylesheets().add(getClass().getResource("/applicationNEW.css").toExternalForm());
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

                createGame(stage);
            }
        });
        return scene;
    }

    private void resetMovements() {
        Movements.clicked = "false";
        Movements.addW = 0;
        Movements.addOnceW = 0;
        Movements.addS = 0;
        Movements.addOnceS = 0;
        Movements.addA = 0;
        Movements.addOnceA = 0;
        Movements.addD = 0;
        Movements.addOnceD = 0;
        Movements.addAtW = 0;
        Movements.keepsAddingW = 0;
        Movements.stopsAtW = 0;
        Movements.addAtS = 0;
        Movements.keepsAddingS = 0;
        Movements.stopsAtS = 0;
        Movements.addAtA = 0;
        Movements.keepsAddingA = 0;
        Movements.stopsAtA = 0;
        Movements.addAtD = 0;
        Movements.keepsAddingD = 0;
        Movements.stopsAtD = 0;
        Movements.coolDownW = false;
        Movements.airTimeW = 0;
        Movements.turningPointw = 0;
        Movements.jumpW = 0;
        Movements.jW = 0;
        Movements.gravity = 0;
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
