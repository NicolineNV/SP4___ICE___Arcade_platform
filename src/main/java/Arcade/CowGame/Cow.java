package Arcade.CowGame;

import Arcade.MainJavaFX;
import Arcade.Menu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Cow {

    public Scene createGame(Stage stage) {
        Pane layout = new Pane();
        Movements motion = new Movements();
        GameInterface game = new CowGame(layout, motion);

        Scene scene2 = new Scene(layout, 1300, 800);
        scene2.getStylesheets().add(getClass().getResource("/applicationNEW.css").toExternalForm());

        //Methods-calls to send information to the methods.
        keyListenener(scene2, motion, game);
        timeCounter(game);

        //Display.
        stage.setScene(scene2);
        stage.show();

        scene2.setOnKeyReleased(e -> {
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

        return scene2;

    }


    public void timeCounter(GameInterface game) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (game.isGameOver()) {
                    this.stop();
                    return;
                }

                game.runConstant();
                game.gravity();
                game.runGame();
            }
        };
        timer.start();

    }

    public void keyListenener(Scene scene, Movements motion, GameInterface game) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    motion.clickW();
                    motion.gravityW("W");
                    game.runConstant();
                    game.runClickedW();
                    break;
                case S:
                    motion.clickS();
                    motion.gravityS("S");
                    game.runConstant();
                    game.runClickedS();
                    break;
                case A:
                    motion.clickA();
                    motion.gravityA("A");
                    game.runConstant();
                    game.runClickedA();
                    break;
                case D:
                    motion.clickD();
                    motion.gravityD("D");
                    game.runConstant();
                    game.runClickedD();
                    break;
                 default:
                    break;
            }
        });
    }
}
