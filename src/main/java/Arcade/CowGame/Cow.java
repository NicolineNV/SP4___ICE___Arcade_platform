package Arcade.CowGame;

//import Arcade.GUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Cow extends Application {


    public void start(Stage stage){
        createGame(stage);
    }


    public Scene createGame(Stage stage) {
        Pane layout = new Pane();
        Movements motion = new Movements();
        GameInterface game = new CowGame(layout, motion);

        Scene scene2 = new Scene(layout, 1300, 800);
        scene2.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

        //Methods-calls to send information to the methods.
        keyListenener(scene2, motion, game);
        timeCounter(game);

        //Display.
        stage.setScene(scene2);
        stage.show();
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
